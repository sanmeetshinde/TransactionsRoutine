# README

### Overview
Transactions Routine micro-service for managing Accounts, Operations and Transactions. Developed using:
1. Spring Boot 3.1.4
2. Gradle 8.2.1
3. JDK17
4. Embedded Tomcat server / Docker
5. Embedded H2 database

### Requirements
For building and running the application you need:
* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Gradle 8.2.1](https://gradle.org/releases/)
* [Docker](https://docs.docker.com/desktop/install/mac-install/)

### Running the application locally

1. Run the following command for building the service:
```shell
./gradlew build
```
2. Run the following command for starting the service on the embedded Tomcat server:
```shell
./gradlew bootRun
```
3. Run the following command for running the test cases:
```shell
./gradlew test
```
4. Run the following command to build the Docker image:
```
docker build -t transactions-routine:latest .
```
5. Run the following command to start the application:
```
docker run -p 8080:8080 transactions-routine:latest
```

### REST API

#### Get Account
```
GET /accounts/{accountId}
```
Request
```
curl --location 'localhost:8080/accounts/1'
```
Response
``` 
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 29 Sep 2023 11:54:54 GMT

{"accountId":1,"documentNumber":"12345","transactions":[]}
```

#### Create a new Account
```
POST /accounts
```
Request
```
curl --location 'localhost:8080/accounts' \
--header 'Content-Type: application/json' \
--data '{
    "document_number": "12345"
}'
```
Response
``` 
HTTP/1.1 200 
Content-Length: 0
Date: Fri, 29 Sep 2023 11:58:37 GMT
```

#### Create a new Transaction
```
POST /transactions
```
Request
```
curl -v --location 'localhost:8080/transactions' \
--header 'Content-Type: application/json' \
--data '{
    "account_id": 1,
    "operation_type_id": 1,
    "amount": 123.45
}'
```
Response
``` 
HTTP/1.1 200 
Content-Length: 0
Date: Fri, 29 Sep 2023 12:00:20 GMT
```

### Accessing the H2 console

1. Go to: http://localhost:8080/h2-console
2. Update JDBC URL to: jdbc:h2:mem:testdb
3. Default Username: sa and Password: (blank)