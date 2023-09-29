# README

### 1. Overview
Transactions Routine micro-service for managing Accounts, Operations and Transactions. Developed using:
1. Spring Boot 3.1.4
2. Gradle 8.2.1
3. JDK 17
4. Embedded Tomcat server / Docker
5. Embedded H2 database

### 2. Requirements
For building and running the application you need:
* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Docker](https://docs.docker.com/desktop/install/mac-install/) (Optional)

### 3. To build the application
Run the following command for building the application:
```shell
./gradlew build
```
### 4. To run the application
With embedded Tomcat Server:
```shell
./gradlew bootRun
```
OR

With Docker:
```shell
docker build -t transactions-routine:latest .
```
```shell
docker run -p 8080:8080 transactions-routine:latest
```
### 5. To run the test cases
```shell
./gradlew test
```

### 6. Accessing the H2 console

1. Go to: http://localhost:8080/h2-console
2. Update JDBC URL to:
   ```
   jdbc:h2:mem:testdb
   ```
3. Default Username: sa and Password: (blank)

### 7. REST API Documentation

#### 1. Get Account
API to retrieve the Account information.
##### Path
```shell
GET /accounts/{accountId}
```
##### Path Parameters
Name | Type
--- | ---
accountId | Long
##### Response Status Codes
Code | Description
--- | ---
200 | Ok
404 | AccountId does not exist
##### Response Body
```shell
{
  "accountId": Integer,
  "documentNumber": String
}
```
##### Sample Request
```shell
curl --location 'localhost:8080/accounts/1'
```
##### Sample Response
``` shell
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 29 Sep 2023 11:54:54 GMT

{"accountId":1,"documentNumber":"12345"}
```

#### 2. Create a new Account
API to create a new Account.
##### Path
```shell
POST /accounts
```
##### Request Body Parameters
Name | Type
--- | ---
document_number | String
##### Response Status Codes
Code | Description
--- | ---
200 | Ok: Account created successfully.
400 | Bad Request: document_number is null or invalid.
##### Sample Request
```shell
curl --location 'localhost:8080/accounts' \
--header 'Content-Type: application/json' \
--data '{
    "document_number": "12345"
}'
```
##### Sample Response
``` shell
HTTP/1.1 200 
Content-Length: 0
Date: Fri, 29 Sep 2023 11:58:37 GMT
```

#### 3. Create a new Transaction
API to create a new transaction.
##### Path
```shell
POST /transactions
```
##### Request Body Parameters
Name | Type
--- | ---
account_id | Long
operation_type_id | Integer
amount | Double
##### Response Status Codes
Code | Description
--- | ---
200 | Ok: Transaction created successfully.
400 | Bad Request: account_id or operation_type_id or amount is null or invalid.
404 | Not Found: account_id or operation_type_id does not exist.
##### Sample Request
```shell
curl -v --location 'localhost:8080/transactions' \
--header 'Content-Type: application/json' \
--data '{
    "account_id": 1,
    "operation_type_id": 1,
    "amount": 123.45
}'
```
##### Sample Response
``` shell
HTTP/1.1 200 
Content-Length: 0
Date: Fri, 29 Sep 2023 12:00:20 GMT
```
