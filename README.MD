# eCommerce Project

## Overview
This project is a demo eCommerce application developed as part of a technical assessment for a Java Developer position. It demonstrates my ability to implement key features of an eCommerce system using Java, Spring Boot, Docker and other related technologies.

## Features
- **Product Management**: CRUD operations for managing product data.
- **Brand Management**: CRUD operations for managing brands.
- **Pricing Service**: Dynamic price calculation based on product, brand, and date.
- **Data Persistence**: Utilizes JPA with an H2 in-memory database for data storage.
- **REST API**: Exposes endpoints for interacting with the application’s features.

## Project Structure
The project follows a standard Maven structure:
- `src/main/java`: Contains the Java source code.
    - `controller`: REST controllers for handling HTTP requests.
    - `service`: Business logic layer.
    - `repository`: Data access layer using Spring Data JPA.
    - `model`: Entity classes representing the data model.
- `src/main/resources`: Configuration files and application properties.

## API Documentation
The API is documented and can be explored via **Swagger UI**.

Once the application is running, access the Swagger UI at:
`http://localhost:8080/swagger-ui.html`

## API Endpoints
- **GET /products: Retrieve all products.**
- **POST /products: Add a new product.**
- **GET /brands: Retrieve all brands.**
- **POST /brands: Add a new brand.**
- **GET /prices: Get price based on product, brand, and date.**

## Technology Stack
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Swagger** for API documentation
- **Docker** for containerization
- **Maven** for build automation

## Installation and Running
1. **Clone the repository**:
   ```bash
   git clone https://github.com/Alexei7ca/ecommerceApplication.git
2. **Navigate to the project directory:**
   ```bash
   cd ecommerce
3. **Build the project:**
   ```bash
   mvn clean install
4. **Run the application:**
   ```bash
   mvn spring-boot:run

## Running with Docker
### Prerequisites:
Make sure Docker is installed and running on your machine. [Install Docker](https://docs.docker.com/get-docker/) if you haven't already.

1. **Build the Docker Image**:
   ```bash
   docker build -t ecommerceapplication .
2. **Run the Docker Container:**
   ```bash
   docker run -p 8080:8080 ecommerceapplication
3. **Access Swagger UI:** Once the container is running, you can access the API documentation and test it using Swagger UI at:
   ```bash
   http://localhost:8080/swagger-ui.html

## Testing 
You can test the application by sending requests to the endpoints listed above or by using the Swagger UI.
