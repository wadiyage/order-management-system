# Order Management System (Spring Boot REST API)

## 📌 Overview
The **Order Management System** is a backend RESTful API built using **Spring Boot** that manages customers, products, and orders for a retail-style application. The system demonstrates clean layered architecture, real-world business logic, and modern backend development practices.

This project was developed as a **portfolio and internship-ready project** to showcase Java backend development skills.

---

## 🏗️ Architecture
The application follows **Layered Architecture**:

- **Controller Layer** – Handles HTTP requests and responses
- **Service Layer** – Contains business logic
- **Repository Layer** – Manages database access using Spring Data JPA
- **DTO Layer** – Handles data transfer and validation
- **Exception Handling** – Centralized global exception handling

---

## 🚀 Features

### Customer Management
- Create, update, retrieve, and delete customers
- Input validation using Bean Validation

### Product Management
- Manage products with price and stock quantity
- Inventory tracking with automatic stock reduction

### Order Management
- Create orders linked to customers
- Add multiple products per order
- Automatic total amount calculation
- Stock availability validation
- Order date auto-generation

### API Documentation
- Interactive API documentation using **Swagger UI (OpenAPI 3)**

---

## 🛠️ Technologies Used

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven

### Database
- MySQL

### API & Testing
- RESTful APIs
- Swagger UI
- Postman

### Tools
- IntelliJ IDEA
- Git & GitHub

---

## 📂 Project Structure

```
com.icet.ordermanagementsystem
│
├── controller
├── service
├── repository
├── model
├── dto
├── exception
├── config
└── OrderManagementSystemApplication.java
```

---

## ⚙️ Setup & Configuration

### Prerequisites
- Java 17+
- Maven
- MySQL

### Database Configuration
Create the database:
```sql
CREATE DATABASE OrderManagementDB;
```

Configure `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/OrderManagementDB
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}
```

> **Note:** Database password is stored securely using environment variables or `.env` file.

---

## ▶️ Running the Application

1. Clone the repository
2. Configure database credentials
3. Run the application
4. Access Swagger UI:
```
http://localhost:8000/swagger-ui/index.html
```

---

## 🧪 API Testing

- APIs can be tested using **Swagger UI** or **Postman**
- Sample endpoints:
  - `GET /api/customers`
  - `POST /api/products`
  - `POST /api/orders`

---

## 📈 Learning Outcomes

- RESTful API design
- Layered architecture implementation
- JPA entity relationships
- Business logic handling
- Exception handling best practices
- API documentation with Swagger

---

## 👨‍💻 Author

**Sandaruwan Wadiyage**  
Graduate Software Engineer (Java | Backend)

- GitHub: https://github.com/wadiyage
- LinkedIn: https://linkedin.com/in/sandaruwan-wadiyage

---

## 📌 Status
🚧 **Project is actively under development** – additional features and improvements are planned.

