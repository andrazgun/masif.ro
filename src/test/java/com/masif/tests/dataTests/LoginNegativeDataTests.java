package com.masif.tests.dataTests;

import com.masif.pages.LoginPage;
import com.masif.tests.BaseTest;
import com.masif.tests.objectModels.LoginModel;
import com.masif.utils.Tools;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Epic("Regression Tests")
@Feature("Login Negative Data Tests")
public class LoginNegativeDataTests extends BaseTest {

    //    JSON Data Provider
    @DataProvider(name = "jsonDataProvider")
    public Iterator<Object[]> jsonDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        File f = new File("src\\test\\resources\\data\\loginNegativeTestData.json");
        LoginModel[] lms = objectMapper.readValue(f, LoginModel[].class);

        for (LoginModel lm : lms)
            dp.add(new Object[]{lm});
        return dp.iterator();
    }

    @Step("Login negative test with data from JSON")
    @Test(
            dataProvider = "jsonDataProvider",
            description = "Login negative test with JSON data provider",
            groups = {"Regression"}
    )
    public void loginWithJsonDataProviderTest(LoginModel lm) {
        printData(lm);
        loginActions(lm);
    }

    //    mySQL Database Data Provider
    @DataProvider(name = "SQLDataProvider")
    public Iterator<Object[]> SQLDpCollection() {

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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM login_data"); //query to DB
            while (resultSet.next()) {
                LoginModel lm = new LoginModel(
                        getEscapedElement(resultSet, "email"),
                        getEscapedElement(resultSet, "password"),
                        getEscapedElement(resultSet, "emailError"),
                        getEscapedElement(resultSet, "passwordError")
                );
                dp.add(new Object[]{lm});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dp.iterator();
    }

    private String getEscapedElement(ResultSet resultSet, String element) throws SQLException {
        return Tools.replaceElements(resultSet.getString(element), "''", "");
    }

    @Step("Login negative test with data from MySQL DB")
    @Test(
            dataProvider = "SQLDataProvider",
            groups = {"Regression"},
            description = "Login negative test with MySQL data provider"
    )
    public void loginWithDBTest(LoginModel lm) {
        printData(lm);
        loginActions(lm);
    }

    private void printData(LoginModel lm) {
        System.out.println(lm);
    }

    @Step
    private void loginActions(LoginModel lm) {
        LoginPage loginPage = new LoginPage(driver);

        System.out.println("Open login page");
        driver.get(baseUrl + loginPage.setPagePath());
        System.out.println(baseUrl + loginPage.setPagePath());

        loginPage.login(lm.getAccount().getEmail(), lm.getAccount().getPassword()); //getter from LoginModel and from AccountModel

        String expectedEmailErr = lm.getEmailError();
        String expectedPassErr = lm.getPasswordError();

        System.out.println("Verify expected errors present:\n Expected email error: " + expectedEmailErr);
        Assert.assertTrue(loginPage.checkErr(expectedEmailErr, "emailErr"));
        System.out.println("Expected password error: " + expectedPassErr);
        Assert.assertTrue(loginPage.checkErr(expectedPassErr, "passErr"));
    }
}
