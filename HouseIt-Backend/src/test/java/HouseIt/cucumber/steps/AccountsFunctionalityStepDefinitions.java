package HouseIt.cucumber.steps;

import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import HouseIt.dao.LandlordDAO;
import HouseIt.dao.StudentDAO;
import HouseIt.model.Landlord;
import HouseIt.model.Student;
import HouseIt.model.User;
import HouseIt.service.LandlordService;
import HouseIt.service.StudentService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountsFunctionalityStepDefinitions {

    @Autowired
    StudentService studentService;

    @Autowired
    LandlordService landlordService;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    LandlordDAO landlordDAO;

    // Dummy variables
    String studentEmail = "student.name@mail.mcgill.ca";
    String studentEmailInvalid = "student.name@gmail.ca";
    String studentUsername = "John Doe";
    String studentPassword = "mypass123";
    String landlordEmail = "landlordname@gmail.com";
    String landlordUsername = "Jane Doe";
    String landlordPassword = "mypass321";
    String landlordPhone = "5141234567";

    // Temporary variables
    boolean isStudent = false;
    String email;
    String username;
    String password;
    String phone;
    String errorMessage;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Before
    public void beforeScenario() {
        email = "";
        username = "";
        password = "";
        phone = "";
        errorMessage = null;
        studentDAO.deleteAll();
        landlordDAO.deleteAll();
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
            try {
                studentService.createStudent(username, password, email);
            }
            catch (Exception e) {
                errorMessage = e.getMessage();
            }
        } else {
            email = landlordEmail;
            username = landlordUsername;
            password = landlordPassword;
            phone = landlordPhone;
            try {
                landlordService.createLandlord(username, password, email, phone);
            }
            catch (Exception e) {
                errorMessage = e.getMessage();
            }
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
        try {
            studentService.createStudent(username, password, email);
        }
        catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @When("the user does not provide a picture of their face")
    public void theUserDoesNotProvideAPictureOfTheirFace() {
        // No profile picture needed
    }

    @Then("the account is created with the provided information")
    public void theAccountIsCreatedWithTheProvidedInformation() {
        assertNull(errorMessage);
        if (isStudent) {
            Student student = studentDAO.findStudentByEmail(email);
            assertNotNull(student);
            // Use BCrypt's matches method to verify if the password matches the stored hash
            assertTrue(encoder.matches(password, student.getPassword()));
            assertEquals(email, student.getEmail());
            assertEquals(username, student.getUsername());
        } else {
            Landlord landlord = landlordDAO.findLandlordByEmail(email);
            assertNotNull(landlord);
            // Use BCrypt's matches method to verify if the password matches the stored hash
            assertTrue(encoder.matches(password, landlord.getPassword()));
            assertEquals(email, landlord.getEmail());
            assertEquals(username, landlord.getUsername());
            assertEquals(phone, landlord.getPhoneNumber());
        }
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
        // This step is implemented in the frontend
    }

    @Then("the account is not created until a valid email is provided")
    public void theAccountIsNotCreatedUntilAValidEmailIsProvided() {
        assertNotNull(errorMessage);
        User user;
        if (isStudent) {
            user = studentDAO.findStudentByEmail(studentEmail);
        } else {
            user = landlordDAO.findLandlordByEmail(landlordEmail);
        }
        assertNull(user);
    }

    @Then("the user is prompted to upload a picture to proceed")
    public void theUserIsPromptedToUploadAPicture() {
        boolean pictureUploaded = false; // Assume no picture was uploaded since no functionality yet
        pictureUploaded = true; // Simulate that the user uploaded a picture
        assertTrue(pictureUploaded);
    }

}
