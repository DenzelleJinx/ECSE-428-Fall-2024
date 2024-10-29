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

    ResponseEntity<Object> lastResponse;

    @Before
    public void beforeScenario() {
        lastResponse = null;
        studentDAO.deleteAll();
    }

    @Given("the user is not logged in")
    public void theUserIsNotLoggedIn() {
        Student student = new Student();
        // TODO: REMOVE THIS, IT IS JUST FOR TESTING
        student.setEmail("test@mail.mcgill.ca");
        student.setUsername("test");
        student.setPassword("test");
        studentDAO.save(student);
    }

    @When("the user chooses to register a student account")
    public void theUserChoosesToRegisterAStudentAccount() {
        var result = testRestTemplate.getForEntity("/student", Object.class); // TODO: Test if it works with controller
        lastResponse = result;
    }

    @When("the user chooses to register a landlord account")
    public void theUserChoosesToRegisterALandlordAccount() {
        var result = testRestTemplate.getForEntity("/landlord", Object.class);
        lastResponse = result;
    }

    @When("the user enters their email, password, first name, last name, and phone number")
    public void theUserEntersTheirEmailPasswordUsernameAndPhoneNumber() {
        
    }

    @When("the user provides a picture of their face")
    public void theUserProvidesAPictureOfTheirFace() {
        
    }

    @When("the user enters an email that does not end with @mail.mcgill.ca or @mcgill.ca")
    public void theUserEntersAnEmailThatIsNotMcgill() {
        
    }

    @When("the user does not provide a picture of their face")
    public void theUserDoesNotProvideAPictureOfTheirFace() {
        
    }

    @Then("the account is created with the provided information")
    public void theAccountIsCreatedWithTheProvidedInformation() {
        // TODO: FIX THIS. THIS IS JUST FOR TESTING THE DAO
        //assertEquals(404, lastResponse.getStatusCode().value());
        Student student = studentDAO.findStudentByEmail("test@mail.mcgill.ca");
        assertNotNull(student);
        assertEquals("test", student.getUsername());
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
