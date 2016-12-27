# Welcome to the Folo demo project
This is the back-end implementation of a simple social application.
user can send messages to other users or share photos with them.

## Key points
- ```Spring-boot``` framework for Java web applications
- Use PostgreSQL for production quality database
- Breaks down the server side program into different layers (web/service/dao/entity)
    - The "Web layer" contains the Controller objects that defines the RESTful API and use service layers to handle the calls.
    - The "Service layer" presents the Java APIs that can be consumed by other service object as well as the "web layer". 
    - The "DAO (Data access object) layer" interact with database tables. The SQL queries only present in this layer.
    - The Entity layer defines the classes that can be mapped into database tables.
- Project source code layout that suitable for large server-side Java applications.
- Use ```Optional``` to avoid direct null checking (Java 8 feature)
- Use ```@Autowired``` for dependency injection in the source code. It is used mainly in web layer and service layer.

## Notice
code like the following has no runtime penalty, as they will be concatenated into one string during compile time. 
``` java
final String sql = "SELECT * from users " +
        "WHERE id = 10 " +
        "ORDER BY id ASC";
```
## Pre requisities
- Java 8
- Spring-boot framework
- Maven 3.1.x or above
- Postgresql instance

## Database schema and data sample Data
schema and data are initialized using ```schema.sql``` and ```data.sql```
The files are located in ```src/main/resources```.



## Run Application Locally
```mvn spring-boot:run```

## Invoke Application
### Add a user
``` commandline
curl -X POST -H "Content-Type: application/json" \
         -d '{"first_name": "jiang", "last_name":"Zhang", "email":"zh_jiang@hotmail.com", "password = "pwd"}' \
         "http://localhost:8080/users"
```
running the above POST request will return 200 Ok HTTP response

### List All Users
```curl "http://localhost:9095/user"```
running the above GET request will list all users in json format.

