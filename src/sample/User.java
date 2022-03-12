package sample;

public class User {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String role;

    public User(String firstName, String lastName,String login, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public String getNameOne() {
        return firstName;
    }

    public void setNameOne(String firstName) {
        this.firstName = firstName;
    }

    public String getNameTwo() {
        return lastName;
    }

    public void setNameTwo(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String password) {
        this.password = role;
    }
}
