Feature: Create the domain model

Scenario: Updating a class to add new attributes
    Given the domain model exists
    And the class exists
    When the user chooses to modify the class
    And the user creates a new attribute for the class
    And the user try to generate code using Umple
    Then the system should automatically generate the code to add the new attribute to the class

Scenario: Updating a class to add new associations
    Given the domain model exists
    And the class exists
    When the user chooses to modify the class
    And the user creates a new relationship between 2 classes
    And the user specifies the multiplicity of the relationship
    And the user tries to generate code using Umple
    Then the system should automatically generate the code to add the relationship between the classes

Scenario: Updating a class to add an enumeration
    Given the domain model exists
    And the class exists
    When the user chooses to modify the class
    And the user creates an enumeration attribute
    And the user tries to generate code using Umple
    Then the system should automatically generate the code to add the enumeration in the class

Scenario: Creating a sub-class  
    Given the domain model exists
    And the super-class exists
    When the user chooses to create a sub-class
    And the user adds new attribute to the sub-class
    And the user tries to generate code using Umple
    Then the system should automatically generate the code to add the sub-class
    And the sub-class should inherit all attributes of the super-class
    And the sub-class should have the new attributes

Scenario: Create Invalid Domain Model Definitions
    Given the domain model exists
    And the class exists
    And the class has a duplicate unique constraint on "email"
    When the user tries to generate code using Umple
    Then the system should provide a meaningful error message "Duplicate unique constraint"
    And the model should not generate incorrect code
