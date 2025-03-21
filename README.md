# Spring Boot Project with AWS Cognito and MongoDB

This project is a REST API built with Spring Boot for managing Streams, Posts, and Users. It uses MongoDB as the database and integrates AWS Cognito for secure authentication and authorization via OAuth2 and OpenID Connect.

## Getting Started
These instructions will help you set up and run the project on your local machine for development and testing purposes.

## Prerequisites
- **Java 17** or later
- **Maven** installed
- **AWS Cognito** configured for OAuth2 authentication

## Installing
### Clone the Repository
```sh

```


### Run the Application
```sh
$ mvn spring-boot:run
```

## Architecture

```
/src/main/java/co/edu/eci/arep/x
│── controller        # REST Controllers
│── model             # Data Models
│── repository        # Database Access Interfaces
│── service           # Business Logic Layer
│── security          # Security Configurations (OAuth2, JWT, etc.)
│── Application.java  # Main Entry Point of the Application
```

## API Endpoints

### **Posts**
- `GET /posts` - Retrieve all posts.
- `GET /posts/stream/{streamId}` - Retrieve posts from a specific stream.
- `POST /posts` - Create a new post.
- `DELETE /posts/{id}` - Delete a post by ID.

### **Streams**
- `POST /streams` - Create a new stream.
- `GET /streams` - Retrieve all streams.
- `GET /streams/{id}` - Retrieve a stream by ID.
- `POST /streams/{id}/posts` - Add a post to a stream.
- `GET /streams/{id}/posts` - Retrieve all posts from a stream.
- `DELETE /streams/{id}` - Delete a stream by ID.

### **Users**
- `GET /users/me` - Retrieve authenticated user details.
- `GET /users/{username}` - Retrieve a user by username.
- `GET /users/id/{id}` - Retrieve a user by ID.
- `POST /users` - Create a new user.

## Authentication and Security
This project implements authentication using AWS Cognito with OAuth2 and OpenID Connect (OIDC).

### Security Flow
1. The user logs in through AWS Cognito.
2. The API verifies the identity and issues an access token.
3. Endpoints that require authentication validate the token before granting access.

## Deployment
### Local Deployment
Run the application locally using Maven:
```sh
$ mvn spring-boot:run
```

### AWS Deployment
To deploy on AWS, follow these steps:
1. Build the project:
   ```sh
   $ mvn clean package
   ```
2. Upload the generated JAR file to an AWS EC2 instance or AWS Elastic Beanstalk.
3. Configure environment variables and security groups.
4. Run the application on the AWS instance.

## Built With
- **Spring Boot** - Framework for building Java applications.
- **MongoDB** - NoSQL database.
- **AWS Cognito** - Authentication and security.
- **Lombok** - Reduces boilerplate code.
- **Maven** - Dependency management and build tool.

## Authors

**Mateo Forero** - *Initial work* - [MateoSebF](https://github.com/MateoSebF)
**Juan Daniel Murcia** - #Initial work" - [murcia0421](https://github.com/murcia0421)

## License
This project is licensed under the MIT License.

