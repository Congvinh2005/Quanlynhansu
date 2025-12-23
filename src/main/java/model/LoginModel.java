package model;

public class LoginModel {

    private String user;
    private String pass;

    public LoginModel() {
    }

    public LoginModel(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
}
