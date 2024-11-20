package HouseIt.dto.users;

import HouseIt.model.User;
import HouseIt.model.Landlord;
import HouseIt.model.Student;


public class UserDTO {
    
    private int id;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String accountStatus;
    private float rating;
    private String accountType;

    public UserDTO() {
    }

    public UserDTO(String userName, String email, String password, String phoneNumber, String accountStatus, float rating, String accountType) {
        this.username = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.accountStatus = accountStatus;
        this.rating = rating;
        this.accountType = accountType;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        if (user instanceof Landlord) {
            Landlord landlord = (Landlord) user;
            this.accountType = "landlord";
            this.phoneNumber = landlord.getPhoneNumber();
        } else if (user instanceof Student) {
            this.accountType = "student";
            this.phoneNumber = "";
        } else {
            this.accountType = "admin";
        }
        this.rating = user.getRating();
        this.accountStatus = user.getStatus().toString();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String name) {
        this.username = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAccountStatus() {
        return accountStatus;
    }
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}   