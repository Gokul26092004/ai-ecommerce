# AI-Powered E-Commerce Platform

## Abstract

This project presents the design and implementation of an **AI-Powered E-Commerce Platform** that integrates modern web technologies with artificial intelligence to deliver a smart, intuitive, and personalized online shopping experience.

The system architecture follows a **microservices pattern** with three core components:

1. **Backend Service** — A Spring Boot (Java 17) RESTful API handling user authentication (JWT-based), product management, cart operations, order processing, and review management. Data persistence is powered by **MySQL** with JPA/Hibernate ORM.

2. **Frontend Application** — A React.js single-page application providing a responsive user interface for product browsing, cart management, checkout, and order history. The frontend communicates with the backend via Axios HTTP client with JWT token-based authentication.

3. **AI Microservice** — A FastAPI (Python) service that provides intelligent features:
   - **Product Recommendations** — Suggests related products using collaborative filtering.
   - **Visual Search** — Uses a pretrained **MobileNetV2** deep learning model (PyTorch + torchvision) to extract image features and perform **cosine similarity search** across the product catalog, enabling users to upload an image and find visually similar products.
   - **Sentiment Analysis** — Analyzes customer review text to classify sentiment.
   - **Natural Language Search** — Enables text-based product search.

### Key Technical Highlights
- **JWT Authentication** with role-based access control (RBAC)
- **Spring Security** for endpoint protection
- **LRU Caching** for visual search index optimization
- **CORS-enabled** cross-origin communication between services
- **Lombok** for boilerplate reduction in Java entities
- **React Hooks** (useState, useEffect, useMemo) for efficient frontend state management

### Technology Stack

| Layer        | Technology                              |
|-------------|------------------------------------------|
| Frontend    | React.js, Axios, React Router            |
| Backend     | Spring Boot 3.2.3, Spring Security, JPA  |
| AI Service  | FastAPI, PyTorch, torchvision, scikit-learn |
| Database    | MySQL 8.0                                |
| Auth        | JWT (JSON Web Tokens)                    |
| Build Tools | Maven (Backend), npm (Frontend), pip (AI) |

### Outcome
The platform demonstrates a practical integration of AI/ML models within a full-stack e-commerce application, offering visual search, smart recommendations, and sentiment analysis — capabilities that elevate the user experience far beyond traditional e-commerce systems.
