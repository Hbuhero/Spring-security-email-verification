## Email Verification Authentication Web App (Java)

This project provides a Java web application for implementing email verification authentication with additional features like JWT and stateless session management.

**Features:**

* User registration with email verification
* JWT based token generation and validation
* Stateless session management
* (Optional) Integration with your existing user management system

**Prerequisites:**

* Java 11+
* PostgreSQL

**Installation:**

1. Clone the project repository.
2. Configure the database connection details in the `application.properties` file.
3. Configure SMTP settings such as username and password. Check out this article for more information: [https://www.gmass.co/blog/gmail-smtp/.]
4. Build the project using Maven: `mvn clean install`

**Deployment:**

Deploy the built WAR file to your preferred web container (e.g., Tomcat, Jetty).

**Usage:**

The web app provides functionalities for:

* User registration with email address
* Sending email verification links
* Verifying user email addresses
* User login and JWT token generation
* Accessing protected resources with JWT tokens

**Additional Notes:**

* This is a basic example. You may need to adapt the code to your specific needs.
* Consider implementing security best practices for handling passwords and tokens.
* Refer to the source code for detailed implementation details.

**Contributing:**

Feel free to contribute to this project by creating pull requests with improvements or additional features.

**Enjoy using this email verification authentication web app!**
