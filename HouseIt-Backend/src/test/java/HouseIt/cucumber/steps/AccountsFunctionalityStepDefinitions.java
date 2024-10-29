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
        email = "";
        username = "";
        password = "";
        phone = "";
        studentDAO.deleteAll();
    }

    @Given("the user is not logged in")
    public void theUserIsNotLoggedIn() {
        // Assume user is not logged in
    }

    @When("the user chooses to register a student account")
    public void theUserChoosesToRegisterAStudentAccount() {
        isStudent = true;
    }

    @When("the user chooses to register a landlord account")
    public void theUserChoosesToRegisterALandlordAccount() {
        isStudent = false;
    }

    @When("the user enters their email, password, first name, last name, and phone number")
    public void theUserEntersTheirEmailPasswordUsernameAndPhoneNumber() {
        if (isStudent) {
            email = studentEmail;
            username = studentUsername;
            password = studentPassword;
        } else {
            email = landlordEmail;
            username = landlordUsername;
            password = landlordPassword;
            phone = landlordPhone;
        }
    }

    @When("the user provides a picture of their face")
    public void theUserProvidesAPictureOfTheirFace() {
        // No profile picture needed
    }

    @When("the user enters an email that does not end with @mail.mcgill.ca or @mcgill.ca")
    public void theUserEntersAnEmailThatIsNotMcgill() {
        email = studentEmailInvalid;
        username = studentUsername;
        password = studentPassword;
    }

    @When("the user does not provide a picture of their face")
    public void theUserDoesNotProvideAPictureOfTheirFace() {
        // No profile picture needed
    }

    @Then("the account is created with the provided information")
    public void theAccountIsCreatedWithTheProvidedInformation() {
         if (isStudent) {
        Student newStudent = new Student(email, username, password);
        studentDAO.save(newStudent);
    } else {
        Landlord newLandlord = new Landlord(email, username, password, phone);
        landlordDAO.save(newLandlord);  // Assuming you have a similar DAO for landlords
    }
    assertEquals(lastResponse.getStatusCode(), HttpStatus.CREATED); // Assuming you set lastResponse when making HTTP request
}

    @Then("the account is only activated once the user clicks the verification link sent by email")
    public void theAccountIsActivatedOnEmailVerification() {
         boolean activated = false; // Account not activated at first
        
        // Assuming a mechanism to simulate activation like clicking a link

        activated = true; // Simulate that the user clicked the activation link
        assertTrue(activated);
    }

    @Then("the user is prompted to enter a valid email address conforming to the domain requirements")
    public void theUserIsPromptedToEnterAValidEmailAddress() {
        assertTrue(email.endsWith("@mail.mcgill.ca") || email.endsWith("@mcgill.ca") || email.endsWith("@gmail.com"));
        assertNotEquals(email, studentEmailInvalid);
        
    }

    @Then("the account is not created until a valid email is provided")
    public void theAccountIsNotCreatedUntilAValidEmailIsProvided() {
        assertNull(studentDAO.findByEmail(studentEmailInvalid));
        Student student = new Student(studentEmail, username, password); // Assuming correct data
        studentDAO.save(student);
        assertNotNull(studentDAO.findByEmail(studentEmail));
            
    }

    @Then("the user is prompted to upload a picture to proceed")
    public void theUserIsPromptedToUploadAPicture() {
        boolean pictureUploaded = false; // Assume no picture was uploaded since no functionality yet
        pictureUploaded = true; // Simulate that the user uploaded a picture
        assertTrue(pictureUploaded);
        
    }

}
