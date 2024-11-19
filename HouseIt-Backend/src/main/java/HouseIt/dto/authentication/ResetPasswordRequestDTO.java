package HouseIt.dto.authentication;

public class ResetPasswordRequestDTO {
    private String email;
    private String username;
    private String newPassword;
    private String accountType;

    public ResetPasswordRequestDTO(String email, String username, String newPassword, String accountType) {
        this.email = email;
        this.username = username;
        this.newPassword = newPassword;
        this.accountType = accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

