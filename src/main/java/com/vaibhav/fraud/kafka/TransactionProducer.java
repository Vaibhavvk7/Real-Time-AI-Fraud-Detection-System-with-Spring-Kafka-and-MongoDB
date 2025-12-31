package com.vaibhav.fraud.kafka;

import com.vaibhav.fraud.model.Transaction;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public TransactionProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, Transaction tx) {
        kafkaTemplate.send(topic, tx.getCustomerId(), tx);
        System.out.println("📤 Produced to Kafka: " + tx.getCustomerId() + " " + tx.getAmount());
    }
}
