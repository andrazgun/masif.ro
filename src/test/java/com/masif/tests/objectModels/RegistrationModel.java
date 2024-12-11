package com.masif.tests.objectModels;

public class RegistrationModel {
    private AccountModel account;

    private String firstName, lastName,
            reEnterPassword, firstNameError, lastNameError,
            emailError, passwordError, reEnterPasswordError;

    public RegistrationModel(
            String firstName, String lastName,
            String email, String password,
            String reEnterPassword, String firstNameError,
            String lastNameError, String emailError,
            String passwordError, String reEnterPasswordError
            ) {
        AccountModel ac = new AccountModel();
        ac.setEmail(email);
        ac.setPassword(password);

        this.account = ac;
        this.firstName = firstName;
        this.lastName = lastName;
        this.reEnterPassword = reEnterPassword;
        this.firstNameError = firstNameError;
        this.lastNameError = lastNameError;
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.reEnterPasswordError = reEnterPasswordError;
    }
    public AccountModel getAccount() {return account;}

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public String getReEnterPassword() {return reEnterPassword;}

    public String getFirstNameError() {return firstNameError;}

    public String getLastNameError() {return lastNameError;}

    public String getEmailError() {return emailError;}

    public String getPasswordError() {return passwordError;}

    public String getReEnterPasswordError() {return reEnterPasswordError;}
}
