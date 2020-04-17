# TwitterWS

Build Restful CRUD API for a simple microservice which consumes Tweets from Twitter and persist them in an in-memory database.

It is build with Spring Boot, JPA, Hibernate, twitter4j and an H2 in-memory database.

## Requirements

Java 11.x

Maven - 3.x.x

## Steps to Setup

1. Clone the application

git clone 

2. Build and run the app using maven

The app will start running at http://localhost:8080.

## Swagger

The app will start running at http://localhost:8080/swagger-ui.html

## Explore Rest APIs

The app defines following CRUD APIs:

  GET /tweet

  GET /tweet/validated

  GET /tweet/mostUsedHashtags

  POST /tweet/validate

You can test them using postman or any other rest client.

