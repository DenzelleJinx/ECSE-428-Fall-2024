package HouseIt.cucumber.steps;

import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import HouseIt.dao.StudentDAO;
import HouseIt.model.Student;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountsFunctionalityStepDefinitions {

    @LocalServerPort
    int port; // TODO: Remove if not needed

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    StudentDAO studentDAO;

    // Dummy variables
    String studentEmail = "student.name@mail.mcgill.ca";
    String studentEmailInvalid = "student.name@gmail.ca";
    String studentUsername = "John Doe";
    String studentPassword = "mypass123";
    String landlordEmail = "landlordname@gmail.com";
    String landlordUsername = "Jane Doe";
    String landlordPassword = "mypass321";
    String landlordPhone = "514-123-4567";

    // Temporary variables
    boolean isStudent = false;
    String email;
    String username;
    String password;
    String phone;
    
    ResponseEntity<Object> lastResponse;

    @Before
    public void beforeScenario() {
        lastResponse = null;
        studentDAO.deleteAll();
    }

    @Given("the user is not logged in")
    public void theUserIsNotLoggedIn() {
        // Assume user is not logged in
    }

    @When("the user chooses to register a student account")
    public void theUserChoosesToRegisterAStudentAccount() {
        /* var result = testRestTemplate.getForEntity("/student", Object.class); // TODO: Test if it works with controller
        lastResponse = result; */
        isStudent = true;
    }

    @When("the user chooses to register a landlord account")
    public void theUserChoosesToRegisterALandlordAccount() {
        isStudent = false;
    }

    @When("the user enters their email, password, first name, last name, and phone number")
    public void theUserEntersTheirEmailPasswordUsernameAndPhoneNumber() {
        
    }

    @When("the user provides a picture of their face")
    public void theUserProvidesAPictureOfTheirFace() {
        // No profile picture needed
    }

    @When("the user enters an email that does not end with @mail.mcgill.ca or @mcgill.ca")
    public void theUserEntersAnEmailThatIsNotMcgill() {
        // Include other information here (valid only)
    }

    @When("the user does not provide a picture of their face")
    public void theUserDoesNotProvideAPictureOfTheirFace() {
        // No profile picture needed
    }

    @Then("the account is created with the provided information")
    public void theAccountIsCreatedWithTheProvidedInformation() {
        
    }

    @Then("the account is only activated once the user clicks the verification link sent by email")
    public void theAccountIsActivatedOnEmailVerification() {
        
    }

    @Then("the user is prompted to enter a valid email address conforming to the domain requirements")
    public void theUserIsPromptedToEnterAValidEmailAddress() {
        
    }

    @Then("the account is not created until a valid email is provided")
    public void theAccountIsNotCreatedUntilAValidEmailIsProvided() {
        
    }

    @Then("the user is prompted to upload a picture to proceed")
    public void theUserIsPromptedToUploadAPicture() {
        
    }

}
