Feature: Structured backend environment with project structure, dependencies and initial configuration using Java

  Background:
    Given I have access to AWS environment

    Scenario: Ensure application performance and scalability (Normal Flow)
        Given the application is running successfully
        When I simulate multiple concurrent requests to the REST API
        Then the application should respond within 200ms
        And the system should handle up to 20 requests simultaneously without crashing

    Scenario: Ensure application performance and scalability (Alternate Flow: slow response under load)
        Given the application is running successfully
        When I simulate more than 20 concurrent requests to the REST API
        Then the application should start to respond slower than 200ms
        And the system should still process all requests without crashing but with reduced performance
        And I should identify potential bottlenecks in the code or server configuration for optimization

    Scenario: Ensure application security(Normal Flow)
        Given the application has REST endpoints
        When I configure security settings for the application
        And I add basic authentication to the endpoints
        Then sensitive operations should require authentication
        And unauthorized requests should be denied with a 401 Unauthorized response

    Scenario: Ensure application security (Alternate Flow: role-based access control)
        Given the application has REST endpoints
        When I configure role-based access control (RBAC) for the endpoints
        Then users with different roles should have different levels of access (e.g., admin vs user)
        And unauthorized users trying to access restricted endpoints should receive a 403 Forbidden response


    Scenario: Ensure application is maintainable
        Given the project has a well-defined structure
        When I review the project organization and codebase
        Then the code should be modular, properly spaced, and follow proper naming conventions
        And there should be clear separation between controllers, services, and repositories

    Scenario: Ensure application is fault tolerant  
        Given the application interacts with external services (e.g., database, APIs)
        When an external service is down or slow to respond
        Then the application should handle the error gracefully without crashing
        And appropriate error messages should be returned to the client (e.g., 503 Service Unavailable)

    Scenario: Ensure application is deployable to AWS
        Given the project is completed and running locally
        When I prepare the project for deployment to an AWS environment
        Then the project should be packaged correctly (e.g., as a JAR or Docker image)
        And it should deploy to AWS without errors
        And the deployed application should be accessible via a public endpoint

    Scenario: Ensure database connection reliability
        Given the application connects to a database
        When the database is under heavy load or experiences a temporary disconnection
        Then the application should retry the connection or provide a meaningful error message
        And ensure no data is lost or corrupted during this time

    Scenario: Ensure database connection reliability (alternate flow - failover to backup database)
        Given the application connects to a primary database
        When the primary database becomes unavailable or experiences heavy load
        Then the application should automatically fail over to a backup database
        And the data should remain consistent and the system should continue functioning without interruption
        And an alert should be raised to notify the team of the failover event