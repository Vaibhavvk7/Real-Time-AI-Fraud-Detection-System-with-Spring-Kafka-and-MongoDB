package com.vaibhav.fraud.repo;

import com.vaibhav.fraud.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {}

