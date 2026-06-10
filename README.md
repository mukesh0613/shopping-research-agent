# Shopping Research Agent

An AI-powered Shopping Research Agent built with Spring Boot and Groq LLM that performs product extraction, price comparison, review analysis, and recommendation generation using a multi-agent architecture.

## Features

* AI Product Extraction
* Multi-Agent Architecture
* Parallel Provider Search
* Price Analysis
* Review Analysis
* Recommendation Engine
* Observability Metrics
* Caching with Caffeine
* Retry & Timeout Handling

## Agents

* ShoppingCoordinatorAgent
* ProductExtractionAgent
* PriceAgent
* ReviewAgent
* PriceAnalysisAgent
* ReviewAnalysisAgent
* RecommendationAgent
* MetricsAgent

## Supported Providers

* Amazon
* Flipkart
* Blinkit
* BigBasket

## Tech Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Web
* Spring Data JPA

### AI

* Groq LLM

### Database

* PostgreSQL

### Caching

* Caffeine Cache

### Concurrency

* CompletableFuture
* ExecutorService

### Resilience

* Spring Retry

## Architecture

User Query
→ ResearchController
→ ShoppingCoordinatorAgent
→ ProductExtractionAgent
→ PriceAgent
→ Providers (Amazon, Flipkart, Blinkit, BigBasket)
→ PriceAnalysisAgent
→ ReviewAgent
→ RecommendationAgent
→ Final Response

## Experimental Modules

This repository also contains implementations for:

* ReAct Framework
* Graph-Based Execution Framework
* Memory Framework
* Tool Calling Framework

These modules were created to learn advanced Agentic AI concepts and will be integrated into future versions.

## Future Enhancements

* Real Ecommerce APIs
* Vector Database Support
* RAG-based Review Analysis
* LangGraph-style Execution
* Autonomous Shopping Agents

## Version

v1.0

## Author

Mukesh
