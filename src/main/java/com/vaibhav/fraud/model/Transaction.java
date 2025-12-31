package com.vaibhav.fraud.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;

    @JsonProperty("transaction_id")
    private String transactionId;

    private String customerId;     // tutorial calls this userId
    private double amount;
    private String merchant;
    private String category;       // keep as String for now (easy)
    private String currency;       // keep as String for now (easy)
    private Instant timestamp;

    private boolean fraud;

    // IMPORTANT: store as float[] to match Atlas vector index dimension + Spring AI output
    private float[] embedding = {};

    public Transaction() {}

    // ---- helpers ----
    public String generateEmbeddingText() {
        return customerId + " " + amount + " " + currency + " " + merchant + " " + category;
    }

    // ---- getters/setters ----
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getMerchant() { return merchant; }
    public void setMerchant(String merchant) { this.merchant = merchant; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public boolean isFraud() { return fraud; }
    public void setFraud(boolean fraud) { this.fraud = fraud; }

    public float[] getEmbedding() { return embedding; }
    public void setEmbedding(float[] embedding) { this.embedding = embedding; }
}
