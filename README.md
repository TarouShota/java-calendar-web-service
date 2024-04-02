# Spring RESTful API with H2 Database

This project is a backend service built using Spring Boot to store event information in an H2 database.
The service is hosted on AWS Elastic Beanstalk. 

## Dependencies
- Springdoc OpenAPI Starter (version 2.4.0)
- Spring Boot Starter HATEOAS
- Spring Boot Starter
- H2 Database
- Spring Boot Starter Web
- Spring Boot Starter Data JPA

## Technologies Used
- Gradle
- Java 17
- Spring Boot

## Getting Started
To run this project locally, follow these steps:
1. Clone the repository using `git clone https://github.com/your-username/your-repo.git`
2. Navigate to the project directory
3. Run the project using `./gradlew bootRun`

## Endpoints
The API provides the following endpoints:
- GET /events - To retrieve all events
- GET /events?start_time&?end_time - To retrive events in a given range
- GET /events/{id} - To retrive a event by ID
- POST /events - To create a new event
- DELETE /events/{id} - To delete an event by ID
