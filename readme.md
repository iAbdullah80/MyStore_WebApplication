# myStore-api

MyStore API is a Spring Boot-based e-commerce server that handles user authentication, product management, orders, invoices, and integrates with the Stripe payment gateway. It uses JWT for securing endpoints and handling user sessions.

## Features

- **User Authentication:** Handles user signup, login, JWT tokens and session management.
- **Product Management:** Admin can manage products, including adding and deleting them.
- **Order Processing:** Manages order creation and integrates with Stripe for payment processing.
- **Invoice Management:** Generates invoices for orders and allows users to retrieve their invoices.
- **Admin functionalities**:** Admin can manage users, products, and orders.

## Technologies Used

- Spring Boot
- Spring Security
- JSON Web Tokens (JWT)
- JPA / Hibernate
- MySQL
- Stripe API

## Prerequisites
- **Java 11+**
- **Maven 3.6+**
- **MySQL Database**
- **Stripe Account and API Keys**
- **OpenSSL (for generating RSA keys)**
- **Stripe CLI (for testing webhooks)**

## Setup

1. Clone the repository
```bash
   git clone https://github.com/iAbdullah80/MyStore_WebApplication.git
   cd MyStore_WebApplication
   ```
2. Configure your MySQL database in `application.properties`
3. Set up your Stripe API key and webhook secret in `secrets.properties`
4. Generate RSA key pair for JWT signing (instructions below)
5. Run the application

### Generating RSA Keys

Generate a public/private key pair for JWT signing:

```
openssl genrsa -out keypair.pem 2048
openssl rsa -in keypair.pem -pubout -out public.pem
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
```

Add the keys to your `application.properties`:

```
rsa.private-key=classpath:certs/private.pem
rsa.public-key=classpath:certs/public.pem
```


## Running the Application

Run the application using:

```
./mvnw spring-boot:run
```

or build and run the JAR:

```
./mvnw clean package
java -jar target/mystore-api.jar
```

**or run the application using Docker:**

Build the Docker images using the provided Dockerfile.
```
docker build -t spring-server .
```
Configure Environment Variables in the `docker-compose.yml` file and run the application using Docker Compose.
```
docker-compose up --build
```

Remember to set up all necessary environment variables and configuration files before running the application.