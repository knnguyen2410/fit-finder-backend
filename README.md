# Fit Finder API

## Table of Contents
- [Description](#description)
  - [Features](#features)
- [Technologies](#technologies)
    - [Installation Instructions](#installation-instructions)
- [Development Process](#development-process)
  - [Project Management](#project-management)
  - [Entity Relationship Diagram](#entity-relationship-diagram)
  - [User Stories](#user-stories)
  - [Endpoints](#endpoints)
  - [Behaviour-Driven Development](#behaviour-driven-development)
  - [Postman](#postman)
- [Hurdles](#hurdles)
- [Credits](#credits)
  - [Sources](#sources)
  - [Acknowledgments](#acknowledgments)

---

# Description
Fit Finder aims to improve users' health by enhancing their fitness journey. This application serves to meet the needs of two user groups - gym goers and gym owners.
Both groups can use Fit Finder to connect, find specific facilities and services, and grow their community.
* Users can view gyms, as well as the equipment and amenities the gyms offer.
* Gym owners can register for an account, allowing them to list their gyms and gym offerings.

As a full-stack Java application, Fit Finder comprises an Angular frontend and Spring Boot API backend. The Fit Finder API allows users to access and manage account and gym data. 
To see the frontend in action, check out the [Fit Finder Angular frontend](https://github.com/knnguyen2410/fit-finder-frontend) with which it communicates.

## Features

This Java REST API contains the following features:

* Persists at four models.
* Environment settings are set up using Spring Profiles.
* API endpoints can perform full CRUD tasks based on the business use-case.
* Individually tested endpoints.
* CRUD routes were built based on rest conventions to be exposed as part of the API.
* Security and authentication layer for private endpoints.
* Gracefully handles exceptions when they occur.
* In the event that an exception occurs, sends appropriate error messages back to the user.
* Conforms to the MVC design pattern, having separate models, controllers, and services.

---

# Technologies

Below is a comprehensive list of technologies used during Fit Finder API's development.

- **Java 17**: The programming language used for developing this application.
- **Cucumber 6.8.1**: The testing tool used for behavior-driven development, used with Rest Assured.
- **Spring 2.7.8**: The framework for creating this web application.
  - **Tomcat** The server the Spring application runs on.
- **[Spring Initializer](https://start.spring.io/)**: The application used to boostrap the project structure.
- **Maven**: The build tool used to source dependencies.
- **IntelliJ IDEA**: This is the IDE (integrated development environment) used to create the application.
    - Build System: IntelliJ
    - JDK: corretto-17, Amazon Correto version 17.0.6
- **H2 Database**: The relational database management system used to view and manage data as modified by the API.
- **Postman**: Used to test endpoints and debug the API.
- **Git**: Used for version control on local computer and pushing changes to remote repository.
- **GitHub**: The hosting service for the remote repository, used for version control and branch management.
    - **[Github Projects](https://github.com/users/knnguyen2410/projects/3/views/3)**: Used for project planning and documentation of deliverables and timelines.
- **Google Chrome**: The browser used for accessing the H2 database engine.
- **Google Docs**: The document editor used to take notes and ideas during development process.
- **Chat GPT**: The artificial intelligence chatbot developed by OpenAI.
- **[dbdiagram ERD Tool](https://dbdiagram.io/d/64777d42722eb7749428ec9f)**: Used to create the entity relationship diagram (ERD) during the planning process.
- **[Markdown Table Generator](https://www.tablesgenerator.com/markdown_tables)**: Used to document the REST API endpoints.

## Installation Instructions

The following dependencies were installed, sourced from Maven.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-rest</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
        <version>3.0.6</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>${cucumber.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>${cucumber.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-spring</artifactId>
        <version>${cucumber.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>4.3.0</version>
        <scope>test</scope>
    </dependency>
    <!-- Dependencies for Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>
    <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

Cross-Origin Resource Sharing (CORS) is used to communicate between this Spring Boot application and the Angular frontend. [[code source](https://git.generalassemb.ly/java-interapt-3-13-2023/project-04)]

```java
 package com.example.demo;
 
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.CorsRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

 @Configuration
 public class CorsConfig {
     @Bean
     public WebMvcConfigurer corsConfigurer() {
 	return new WebMvcConfigurer() {
 	    @Override
 	    public void addCorsMappings(CorsRegistry registry) {
 		registry.addMapping("/**")
 			.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
 			.allowedHeaders("*")
 			.allowedOrigins("*");
 	    }
 	};
     }
 }
```

---

# Development Process

The Fit Finder API was developed before the Fit Finder Angular frontend application. In developing the Fit Finder API, a thoughtful approach was taken to ensure a well-structured and functional application. The API caters to two distinct user groups: gym goers, who can access public data, and gym owners, who require authentication to access private data.

The development process began by considering the user stories for both user groups. Understanding the needs and requirements of each group was crucial in designing a comprehensive API. Once the user stories were defined, the focus shifted to conceptualizing the models that form the foundation of the API. Models such as owners, gym, equipment, and amenity were identified, along with their respective properties. To establish the relationships between these models, an Entity Relationship Diagram (ERD) was constructed.

With a clear understanding of the user stories and a well-defined ERD, the next step involved designing the API endpoints. Each endpoint was crafted to fulfill a specific user story and provide the necessary functionality. Public endpoints were created and tested first. Then the security layer was implemented, and the private endpoints were created and tested. This meticulous planning ensured that all aspects of the API were covered.

To maintain a high level of confidence in the API's functionality, a Behavior-Driven Development (BDD) approach was adopted using Cucumber with Rest Assured. This approach allowed for the creation of scenarios that represented various use cases. Step definitions were then implemented to handle each step in the scenarios. Accompanying the step definitions, services and controllers were developed to fulfill the requirements outlined in the tests. This systematic approach ensured that each endpoint was thoroughly tested individually, guaranteeing that they functioned as intended.

By following this well-structured development process, Fit Finder API was built with careful consideration of user needs, proper planning of models and relationships, and thorough testing using BDD. This approach not only facilitated the creation of a robust API but also ensured that it met the expectations of both gym goers and gym owners.

## Project Management

[Github Projects](https://github.com/users/knnguyen2410/projects/3/views/3) was used for both frontend and backend components.

![kanban.png](assets%2Fkanban.png)
A **kanban board** was used to exercise agile methodology.
Tasks were broken down and prioritized by which ones would contribute directly to the minimum viable product.
By implementing an iterative approach to the software development cycle, deliverables were able to continually be rolled out.

![roadmap.png](assets%2Froadmap.png)
A **roadmap** was used to visualize the deliverables and timelines. 
By creating a schedule, tasks were strategically organized to maximize time efficiency. 
Assigning milestones to strict deadlines allowed for proper time management, task ownership, and a sense of accomplishment each day. 

## Entity Relationship Diagram
![erd.png](assets%2Ferd.png)
Link to ERD here: [Dbdiagram.io](https://dbdiagram.io/d/64777d42722eb7749428ec9f)

The application consists of 4 models:
* Owner - represents the data from accounts created by the gym owner user group
* Gym - represents the data of a fitness gym belonging to a gym owner
* Equipment - represents the data of equipment belonging to a fitness gym
* Amenity - represents the data of an amenity belonging to a fitness gym

The Owner and Gym models have a one-to-many relationship:
* One owner can have many gyms
* Many gyms can belong to one owner

The Gym and Equipment models have a one-to-many relationship:
* One gym can have many pieces of equipment
* Many pieces of equipment can belong to one gym

The Gym and Amenity models have a one-to-many relationship:
* One gym can have many amenities
* Many amenities can belong to one gym

## User Stories

Below are the user stories for each model in the application:

Owner
- As an unregistered owner, I want to create an account, so that I can list my gyms.
- As a registered owner, I want to log into my account, so that I can manage my account and gyms.
- As an unregistered owner, I want to view all gym owners, so that I can see who has created an account.
- As a registered owner, I want to view my account, so that I can see my information.
- As a registered owner, I want to update my account details, so that my information is up-to-date.
- As a registered owner, I want to delete my account when I no longer need it, so that my details are unavailable to the public.
- As a user, I want to view all gyms belonging to an owner, so that I can see the gyms the owner has.

Gym
- As a registered owner, I want to list a gym I own, so that my gym details are available to the public.
- As a user, I want to view all gyms, so that I can see a list of gyms available
- As a user, I want to see the details of a specific gym, so that I can see what the gym offers.
- As a registered owner, I want to update the details for a specific gym I own, so that the information is up-to-date.
- As a registered owner, I want to remove a gym I own from the listing, so that its information is no longer available to the public.

Equipment
- As a registered owner, I want to add a a piece of equipment to a gym I own, so that the public knows the gym has this equipment.
- As an unregistered user, I want to see all equipment, so that I can see what equipment is available.
- As a user, I want to see all equipment a gym offers, so that I can compare equipment between gyms.
- As a registered owner, I want to update a piece of equipment for a gym I own, so that the information is up-to-date.
- As a registered owner, I want to unlist a piece of equipment for a gym I own, so that the public knows the equipment is no longer available.

Amenity
- As a registered owner, I want to add an amenity to a gym I own, so that the public knows the gym has this amenity.
- As an unregistered user, I want to see all amenities, so that I can see what amenities are available.
- As a user, I want to see all amenities a gym offers, so that I can compare equipment between gyms.
- As a registered owner, I want to update an amenity for a gym I own, so that the information is up-to-date.
- As a registered owner, I want to unlist an amenity for a gym I own, so that the public knows the amenity is no longer available.

## Endpoints

The Fit Finder API provides 22 unique CRUD endpoints, allowing for comprehensive data management and retrieval. 

| Request Type | URL                                       | Functionality                                | Access  |
|--------------|-------------------------------------------|----------------------------------------------|---------|
| Owner        |                                           |                                              |         |
| POST         | /api/owners/register                      | Creates owner account                        | Public  |
| POST         | /api/owners/login                         | Logs into owner account                      | Public  |
| GET          | /api/owners                               | Returns all owners                           | Public  |
| GET          | /api/owners/{ownerId}                     | Returns owner account details                | Private |
| PUT          | /api/owners/{ownerId}                     | Updates owner account details                | Private |
| DELETE       | /api/owners/{ownerId}                     | Deletes owner account                        | Private |
| GET          | /api/owners/{ownerId}/gyms                | Returns all gyms belonging to owner account  | Public  |
| Gym          |                                           |                                              |         |
| POST         | /api/gyms                                 | Creates a gym belonging to owner             | Private |
| GET          | /api/gyms                                 | Returns all gyms                             | Public  |
| GET          | /api/gyms/{gymId}                         | Returns gym details                          | Public  |
| PUT          | /api/gyms/{gymId}                         | Updates gym details                          | Private |
| DELETE       | /api/gyms/{gymId}                         | Deletes gym                                  | Private |
| Equipment    |                                           |                                              |         |
| POST         | /api/gyms/{gymId}/equipment               | Creates a piece of equipment for gym         | Private |
| GET          | /api/equipment                            | Returns all equipment                        | Public  |
| GET          | /api/gyms/{gymId}/equipment               | Returns all equipment for gym                | Public  |
| PUT          | /api/gyms/{gymId}/equipment/{equipmentId} | Updates a piece of equipment details for gym | Private |
| DELETE       | /api/gyms/{gymId}/equipment{equipmentId}  | Deletes a piece of equipment for gym         | Private |
| Amenity      |                                           |                                              |         |
| POST         | /api/gyms/{gymId}/amenities               | Creates an amenity for gym                   | Private |
| GET          | /api/amenities                            | Returns all amenities                        | Public  |
| GET          | /api/gyms/{gymId}/amenities               | Returns all amenities for gym                | Public  |
| PUT          | /api/gyms/{gymId}/amenities/{amenityId}   | Updates amenity details for gym              | Private |
| DELETE       | /api/gyms/{gymId}/amenities{amenityId}    | Deletes an amenity for gym                   | Private |

## Behaviour-Driven Development

Behaviour-Driven Development and proper testing convention was instrumental in the creation of Fit Finder API.

* User stories were grouped into scenarios, and Cucumber tests were written in Gherkin for each scenario's step definitions.
* After each step definition was written, the test would fail successfully. 
* The business logic for each test was then written in the service, and the controller method was written to allow a CRUD endpoint to access data from its respective repository. 
* The test would then pass successfully, and the next step definition would be written. 

This iterative development process ensured each user story was properly addressed, as well as ensuring methods were properly returning data and exceptions.

![testing-cucumber.png](assets%2Ftesting-cucumber.png)

## Postman

Leveraging Postman Workspaces allowed for efficient testing of individual endpoints and exception handling. Below are all the endpoints tested in Postman, as well as snapshots of the exceptions handled at each request.

| Owner | Gym | Equipment | Amenity |
|:----:|:--:| :--: |:------:|
|![postman-owner.png](assets%2Fpostman-owner.png)|![postman-gym.png](assets%2Fpostman-gym.png)|![postman-equipment.png](assets%2Fpostman-equipment.png)|![postman-amenity.png](assets%2Fpostman-amenity.png)|

---

# Hurdles

During the development process, a significant hurdle arose when trying to obtain the desired output in the JSON object when testing the endpoints in Postman. Initially, the models had the @JsonIgnore annotation applied to certain class data, which resulted in the desired data being excluded from the JSON response. However, as the development progressed, there arose a need to access this data.

Removing the @JsonIgnore annotation seemed like a straightforward solution, but it led to another challenge. The model relationships were configured in a way that caused a stack overflow issue. Recognizing the need for a different approach, guidance from instructors proved invaluable. They suggested utilizing the @JsonIgnoreProperties annotation instead.

By using the @JsonIgnoreProperties annotation, the hurdle was overcome, and the desired data could be accessed in the JSON response. This adjustment set the stage for success in later stages of the development process, particularly when integrating the API with the frontend Angular application. The data that was once excluded from the JSON object could now be displayed effectively on the frontend, enhancing the overall user experience.

This hurdle served as a valuable learning experience, highlighting the importance of adapting and seeking guidance when faced with challenges during development. By finding alternative solutions and leveraging the expertise of instructors, a more robust and functional API was created.

---

# Credits

## Sources

- [JAVA API](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)
- [Spring Framework Documentation](https://docs.spring.io/spring-framework/reference/)
- [Angular Documentation](https://angular.io/docs)
- [Stack Overflow Solution](https://stackoverflow.com/questions/43794721/spring-boot-h2-console-throws-403-with-spring-security-1-5-2) - Accessing H2 Database with spring security implemented. This code snippet was included in the SecurityConfiguration class.

      ```java
          @Bean
          public WebSecurityCustomizer webSecurityCustomizer() {
              return (web) -> web.ignoring().antMatchers("/h2-console/**");
          }
      ```

## Acknowledgments

I have immense gratitude to my instructors and peers who have taught me so much during this development process. Please see their GitHub accounts below:
- [Leonardo Rodriguez](https://github.com/LRodriguez92)
- [Suresh Sigera](https://github.com/sureshmelvinsigera)
- [Maksym Zinchenko](https://github.com/maklaut007)
- [Kevin Barrios](https://github.com/dayjyun)
- [Jaime Padilla](https://github.com/Jaypad07)
- [Lorena Rojas](https://github.com/lrojas4)
- [Obinna Umerah](https://github.com/ObinnaUmerah)

[Go to top](#fit-finder-api)
