# TestExercises

## SQL exercises

- MySQL

## Java exercises:

- Spring Boot
- Spring Data
- H2(in-memory)
- Spring Web MVC
- slf4j

## Running SQL queries

### SQL requests are located in the following path:

    ./TestExercises/src/main/resources/query/

## Running the Application

A WEB service (Spring MVC) was designed without View, only controllers / repository and other layers for the User
entity.

Connected logging.

### To run the application, follow these steps:

#### 1. Clone the Repository

    git clone https://github.com/pav1ov-v/TestExercises.git
    cd TestExercises/

#### 2. Run the demo-jar

    Before running the application, make sure that the local port 8080 is not in use.
    java -jar ./TestExercises/demo/TestExercises-0.0.1-SNAPSHOT.jar

#### 3. Application requests

##### 3.1 To add a new user

    POST localhost:8080/user

    Raw JSON body:

    {
    "name" : "not null or empty",
    "pass" : "not null or empty",
    "mail" : ""
    }

##### 3.2 To get a user

    GET localhost:8080/user/{id}

##### 3.3 To get all users

    GET localhost:8080/users

##### 3.4 To update a user

    PUT localhost:8080/user/{id}

    Raw JSON body:

    {
    "name" : "not null or empty",
    "pass" : "not null or empty",
    "mail" : "not null or empty"
    }

##### 3.5 To delete a user

    DELETE localhost:8080/user/{id}

