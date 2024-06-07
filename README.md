# ecommerce-springboot-microservices
Ecommerce microservices created using Spring boot, spring cloud, spring security and database: mongodb and mysql.

Here is a breakdown of each component:

1. **User Service**
   - **Port:** 8000
   - **Database:** MySQL
   - **Schema:**
     - Long id
     - String name
     - String email
     - String phoneNumber
     - String password
   - **APIs:**
     - GET /users
     - POST /users
     - GET /users/{id}
     - PUT /users/{id}
     - DELETE /users/{id}

2. **Product Service**
   - **Port:** 8100
   - **Database:** MongoDB
   - **Schema:**
     - String id
     - String key
     - String name
     - String description
     - double price
     - String quantityOnStock
   - **APIs:**
     - GET /products
     - POST /products
     - GET /products/{id}
     - PUT /products/{id}
     - DELETE /products/{id}

3. **Order Service**
   - **Port:** 8200
   - **Database:** MongoDB
   - **Schema:**
     - String id
     - LocalDate dateCreated
     - String status
     - List<Product> products
     - User user
   - **APIs:**
     - GET /orders
     - POST /orders
     - GET /orders/{id}
     - PATCH /orders/updateStatus/{id}
     - POST /orders/cancel/{id}

4. **Service Registry**
   - **Port:** 8761
   - **Technology:** Eureka

5. **Gateway Service**
   - **Port:** 8765
   - **Technology:** Spring Cloud API Gateway

6. **Common Services**
   - MySQL (docker container: 3306)
   - MongoDB (docker container: 27017)
   - Zipkin (docker container: 9411)

The architecture uses Eureka for service discovery and a Spring Cloud API Gateway for routing requests to the appropriate microservices. The services interact with different databases, with User Service using MySQL and Product and Order Services using MongoDB. Common services include the databases and Zipkin for distributed tracing.

Keywords: springboot-microservices ecommerce-micrservices springboot-ecommerce-microservices
