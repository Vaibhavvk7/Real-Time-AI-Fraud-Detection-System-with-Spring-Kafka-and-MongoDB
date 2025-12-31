package com.vaibhav.fraud;

import com.vaibhav.fraud.kafka.TransactionProducer;
import com.vaibhav.fraud.model.Transaction;
import com.vaibhav.fraud.service.EmbeddingGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

@Component
public class Runner implements CommandLineRunner {

    private final TransactionProducer producer;
    private final EmbeddingGenerator embeddingGenerator;

    @Value("${app.kafka.topic:transactions}")
    private String topic;

    public Runner(TransactionProducer producer, EmbeddingGenerator embeddingGenerator) {
        this.producer = producer;
        this.embeddingGenerator = embeddingGenerator;
    }

    @Override
    public void run(String... args) {
        try {
            Transaction tx = new Transaction();
            tx.setTransactionId(UUID.randomUUID().toString());
            tx.setCustomerId("user-" + (new Random().nextInt(3) + 1));
            tx.setAmount(10 + new Random().nextInt(90));
            tx.setMerchant("TestMerchant");
            tx.setCategory("RETAIL");
            tx.setCurrency("USD");
            tx.setTimestamp(Instant.now());
            tx.setFraud(false);

            // REAL embedding (1536 dims for text-embedding-3-small)
            String embeddingText = tx.generateEmbeddingText();
            tx.setEmbedding(embeddingGenerator.getEmbedding(embeddingText));

            producer.send(topic, tx);

            System.out.println("📤 Sent tx with embedding dims = " + tx.getEmbedding().length);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
