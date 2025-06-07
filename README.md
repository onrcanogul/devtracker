# DevTracker - Event-Driven Microservices Architecture

This project is a hands-on implementation of an **event-driven system** using **Spring Boot**, **RabbitMQ**, and **Docker**. It was built with the goal of mastering asynchronous communication between microservices and understanding robust messaging patterns in distributed systems.

---

## Purpose

The primary purpose of this project is to:
- Learn and implement **event-driven architecture**
- Understand how **microservices** communicate asynchronously
- Build reliable message workflows using **Retry**, **DLQ (Dead Letter Queue)**, and **Correlation IDs**
- Improve skills in **distributed system design**

---

## Tech Stack

- **Java 21 + Spring Boot 3**
- **RabbitMQ** for message brokering
- **Docker + Docker Compose**
- **PostgreSQL** for persistence
- **Event-based communication** with:
  - `@RabbitListener`, `@RabbitHandler`
  - Retry policies, DLQ handling
- **Custom correlation ID injection**
- Clean, modular service separation:
  - `GitHubService`
  - `LogService`
  - `InsightService`
  - `GoalService`

---

## Key Features

- Event publishing and consuming with RabbitMQ
- Retry & DLQ configuration with full fallback support
- `@RabbitHandler`-based message dispatching
- Event chaining: Log → Insight → Goal Progress
- Docker-ready microservice setup

---

## Microservices

| Service         | Description                                      |
|-----------------|--------------------------------------------------|
| `GitHubService` | Publishes commit events                          |
| `LogService`    | Creates logs from commits and emits log events   |
| `InsightService`| Listens to log events, performs AI analysis      |
| `GoalService`   | Updates goal progress based on insights          |

---

## Example Event Flow

```text
CommitCreatedEvent
    ↓
LogCreatedEvent
    ↓
LogAnalyzedEvent
    ↓
GoalProgressEvaluatedEvent
