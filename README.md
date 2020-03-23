# TwitterWS
Build Restful CRUD API for a simple Twitter application using Spring Boot, H2, JPA and Hibernate.

## Requirements

Java 11.x

Maven - 3.x.x

## Steps to Setup

1. Clone the application

git clone 

2. Build and run the app using maven

The app will start running at http://localhost:8080.

## Swagger

Swagger documentation can be found at http://localhost:8080/swagger-ui.html


## Explore Rest APIs

The app defines following CRUD APIs:

  POST /tweet/create

  GET /tweet

  GET /tweet/validated

  GET /tweet/mostUsedHashtags

  POST /tweet/validate

You can test them using postman or any other rest client as well as using swagger.

## Default users
  name, username, location, followers
  
  'María', 'mariaperez', 'Madrid', 1500
  
  'John', 'johndoe', 'Madrid', 5
  
  'Juan', 'juancastro', 'Barcelona', 3000
  
They can be changed modifying the data.sql file inside resources.
