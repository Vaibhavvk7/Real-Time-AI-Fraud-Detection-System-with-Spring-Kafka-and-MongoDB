package com.vaibhav.fraud.kafka;

import com.vaibhav.fraud.model.Transaction;
import com.vaibhav.fraud.repo.TransactionRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumer {

    private final TransactionRepository repo;

    public TransactionConsumer(TransactionRepository repo) {
        this.repo = repo;
    }

    @KafkaListener(topics = "${app.kafka.topic:transactions}", groupId = "fraud-consumer")
    public void consume(Transaction tx) {
        repo.save(tx);
        System.out.println("✅ Saved to MongoDB: customerId=" + tx.getCustomerId() + ", amount=" + tx.getAmount());
    }
}
