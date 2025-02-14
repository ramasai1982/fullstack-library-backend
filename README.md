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
