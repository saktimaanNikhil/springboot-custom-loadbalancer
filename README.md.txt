📖 Overview
============= 
This project demonstrates a simple custom load balancing system built using Spring Boot.

The system consists of two modules:

api-service – REST API service (multiple instances)

load-balancer – Custom Round Robin load balancer

The load balancer distributes incoming client requests between two running instances of the API service.

🏗️ Architecture
================
Client
   ↓
Load Balancer (Port 8080)
   ↓ (Round Robin)
---------------------------------
| API Service Instance 1 (8081)
| API Service Instance 2 (8082)
---------------------------------

🛠️ Technologies Used
========================
Java 17

Spring Boot 3.x

Maven

REST APIs

AtomicInteger (Thread-safe Round Robin)

Global Exception Handling

🚀 Setup and Run Instructions
================================
(1) Clone the Repository
--------------------
git clone <your-repository-url>
cd springboot-custom-loadbalancer

(2) Build API Service
------------------
cd api-service
mvn clean package

(3) Start Multiple API Instances
----------------------------
Start First Instance (Port 8081)
	java -jar target/api-service-0.0.1-SNAPSHOT.jar --server.port=8081

Start Second Instance (Port 8082)

	Open another terminal:

	java -jar target/api-service-0.0.1-SNAPSHOT.jar --server.port=8082


(4) Start Load Balancer
-------------------------
cd load-balancer
mvn spring-boot:run

Load balancer runs on:

http://localhost:8080


(5️) Test Load Balancing
-------------------------
Open browser or Postman:

	http://localhost:8080/api/hello

Refresh multiple times.

Expected behavior:

Request 1 → 8081

Request 2 → 8082

Request 3 → 8081

Request 4 → 8082

🔁 Load Balancing Strategy
============================
The project implements:

✅ Round Robin Algorithm

	Uses AtomicInteger

	Thread-safe implementation

	Even traffic distribution

	Simple and lightweight approach


🛡️ Exception Handling
=======================
	Custom exception class (ServiceUnavailableException)

	Centralized global exception handling using @RestControllerAdvice

	Returns structured JSON error response

	Returns HTTP 503 when service instance is down

Example error response:
	{
  "timestamp": "2026-02-24T18:40:00",
  "status": 503,
  "error": "Service Unavailable",
  "message": "Target service is down"
}

📂 Project Structure
=======================
springboot-custom-loadbalancer
 ├── api-service
 └── load-balancer

📌 Assumptions Made
======================
Service instances are statically configured (localhost)

No dynamic service discovery

No authentication or security layer

Designed for local environment testing

Only GET request routing implemented


🚀 Possible Improvements / Enhancements
=========================================
Add Health Check before routing

Implement Retry Mechanism

Add Circuit Breaker (Resilience4j)

Use Spring Cloud LoadBalancer

Integrate Service Registry (Eureka)

Add Logging Framework (SLF4J + Logback)

Add Metrics with Spring Actuator

Dockerize both applications

Deploy on Kubernetes


👨‍💻 Author
===========
Nikhil 
Java Backend Developer

