# ER Diagram – AI E-Commerce Platform

## Entity-Relationship Diagram

```mermaid
erDiagram
    USERS {
        BIGINT id PK
        VARCHAR name
        VARCHAR email UK
        VARCHAR password
    }

    ROLES {
        BIGINT id PK
        VARCHAR name
    }

    USER_ROLES {
        BIGINT user_id FK
        BIGINT role_id FK
    }

    CATEGORY {
        BIGINT id PK
        VARCHAR name
    }

    PRODUCT {
        BIGINT id PK
        VARCHAR name
        VARCHAR description
        DOUBLE price
        DOUBLE rating
        BIGINT category_id FK
    }

    CART {
        BIGINT id PK
        BIGINT user_id FK
    }

    CART_ITEM {
        BIGINT id PK
        BIGINT cart_id FK
        BIGINT product_id FK
        INT quantity
    }

    ORDERS {
        BIGINT id PK
        BIGINT user_id FK
        DOUBLE total_amount
        VARCHAR status
        DATETIME order_date
    }

    ORDER_ITEMS {
        BIGINT id PK
        BIGINT order_id FK
        BIGINT product_id FK
        VARCHAR product_name
        INT quantity
        DOUBLE price
    }

    REVIEW {
        BIGINT id PK
        INT rating
        VARCHAR comment
        DATETIME created_at
        BIGINT product_id FK
        BIGINT user_id FK
    }

    USERS ||--o{ USER_ROLES : "has"
    ROLES ||--o{ USER_ROLES : "assigned to"
    USERS ||--o{ CART : "owns"
    USERS ||--o{ ORDERS : "places"
    USERS ||--o{ REVIEW : "writes"
    CATEGORY ||--o{ PRODUCT : "contains"
    PRODUCT ||--o{ REVIEW : "receives"
    CART ||--o{ CART_ITEM : "has"
    ORDERS ||--o{ ORDER_ITEMS : "contains"
```

---

## Table Descriptions

| Table         | Description                                    |
|---------------|------------------------------------------------|
| `users`       | Stores user accounts (name, email, password)   |
| `roles`       | Defines roles (ADMIN, USER)                    |
| `user_roles`  | Many-to-many join table for users and roles    |
| `category`    | Product categories                             |
| `product`     | Products with name, description, price, rating |
| `cart`        | One cart per user                               |
| `cart_item`   | Items in a cart with quantity                   |
| `orders`      | Customer orders with total and status           |
| `order_items` | Individual items within an order                |
| `review`      | Product reviews with rating and comment         |

---

## Key Relationships

- **User ↔ Role**: Many-to-Many (via `user_roles`)
- **User → Cart**: One-to-One (each user has one cart)
- **User → Order**: One-to-Many (user places multiple orders)
- **User → Review**: One-to-Many (user writes multiple reviews)
- **Category → Product**: One-to-Many (category contains products)
- **Product → Review**: One-to-Many (product receives reviews)
- **Cart → CartItem**: One-to-Many (cart has items)
- **Order → OrderItem**: One-to-Many (order contains items)
