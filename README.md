# Real-Time-AI-Fraud-Detection-System-with-Spring-Kafka-and-MongoDB
Real-time fraud detection system using MongoDB Atlas Vector Search, Apache Kafka, and LLM-generated embeddings. The pipeline detects anomalous financial transactions by comparing incoming events against user transaction history in real time using AI-powered vector similarity search.

This repository demonstrates how to build a real-time fraud detection system using MongoDB Atlas Vector Search, Apache Kafka, and LLM-generated embeddings.
The system analyzes streaming financial transactions, compares each new transaction against a user’s historical behavior using vector similarity search, and flags suspicious activity in near real time.
This project is designed as a hands-on, end-to-end demo, while also discussing architectural trade-offs and scalability considerations for production systems.


**🧠What This Project Demonstrates**
AI-powered anomaly detection using vector embeddings
Real-time stream processing with Kafka
Event-driven fraud detection using MongoDB Change Streams
Practical use of MongoDB Atlas Vector Search in a transactional system
Design trade-offs between simplicity, latency, and scalability

**🏗️ Architecture Overview**
The real-time fraud detection pipeline works as follows:

**1. Generate Customer Profiles**
Synthetic users with predictable spending behavior are generated and stored in MongoDB.

**2. Produce Transactions (Kafka Producer)**
Simulates financial transactions
Generates AI embeddings for each transaction
Publishes transactions to a Kafka topic

**3. Consume Transactions (Kafka Consumer)**
Reads transactions from Kafka
Persists them to MongoDB

**4. Monitor MongoDB in Real Time**
Uses MongoDB Change Streams (Java synchronous driver)
Listens for newly inserted transactions

**5. Run Vector Search for Anomaly Detection**
Compares the new transaction embedding against the user’s historical transactions
Uses MongoDB Atlas Vector Search for similarity matching

**6. Detect Fraud**
A transaction is flagged as fraudulent if:
No similar historical transactions exist for the user, or
Any similar transaction is already marked as fraud


⚙️ Tech Stack
Java 21
Apache Kafka 3.5+
MongoDB Atlas (M0 or higher)
Vector Search
Change Streams
OpenAI API (for embeddings)
Maven 3.9+

📦 Prerequisites
Before running this project, ensure you have:
A MongoDB Atlas account with a free M0 (or higher) cluster
Kafka 3.5+ (local install or Docker)
Java 21 installed
Maven 3.9+
An OpenAI API key for generating embeddings
