# myBLOG
A demo blog entry application to showcase Spring boot, REST API, Swagger UI, Cucumber, Jacoco plugin, Travis-CI.

## Technogies Used
* Framework - Spring boot, REST API
* Integration Test - Cucumber for BDD development
* Test Coverage - Jacoco
* REST API documentation - Swagger UI
* Continuous Integration - Travis-CI
* Hosting - Heroku
* Database - PostgreSQL

## Overview
myBLOG is a backend server which have the following functionalities:
1. Method to **create** a blog entry
2. Method to **view** all existing blog entries
3. Method to **view** a specific blog entry
4. Method to **update** a blog entry
5. Method to **delete** a blog entry

## Getting Started
myBLOG is a typical Spring boot application. It can be deploy similar to other Spring boot application.

### Prerequisites
Internally myBLOG is using mySQL database. A mySQL database named "blog" must be created beforehand.
After database is created, execute the sql script located at /scripts/blog.sql to futher create the table required.

Configuration of the database need to be updated according at application.properties.
```
spring.datasource.url=jdbc:mysql://localhost:3306/blog?useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=<username>
spring.datasource.password=<password>
```

## REST API
Endpoint for REST API is /blog

### API Overview

For more detail API, refer to [Swagger documentation](https://jsfong-my-blog.herokuapp.com/swagger-ui.html#/).

Blog Model in json payload:

| Field     | Type    | Remark |
| --------|---------|-------|
| id | int | Assign by database. Will be ignore if in the json |
| title  | String   | Mandatory for Create blog   |
| blog | String | Optional for Create blog   |

example:
```
{
  "body": "Sample Body",
  "id": 0,
  "title": "Sample title"
}
```

#### Create Blog
* Endpoint: /blog
* Http Method: POST
* Parameters: Blog JSON payload
* Return: Blog JSON payload which created

#### View All Blog
* Endpoint: /blog
* Http Method: GET
* Parameters: None
* Return: List of Blog in json

#### View Blog with id
* Endpoint: /blog/{id}
* Http Method: GET
* Parameters: int id
* Return: Blog JSON payload with id {id}

#### Update Blog with id
* Endpoint: /blog/{id}
* Http Method: PUT
* Parameters: int id, Blog JSON payload
* Return:Blog JSON payload which updated

#### Delete Blog with id
* Endpoint: /blog/{id}
* Http Method: DELETE
* Parameters: int id
* Return: None

## Web UI
Refer to the [Web UI](https://jsfong-my-blog.herokuapp.com/showCreateBlog) JSP View

## Testing
### Unit Test
Unit test implemeted using jUnit, in package **com.jsfong.myblog.unittest**.

### Integration Test
Integration test or system test implemeted using Cucumber in package **com.jsfong.myblog.integration**.

### Test Coverage
Test coverage implemeted using jacoco. Report available at /target/site/jacoco.

Command to trigger:
```
mvn clean verify
```
