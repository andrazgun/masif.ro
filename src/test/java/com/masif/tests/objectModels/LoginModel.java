package com.masif.tests.objectModels;

public class LoginModel {
    private AccountModel account;
    private String emailError;
    private String passwordError;

    public LoginModel() {}

    public LoginModel(String email, String password, String emailError, String passwordError) {
        AccountModel ac = new AccountModel();
        ac.setEmail(email);
        ac.setPassword(password);

        this.account = ac;
        this.emailError = emailError;
        this.passwordError = passwordError;
    }

    public AccountModel getAccount() {return account;}

    public String getEmailError() {return emailError;}

    public String getPasswordError() {return passwordError;}

    @Override
    public String toString() {
        return "LoginData{" +
                "account={email: " + account.getEmail() +
                ",password: " + account.getPassword() +
                "}, emailError='" + emailError + '\'' +
                ", passwordError='" + passwordError + '\'' +
                '}';
    }
}
