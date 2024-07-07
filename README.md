**Exercise 1 and 2**
The solutions for Exercise 1 and 2 are in .txt folder called Exercise _1 _and_ 2.txt
**Stock Application**
This project is a Java-based backend application developed with Spring Boot located in the src folder . It provides a RESTful API for managing stocks, offering endpoints to perform CRUD (Create, Read, Update) operations. The application stores the list of stocks in memory, allowing for efficient management and retrieval of stock information.

**Endpoints**
POST /api/stock/create: Creates a new stock. Provide a JSON object representing the new stock data in the request body.
PUT /api/stock/update-stock/{id}: Updates the price of a specific stock. Replace {id} with the actual stock ID. Include the updated price in the request body.
GET /api/stock/get-stock/{id}: Retrieves details of a specific stock by its ID. Replace {id} with the actual stock ID.
GET /api/stock/get-all-stocks: Retrieves a list of all available stocks.

**Technologies Used**
Java
Springboot
Postgresql

**Postman Collection**
A Postman collection with pre-configured requests is available for testing the API. You can use the url below to access the documentation.
https://documenter.getpostman.com/view/28338485/2sA3e1Bpq5
