# REST Tasks Application

![Java 8](https://img.shields.io/badge/Java%20SE-8-green.svg)
![SpringBoot 2.0](https://img.shields.io/badge/Spring%20Boot-2.0-green.svg)
![Lombok](https://img.shields.io/badge/Lombok-1.16-green.svg)
![Swagger valid](https://img.shields.io/badge/Swagger-valid-green.svg)
![TDD](https://img.shields.io/badge/Coverage-85%25-green.svg)
  
  REST API and Backend service to Trello tasks app using  Gradle configuration for building, testing and running the application. A personalized task list app with the ability to export to Trello.
  
  The application is based on [Richardson maturity model](https://martinfowler.com/articles/richardsonMaturityModel.html). This is a model developed by Leonard Richardson, who describes how to design REST API systems.
  
  #### Technology stack on the server side. 

  * Java 8
  * Spring Boot 2.0
  * Spring Boot Actuator
  * Spring Boot Email
  * Spring Data JPA
  * Hibernate
  * Lombok
  * GSON
  * SQL
  
  *The application is compatible with MySQL and PostgreSQL databases.*
  
  #### Technology stack on the client side. 
  
  * HTML5
  * CSS3
  * jQuery
  * Thymeleaf

  Implementation and support for the Thymeleaf template engine, to generate Web pages on the server side.
  
 #### Testing
  
  Unit Testing and Mocking written using Mockito and JUnit. 
  
  The result of the coverage shows 85% classes coverage in code.

 Class | Method | Line
 --- | --- | ---
 85% | 80% | 74%   
  
  Selenium WebDriver and XPath were used for Web Integration Testing in Chrome and Firefox browsers.
    
  Bash scripts for automatically building and starting applications. Besides generating files like war or jar.
  
  
  #### Ready to go into production:
  
  * Builds a standard WAR file or an executable JAR file
  * Monitoring and Metrics with Actuator and Swagger
  * Support for Heroku cloud provider

  [Demo on Heroku](https://lit-shore-95037.herokuapp.com/)