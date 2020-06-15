package kg.max.crud.model;

import java.util.List;

public class UserDTO {

    private long id;
    private String username;
    private String password;
    private String firstname;
    private String surname;
    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String firstname, String surname, List<String> roles) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.roles = roles;
    }

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstname = user.getFirstname();
        this.surname = user.getSurname();
        this.password = user.getPassword();
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
