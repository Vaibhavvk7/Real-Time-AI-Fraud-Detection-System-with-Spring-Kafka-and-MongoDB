package com.vaibhav.fraud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("customers")
public class Customer {

    @Id
    private String id;

    private String customerId;
    private String name;
    private List<String> typicalMerchants;
    private double avgAmount;

    public Customer() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getTypicalMerchants() { return typicalMerchants; }
    public void setTypicalMerchants(List<String> typicalMerchants) {
        this.typicalMerchants = typicalMerchants;
    }

    public double getAvgAmount() { return avgAmount; }
    public void setAvgAmount(double avgAmount) { this.avgAmount = avgAmount; }
}

