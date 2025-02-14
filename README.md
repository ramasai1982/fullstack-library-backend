# fullstack-library-backend

Backend - Spring Boot

 1. Prerequisites
Make sure you have the following installed:

Java 17+ (Recommended: OpenJDK 17)
Maven (Ensure it's installed and available in the PATH)
H2 Database (in-memory)
Git (optional)

 2. Clone the Repository

git clone https://github.com/ramasai1982/fullstack-library-backend.git

cd fullstack-library-backend/library-backend

 3. Configure the Application
 
Modify src/main/resources/application.properties if needed:
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:librarydb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# CORS Configuration
server.port=8080

4. Run the Backend

choco install maven -y (optional - to install maven)
mvn spring-boot:run

API will be available at: http://localhost:8080/books

5. Access H2 Database (Optional)

Open a browser and go to: http://localhost:8080/h2-console

Use the following settings:
	JDBC URL: jdbc:h2:mem:librarydb
	Username: sa
	Password: (leave blank)
 
6. Testing

Run Unit & Integration Tests: mvn test
	
Frontend - Angular

7. Manual Testing via Postman or cURL

7.1. Get All Books

curl -X GET http://localhost:8080/books

7.2. Get a Book by ID

curl -X GET http://localhost:8080/books/1

7.3. Add a New Book

curl -X POST http://localhost:8080/books -H "Content-Type: application/json" -d '{
  "title": "Spring Boot in Action",
  "author": "Craig Walls",
  "isbn": "9781617292545",
  "publicationYear": 2016,
  "description": "A practical guide to building applications with Spring Boot."
}'

7.4. Update a Book

curl -X PUT http://localhost:8080/books/1 -H "Content-Type: application/json" -d '{
  "title": "Updated Title",
  "author": "Updated Author",
  "isbn": "9781617292545",
  "publicationYear": 2022,
  "description": "Updated description."
}'

7.5. Delete a Book

curl -X DELETE http://localhost:8080/books/1

8. Project Structure

library-backend/
│── src/
│   ├── main/
│   │   ├── java/com/example/library/library_backend
│   │   │   ├── config/         # Configuration Classes (CORS, Security, etc.)
│   │   │   │   ├── CorsConfig.java
│   │   │   ├── controller/     # REST API Controllers
│   │   │   ├── exception/      # Global Exception Handling
│   │   │   ├── model/          # Database Entities
│   │   │   ├── repo/     # Data Access Layer
│   │   │   ├── service/        # Business Logic
│   │   │   ├── LibraryBackendApplication.java
│   │   ├── resources/
│   │   │   ├── application.properties  # Spring Boot Configuration
│── pom.xml  # Project Dependencies
│── README.md
