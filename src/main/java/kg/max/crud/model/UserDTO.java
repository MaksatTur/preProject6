package kg.max.crud.model;

public class UserDTO {

    private long id;
    private String username;
    private String password;
    private String firstname;
    private String surname;
    private String role;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String firstname, String surname, String roles) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.role = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
