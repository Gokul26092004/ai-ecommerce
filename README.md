# AI-Powered E-Commerce Platform

A full-stack e-commerce application that combines a traditional online shopping experience with AI-powered product discovery and product intelligence. The project is organised into three main components: a **React frontend**, a **Java Spring Boot backend**, and a **Python FastAPI AI service**.

> **Repository structure note:** The project components currently live on separate Git branches. The default `main` branch contains the AI-service files; use the branch links below to explore the backend and frontend. They are not yet combined into one directory tree on `main`.

<p align="center">
  <a href="https://github.com/Gokul26092004/ai-ecommerce/tree/Front_end"><img src="https://img.shields.io/badge/Frontend-React-61DAFB?logo=react&logoColor=black" alt="React frontend"></a>
  <a href="https://github.com/Gokul26092004/ai-ecommerce/tree/backend"><img src="https://img.shields.io/badge/Backend-Spring%20Boot-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot backend"></a>
  <a href="https://github.com/Gokul26092004/ai-ecommerce/tree/ai-service"><img src="https://img.shields.io/badge/AI%20Service-FastAPI-009688?logo=fastapi&logoColor=white" alt="FastAPI AI service"></a>
  <img src="https://img.shields.io/badge/Database-MySQL-4479A1?logo=mysql&logoColor=white" alt="MySQL">
</p>

## Contents

- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Repository Branches](#repository-branches)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [AI Service API](#ai-service-api)
- [Configuration](#configuration)
- [Current Implementation Status](#current-implementation-status)
- [Future Improvements](#future-improvements)

## Project Overview

The goal of this project is to build an e-commerce application where users can browse products, manage a shopping cart, place orders, and discover products with the help of AI-based services.

The application is split into separate layers:

| Component | Responsibility | Main technology |
|---|---|---|
| Frontend | Shopping UI, navigation, product and order pages | React |
| Backend | REST APIs, authentication, business logic and persistence | Java, Spring Boot |
| AI service | Image similarity and experimental recommendation/NLP/ML utilities | Python, FastAPI |

## Architecture

```text
                CUSTOMER
                   |
                   v
        +-----------------------+
        |    React Frontend     |
        | Product / Cart /      |
        | Checkout / Account    |
        +-----------+-----------+
                    |
              HTTP / REST
                    |
                    v
        +-----------------------+
        | Spring Boot Backend   |
        | Authentication (JWT) |
        | Products, Cart,       |
        | Orders, Reviews       |
        +-----+-----------+-----+
              |           |
              v           v
          +--------+  +------------------+
          | MySQL  |  | Python AI Service|
          |        |  | FastAPI          |
          +--------+  | Visual Search    |
                      | AI utilities     |
                      +------------------+
```

The diagram describes the intended component responsibilities. The source code is maintained on separate branches, and the degree of integration between all components should be verified in a local end-to-end run.

## Features

### E-commerce application

The frontend branch includes pages and navigation for:

- User registration and login
- Product listing and product details
- Shopping cart
- Checkout
- Order history
- About page

The Spring Boot backend branch contains controllers and supporting packages for authentication, products, product images, cart, orders, recommendations, and reviews.

### Authentication and security

- Spring Security configuration
- JWT utility and request filter
- Registration and login endpoints
- Token attachment in frontend API requests

### Product and order management

- Product API/controller layer
- Product image upload utilities
- Image resizing support
- Cart and order controller/service/repository layers
- Review-related controller and repository layers

### AI-powered capabilities

| Capability | Implementation in repository | Status |
|---|---|---|
| Visual product search | MobileNetV2 image features + cosine similarity | Implemented in FastAPI service |
| Product recommendations | TF-IDF over product descriptions + cosine similarity | Standalone Python function; API route is currently placeholder |
| Text product search | FastAPI `/search` route | Placeholder response |
| Review sentiment | Hugging Face DistilBERT pipeline in a separate script | Prototype; API route is currently placeholder |
| Price prediction | Scikit-learn Linear Regression script | Standalone experiment |
| Product description/tags | BLIP and text-generation prototype script | Experimental script; verify/fix before use |

**Visual search flow:** the AI service extracts image features with a pretrained MobileNetV2 model, compares the query image to indexed product images using cosine similarity, and returns the top similar image paths with scores.

## Technology Stack

### Frontend
- React 19
- React Router
- Axios
- Tailwind CSS

### Backend
- Java 17
- Spring Boot 3.2.3
- Spring Web
- Spring Data JPA / Hibernate
- Spring Security
- JSON Web Tokens (JJWT)
- MySQL Connector/J
- Maven
- Lombok
- Thumbnailator

### AI service
- Python
- FastAPI and Uvicorn
- PyTorch and Torchvision
- MobileNetV2
- Pillow and NumPy
- scikit-learn
- pandas
- Hugging Face Transformers

## Repository Branches

The repository currently separates the components by branch:

| Branch | What it contains | Link |
|---|---|---|
| `main` | AI service snapshot and root README | [Open main](https://github.com/Gokul26092004/ai-ecommerce/tree/main) |
| `backend` | Spring Boot backend (`pom.xml`, Java source, security, controllers, services) | [Open backend](https://github.com/Gokul26092004/ai-ecommerce/tree/backend) |
| `Front_end` | React application under `frontend/` | [Open frontend](https://github.com/Gokul26092004/ai-ecommerce/tree/Front_end) |
| `ai-service` | FastAPI service, AI scripts, dataset and project documentation | [Open AI service](https://github.com/Gokul26092004/ai-ecommerce/tree/ai-service) |

## Project Structure

The following is a **logical view across branches**, not a claim that all folders exist together on `main`.

```text
ai-ecommerce/
|
|-- Front_end branch
|   \-- frontend/
|       |-- src/
|       |   |-- components/
|       |   |-- pages/
|       |   |   |-- home.js
|       |   |   |-- login.js
|       |   |   |-- register.js
|       |   |   |-- products.js
|       |   |   |-- ProductDetail.js
|       |   |   |-- cart.js
|       |   |   |-- checkout.js
|       |   |   \-- orders.js
|       |   \-- services/
|       |       |-- api.js
|       |       \-- authService.js
|       |-- package.json
|       \-- tailwind.config.js
|
|-- backend branch
|   |-- pom.xml
|   |-- mvnw / mvnw.cmd
|   \-- src/
|       |-- main/java/com/gokul/ai/ai_ecommerce/
|       |   |-- Controller/
|       |   |-- Service/
|       |   |-- Repository/
|       |   |-- Entity/
|       |   |-- Security/
|       |   |-- Config/
|       |   |-- Dto/
|       |   \-- ProductImageUpload/
|       \-- main/resources/
|           \-- application.properties
|
\-- ai-service branch
    |-- app/
    |   |-- main.py
    |   |-- schemas/
    |   \-- services/
    |       |-- visual_search_service.py
    |       |-- Sentiment_Analysis.py
    |       |-- price_prediction.py
    |       \-- auto_product_ai.py
    |-- data/
    |   \-- products.csv
    |-- docs/
    |-- recommender.py
    \-- requirements.txt
```

## Getting Started

Because the components are stored on different branches, clone the repository and check out each branch separately (or use separate working directories).

### 1. Clone the repository

```bash
git clone https://github.com/Gokul26092004/ai-ecommerce.git
cd ai-ecommerce
```

### 2. Run the Spring Boot backend

Switch to the backend branch:

```bash
git switch backend
```

Requirements: Java 17+, Maven (or the included Maven wrapper), and MySQL.

Configure your local database connection and JWT settings in `src/main/resources/application.properties`. Do not commit real passwords, secret keys, or production credentials.

Run on Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Run on macOS/Linux:

```bash
./mvnw spring-boot:run
```

The frontend API client currently uses `http://localhost:8080/api` as its backend base URL.

### 3. Run the React frontend

Use a separate terminal or a second clone/worktree, then:

```bash
git switch Front_end
cd frontend
npm install
npm start
```

The React development server starts using the default Create React App configuration (usually `http://localhost:3000`).

### 4. Run the Python AI service

Use another terminal or working directory:

```bash
git switch ai-service
python -m venv .venv
```

Activate the environment:

Windows PowerShell:
```powershell
.\.venv\Scripts\Activate.ps1
```

macOS/Linux:
```bash
source .venv/bin/activate
```

Install dependencies and start FastAPI:

```bash
pip install -r requirements.txt
uvicorn app.main:app --reload --port 8000
```

Open the interactive API documentation at [http://localhost:8000/docs](http://localhost:8000/docs).

> **AI dataset note:** Visual search expects a product-image dataset in `data/images/`. The repository snapshot may not include the image files. Add your own permitted product images before testing the visual-search endpoint.

## AI Service API

The following routes are defined in `app/main.py` on the `ai-service` branch.

| Method | Endpoint | Purpose | Current behavior |
|---|---|---|---|
| GET | `/health` | Health check | Returns service status |
| POST | `/visual-search` | Find visually similar product images | Uses MobileNetV2 features and cosine similarity |
| POST | `/recommand` | Recommendation API | Currently returns fixed sample IDs |
| POST | `/search` | Text search API | Currently returns placeholder results |
| POST | `/sentiment` | Sentiment API | Currently returns a fixed positive label |

Example health check:

```bash
curl http://localhost:8000/health
```

Example visual search request:

```bash
curl -X POST "http://localhost:8000/visual-search" \
  -F "file=@/path/to/product-image.jpg"
```

Example request bodies for the prototype routes:

```json
{ "product_id": 101 }
```

```json
{ "query": "wireless headphones" }
```

```json
{ "text": "The product quality is excellent." }
```

Use the generated OpenAPI page at `/docs` for the exact request schemas and responses of the checked-out AI-service branch.

## Configuration

Keep machine-specific settings outside source control. At minimum, configure:

- MySQL URL, username and password for the Spring Boot backend
- JWT signing secret and token settings
- Frontend API base URLs for the backend and AI service
- Any required model or dataset paths for the AI service

Use environment variables or an untracked local configuration file for secrets. Never paste API keys, database passwords, or JWT secrets into this README or commit them to Git.

## Current Implementation Status

This README distinguishes implemented paths from prototypes and placeholders based on the repository source:

- The React frontend contains routes/pages for the main shopping flow.
- The backend contains Spring Boot controllers and supporting code for users/authentication, products, cart, orders, reviews and recommendation-related operations.
- The AI service has a working visual-search implementation when a compatible image dataset is available.
- The TF-IDF recommender is implemented as a standalone Python function.
- The AI API's recommendation, text-search and sentiment endpoints currently return hard-coded sample responses; they should not be described as completed AI integrations.
- Sentiment, price prediction, and product-description generation exist as separate scripts/prototypes and are not all wired into the FastAPI routes.

## Future Improvements

- Bring the frontend, backend and AI service into a unified repository layout or document a repeatable multi-branch workflow.
- Connect backend AI calls to the FastAPI service with configurable service URLs and robust error handling.
- Integrate the TF-IDF recommendation logic into the recommendation endpoint.
- Replace placeholder text-search and sentiment responses with real implementations.
- Correct and test the experimental product-description generation script before exposing it through an API.
- Add automated tests for backend, frontend and AI service flows.
- Add screenshots, API examples, deployment instructions and environment templates.

## Documentation

The `ai-service` branch includes additional project documentation under `docs/`, including an abstract, demo script, ER diagram, UML diagrams, report, PPT outline and screenshot notes.

---

**Maintainer:** [Gokul Manivannan](https://github.com/Gokul26092004)
