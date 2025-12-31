// package com.vaibhav.fraud.service;

// import org.springframework.ai.embedding.EmbeddingModel;
// import org.springframework.ai.embedding.EmbeddingRequest;
// import org.springframework.ai.embedding.EmbeddingResponse;
// import org.springframework.ai.openai.OpenAiEmbeddingOptions;
// import org.springframework.stereotype.Component;

// import java.util.List;

// @Component
// public class EmbeddingGenerator {

//     private final EmbeddingModel embeddingModel;

//     public EmbeddingGenerator(EmbeddingModel embeddingModel) {
//         this.embeddingModel = embeddingModel;
//     }

//     public float[] getEmbedding(String text) {
//         try {
//             EmbeddingResponse response = embeddingModel.call(
//                 new EmbeddingRequest(List.of(text), OpenAiEmbeddingOptions.builder().build())
//             );
//             return response.getResults().get(0).getOutput();
//         } catch (Exception e) {
//             // fallback so pipeline keeps working
//             return new float[]{0.01f, 0.02f, 0.03f};
//         }
//     }

// }

package com.vaibhav.fraud.service;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmbeddingGenerator {

    private static final int DIMENSIONS = 1536; // MUST match Atlas vector index
    private final EmbeddingModel embeddingModel;

    public EmbeddingGenerator(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    public float[] getEmbedding(String text) {
        try {
            EmbeddingResponse response = embeddingModel.call(
                new EmbeddingRequest(
                    List.of(text),
                    OpenAiEmbeddingOptions.builder()
                        // model is already set in application.yml, but safe to include:
                        .model("text-embedding-3-small")
                        .build()
                )
            );

            float[] vec = response.getResults().get(0).getOutput();

            // Guard: if model ever returns unexpected dims, fallback to keep pipeline stable
            if (vec == null || vec.length != DIMENSIONS) {
                System.out.println("⚠️ Embedding dims unexpected: " +
                        (vec == null ? "null" : vec.length) + " → using fallback");
                return fallback1536();
            }

            return vec;

        } catch (Exception e) {
            // Most likely quota/billing (HTTP 429 insufficient_quota) → keep pipeline running
            System.out.println("⚠️ OpenAI embedding failed (" + e.getClass().getSimpleName() + "): "
                    + (e.getMessage() == null ? "" : e.getMessage()));
            return fallback1536();
        }
    }

    private float[] fallback1536() {
        float[] v = new float[DIMENSIONS];
        v[0] = 0.01f;
        v[1] = 0.02f;
        v[2] = 0.03f;
        return v;
    }
}
