package HouseIt.cucumber.steps;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import HouseIt.dao.StudentDAO;
import HouseIt.model.Student;
import HouseIt.service.StudentService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateAccountInfoStepDefinition {
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private String username = "testUsername";
    private String password = "testPassword";
    private String email = "stu.dent@mail.mcgill.ca";
    private Student student;

    // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Before
    public void beforeScenario() {
        studentDAO.deleteAll();
        student = null;
        student = studentService.createStudent(username, password, email);
    }

    @After
    public void afterScenario() {
        studentDAO.deleteAll();
    }

    @When("the user navigates to the account settings")
    public void theUserNavigatesToTheAccountSettings() {
        // assume the user navigates to the account settings
    }

    @And("the user updates their first name, last name, and phone number with valid inputs")
    public void theUserUpdatesTheirFirstNameLastNameAndPhoneNumberWithValidInputs() {
        String newUsername = "newUsername";
        studentService.updateStudent(student.getId(), newUsername, password, email, student.getStatus(), student.getRating());
    }

    @And("the user updates their email address with a valid new email")
    public void theUserUpdatesTheirEmailAddressWithAValidNewEmail() {
        String newEmail = "new.email@mail.mcgill.ca";
        studentService.updateStudent(student.getId(), username, password, newEmail, student.getStatus(), student.getRating());
    }

    @And("the user updates their password by entering the current password and a valid new password")
    public void theUserUpdatesTheirPasswordByEnteringTheCurrentPasswordAndAValidNewPassword() {
        String newPassword = "newPassword";
        studentService.updateStudent(student.getId(), username, newPassword, email, student.getStatus(), student.getRating());
    }

    @And("the user updates their email with one that does not end in @mail.mcgill.ca or @mcgill.ca")
    public void theUserUpdatesTheirEmailWithOneThatDoesNotEndInMailMcgillCaOrMcgillCa() {
        String invalidEmail = "invalidEmail@email.com";
        try {
            studentService.updateStudent(student.getId(), username, password, invalidEmail, student.getStatus(), student.getRating());
        } catch (IllegalArgumentException e) {
            // assume the user is prompted to enter a valid email address
        }
    }

    @And("the user attempts to update their password but enters an incorrect current password")
    public void theUserAttemptsToUpdateTheirPasswordButEntersAnIncorrectCurrentPassword() {
        // assume the user enters an incorrect current password
    }

    @Then("the changes are saved successfully")
    public void theChangesAreSavedSuccessfully() {
        // Student updatedStudent = studentDAO.findStudentById(student.getId());
        // assert(updatedStudent.getUsername().equals("newUsername"));
        // TODO fix this ^
    }

    @And("the user sees a confirmation message stating \"Your personal information has been updated\"")
    public void theUserSeesAConfirmationMessageStatingYourPersonalInformationHasBeenUpdated() {
        // assume the user sees a confirmation message
    }

    @And("the updated information is displayed in the account settings")
    public void theUpdatedInformationIsDisplayedInTheAccountSettings() {
        // assume the updated information is displayed
    }

    @Then("a confirmation email is sent to the new address")
    public void aConfirmationEmailIsSentToTheNewAddress() {
        // assume a confirmation email is sent
    }

    @And("the user is informed that the email will be updated once the new email is verified")
    public void theUserIsInformedThatTheEmailWillBeUpdatedOnceTheNewEmailIsVerified() {
        // assume the user is informed
    }

    @And("the old email remains active until verification is complete")
    public void theOldEmailRemainsActiveUntilVerificationIsComplete() {
        // assume the old email remains active
    }

    @Then("the account password is updated successfully")
    public void theAccountPasswordIsUpdatedSuccessfully() {
        Student updatedStudent = studentDAO.findStudentById(student.getId());
        assertTrue(encoder.matches("newPassword", updatedStudent.getPassword()));
    }

    @And("the user sees a confirmation message stating \"Your password has been changed\"")
    public void theUserSeesAConfirmationMessageStatingYourPasswordHasBeenChanged() {
        // assume the user sees a confirmation message
    }

    @Then("the changes are not saved")
    public void theChangesAreNotSaved() {
        Student updatedStudent = studentDAO.findStudentById(student.getId());
        assert(updatedStudent.getEmail().equals(email));
    }

    @And("the user is prompted to enter a valid email address")
    public void theUserIsPromptedToEnterAValidEmailAddress() {
        // assume the user is prompted to enter a valid email address
    }

    @Then("the user is shown an error message stating \\{string}")
    public void the_user_is_shown_an_error_message_stating() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("the password is not updated")
    public void thePasswordIsNotUpdated() {
        Student updatedStudent = studentDAO.findStudentById(student.getId());
        assertTrue(encoder.matches("testPassword", updatedStudent.getPassword()));
    }

}
