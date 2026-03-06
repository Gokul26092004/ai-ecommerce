# Demo Script – AI-Powered E-Commerce Platform

> Follow this script step-by-step to demonstrate the platform during your final presentation.

---

## Pre-Demo Setup

1. **Start MySQL** — Ensure MySQL server is running on port 3306
2. **Start Backend**:
   ```bash
   cd backend_repo
   set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.18.8-hotspot
   .\mvnw.cmd spring-boot:run
   ```
3. **Start AI Service**:
   ```bash
   cd ai-ecommerce
   uvicorn app.main:app --reload --port 8000
   ```
4. **Start Frontend**:
   ```bash
   cd frontend_repo/frontend
   npm start
   ```
5. **Open browser** at `http://localhost:3000`

---

## Demo Flow

### Scene 1: User Registration (1 min)
1. Navigate to **Register** page
2. Fill in:
   - Name: `Demo User`
   - Email: `demo@example.com`
   - Password: `password123`
3. Click **Register**
4. **Talking Point**: "The registration data is sent to our Spring Boot backend, which hashes the password and stores it in MySQL."

### Scene 2: User Login (1 min)
1. Navigate to **Login** page
2. Enter credentials: `demo@example.com` / `password123`
3. Click **Login**
4. **Talking Point**: "Upon successful authentication, the backend generates a JWT token containing the user's ID and roles. This token is stored in localStorage and attached to all subsequent API requests."

### Scene 3: Browse Products (2 min)
1. Navigate to **Products** page
2. Show the product grid loading from the API
3. **Demonstrate Search**: Type a product name in the search box
4. **Demonstrate Category Filter**: Select a category
5. **Demonstrate Sort**: Sort by price (Low → High, High → Low)
6. **Talking Point**: "Products are fetched from the Spring Boot backend via REST API. The filtering and sorting happen client-side using React's useMemo hook for optimal performance."

### Scene 4: Add to Cart (1 min)
1. Click **Add to Cart** on a product
2. Navigate to **Cart** page
3. Show the cart items loaded from the server
4. **Demonstrate Quantity Update**: Click + and - buttons
5. **Demonstrate Remove**: Remove an item
6. **Talking Point**: "The cart is now server-side, stored in MySQL. Every action (add, update, remove) makes a real API call to the CartController."

### Scene 5: Checkout & Order Placement (1 min)
1. Click **Proceed to Checkout**
2. Enter delivery address: `123 Demo Street, Chennai`
3. Select payment method: `UPI`
4. Click **Place Order**
5. **Talking Point**: "The order is created on the backend with all cart items. The OrderController extracts the user ID from the JWT token to associate the order with the correct user."

### Scene 6: Order History (1 min)
1. Navigate to **Orders** page
2. Show the order with its items and status
3. **Talking Point**: "Users can view their complete order history, fetched from the database. Each order shows items, total amount, and current status."

### Scene 7: AI Visual Search (2 min)
1. Open a new tab and navigate to `http://localhost:8000/docs` (FastAPI Swagger UI)
2. Click on **POST /visual-search**
3. Upload a sample product image
4. Show the similar products results
5. **Talking Point**: "This is our AI-powered visual search. We use a pretrained MobileNetV2 model to extract feature vectors from images, then compute cosine similarity against our product catalog. The feature index is cached using LRU for performance."

### Scene 8: AI Endpoints (1 min)
1. In Swagger UI, demonstrate:
   - **GET /health** — Show service status
   - **POST /recommand** — Show product recommendations
   - **POST /sentiment** — Show sentiment analysis result for "This product is amazing!"
2. **Talking Point**: "The AI microservice runs independently on FastAPI, communicating with the main backend. This microservices approach allows us to scale the AI service separately."

### Scene 9: Code Quality (1 min)
1. Show terminal with backend test results: `BUILD SUCCESS`
2. Show terminal with pytest results: `4 passed`
3. Show terminal with frontend build: `Compiled successfully`
4. **Talking Point**: "All three services pass their respective test suites, ensuring reliability and code quality."

---

## Closing (1 min)
- Summarize the three-tier architecture
- Highlight the AI differentiators (Visual Search, Recommendations, Sentiment)
- Mention future enhancements (Payment gateway, Real-time tracking, Mobile app)
- Thank the audience and invite questions

---

## Total Demo Time: ~12 minutes

---

## Troubleshooting

| Issue | Fix |
|-------|-----|
| Backend won't start | Check MySQL is running, verify `application.properties` credentials |
| Frontend shows empty products | Ensure backend is running and CORS is enabled |
| AI service import error | Run `pip install -r requirements.txt` |
| JWT token expired | Re-login to get a fresh token |
| Visual search no results | Ensure `data/images/` folder has sample product images |
