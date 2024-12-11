package com.masif.tests.dataTests;

import com.masif.pages.RegistrationPage;
import com.masif.tests.BaseTest;
import com.masif.tests.objectModels.RegistrationModel;
import com.masif.utils.AllureTestListener;
import com.masif.utils.Tools;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
@Listeners({AllureTestListener.class})
@Epic("Regression Tests")
@Feature("Registration Negative Data Tests")
public class RegistrationNegativeDataTests extends BaseTest {

    //    mySQL Database Data Provider
    @DataProvider(name = "SQLDataProvider")
    public Iterator<Object[]> SQLDpCollection() {
        //        get DB connection settings
        System.out.println("Use dbHostname:" + dbHostname);
        System.out.println("Use dbUser:" + dbUser);
        System.out.println("Use dbPort:" + dbPort);
        System.out.println("Use dbPassword:" + dbPassword);
        System.out.println("Use dbSchema:" + dbSchema);

        Collection<Object[]> dp = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbHostname + ":" + dbPort
                    + "/" + dbSchema, dbUser, new String(base64.decode(dbPassword.getBytes()))); //create connection url to DB
            Statement statement = connection.createStatement(); //connect to DB
            ResultSet resultSet = statement.executeQuery("SELECT * FROM registration_data"); //query to DB
            while (resultSet.next()) {
                RegistrationModel rm = new RegistrationModel
                        (
                                getEscapedElement(resultSet, "firstName"),
                                getEscapedElement(resultSet, "lastName"),
                                getEscapedElement(resultSet, "email"),
                                getEscapedElement(resultSet, "password"),
                                getEscapedElement(resultSet, "reEnterPassword"),
                                getEscapedElement(resultSet, "firstNameError"),
                                getEscapedElement(resultSet, "lastNameError"),
                                getEscapedElement(resultSet, "emailError"),
                                getEscapedElement(resultSet, "passwordError"),
                                getEscapedElement(resultSet, "reEnterPasswordError")
                        );
                dp.add(new Object[]{rm});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();
    }

    //    Method designed to:
//    Fetch a string value from a specified column of a database result set.
//    Clean up that string by removing double single quotes.
//    Return the cleaned string.
    private String getEscapedElement(ResultSet resultSet, String element) throws SQLException {
        return Tools.replaceElements(resultSet.getString(element), "''", "");
    }

    @Test(
            dataProvider = "SQLDataProvider",
            groups = {"Regression"},
            description = "Registration negative test with MySQL data provider"
    )
    public void registrationWithDBTest(RegistrationModel rm) {
        printData(rm);
        registrationActions(rm);
    }

    //    Utility methods
//methods used to show info related to login model
    private void printData(RegistrationModel rm) {
        System.out.println(rm);
    }

    private void registrationActions(RegistrationModel rm) {
        RegistrationPage registrationPage = new RegistrationPage(driver);
//        open registration page
        System.out.println("Open registration page");
        driver.get(baseUrl + registrationPage.setPagePath());
        System.out.println(baseUrl + registrationPage.setPagePath());

//        register
        registrationPage.createAccount
                (
                    rm.getFirstName(), rm.getLastName(),
                    rm.getAccount().getEmail(), rm.getAccount().getPassword(),
                    rm.getReEnterPassword()
                ); //getter from RegistrationModel and from AccountModel
        String expectedFirstNameErr = rm.getFirstNameError();
        String expectedLastNameErr = rm.getLastNameError();
        String expectedEmailErr = rm.getEmailError();
        String expectedPassErr = rm.getPasswordError();
        String expectedReEnterPassErr = rm.getReEnterPasswordError();

        System.out.println("Expected First Name error: " + expectedFirstNameErr);
        Assert.assertTrue(registrationPage.checkErr(expectedFirstNameErr, "firstNameErr"));

        System.out.println("Expected Last Name error: " + expectedLastNameErr);
        Assert.assertTrue(registrationPage.checkErr(expectedLastNameErr, "lastNameErr"));

        System.out.println("Expected email error: " + expectedEmailErr);
        Assert.assertTrue(registrationPage.checkErr(expectedEmailErr, "emailErr"));

        System.out.println("Expected password error: " + expectedPassErr);
        Assert.assertTrue(registrationPage.checkErr(expectedPassErr, "passErr"));

        System.out.println("Expected password confirmation error: " + expectedReEnterPassErr);
        Assert.assertTrue(registrationPage.checkErr(expectedReEnterPassErr, "reEnterPassErr"));
    }
}

