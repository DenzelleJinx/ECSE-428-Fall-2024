package HouseIt.cucumber.steps;

import static org.junit.Assert.*;

import HouseIt.service.StudentService;
import HouseIt.service.LandlordService;
import HouseIt.model.Student;
import HouseIt.model.Landlord;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Map;

public class PasswordResetStepDefinitions {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LandlordService landlordService;

    private String responseMessage;
    private String email;
    private String username;
    private String newPassword;
    private String accountType;
    
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Given("the following user exists in the system:")
    public void theFollowingUserExistsInTheSystem(DataTable dataTable) {
        List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : users) {
            String username = row.get("username");
            String password = row.get("password");
            String email = row.get("email");
            String accountType = row.get("accountType");

            if ("student".equalsIgnoreCase(accountType)) {
                studentService.createStudent(username, password, email);
            } else if ("landlord".equalsIgnoreCase(accountType)) {
                landlordService.createLandlord(username, password, email, "1234567890");
            }
        }
    }

    @When("the user submits the following reset password request:")
    public void theUserSubmitsTheFollowingResetPasswordRequest(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = rows.get(0); // Access the first row

        email = row.get("email");
        username = row.get("username");
        newPassword = row.get("newPassword");
        accountType = row.get("accountType");

        try {
            if ("student".equalsIgnoreCase(accountType)) {
                if (studentService.existsByEmail(email) != null &&
                        studentService.existsByEmail(email).getUsername().equals(username)) {
                    studentService.resetPassword(email, newPassword);
                    responseMessage = "Password has been reset successfully.";
                } else {
                    responseMessage = "No student found with the provided email and username.";
                }
            } else if ("landlord".equalsIgnoreCase(accountType)) {
                if (landlordService.existsByEmail(email) != null &&
                        landlordService.existsByEmail(email).getUsername().equals(username)) {
                    landlordService.resetPassword(email, newPassword);
                    responseMessage = "Password has been reset successfully.";
                } else {
                    responseMessage = "No landlord found with the provided email and username.";
                }
            } else {
                responseMessage = "Invalid account type provided.";
            }
        } catch (Exception e) {
            responseMessage = e.getMessage();
        }
    }

    @Then("the system should respond with {string}")
    public void theSystemShouldRespondWith(String expectedMessage) {
        assertEquals(expectedMessage, responseMessage);
    }

    @Then("the user with info exists in the system:")
    public void theUserWithInfoExistsInTheSystem(DataTable dataTable) {
        List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : users) {
            String expectedUsername = row.get("username");
            String expectedPassword = row.get("password");
            String expectedEmail = row.get("email");

            if ("student".equalsIgnoreCase(row.get("accountType"))) {
                Student student = studentService.existsByEmail(expectedEmail);
                assertNotNull(student);
                assertEquals(expectedUsername, student.getUsername());
                assertTrue(encoder.matches(expectedPassword, student.getPassword()));
            } else if ("landlord".equalsIgnoreCase(row.get("accountType"))) {
                Landlord landlord = landlordService.existsByEmail(expectedEmail);
                assertNotNull(landlord);
                assertEquals(expectedUsername, landlord.getUsername());
                assertTrue(encoder.matches(expectedPassword, landlord.getPassword()));
            }
        }
    }
}
