package Tests.DataTests;

import Pages.LoginPage;
import Tests.BaseTest;
import Tests.ObjectModels.LoginModel;
import Utils.Tools;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LoginDataTests extends BaseTest {

    //    JSON Data Provider
    @DataProvider(name = "jsonDataProvider")
    public Iterator<Object[]> jsonDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>(); //list of objects
//      here is starting deserialization of json into LoginModel object
        ObjectMapper objectMapper = new ObjectMapper(); //mapping the json with ObjectMapper

        File f = new File("src\\test\\resources\\data\\loginTestData.json"); //loading the file
        LoginModel[] lms = objectMapper.readValue(f, LoginModel[].class); //mapping the file data as a list [] of LoginModel class

        for (LoginModel lm : lms)
            dp.add(new Object[]{lm}); //create an object that will add objects in data provider

        return dp.iterator();
    }
    @Test(dataProvider = "jsonDataProvider")
    public void loginWithJsonDataProviderTest(LoginModel lm) {
        printData(lm);
        loginActions(lm);
    }

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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM login_data"); //query to DB
            while (resultSet.next()) {
                LoginModel lm = new LoginModel(
                        getEscapedElement(resultSet, "email"),
                        getEscapedElement(resultSet, "password"),
                        getEscapedElement(resultSet, "emailError"),
                        getEscapedElement(resultSet, "passwordError"));
                dp.add(new Object[]{lm});
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

    @Test(dataProvider = "SQLDataProvider")
    public void loginWithDBTest(LoginModel lm) {
        printData(lm);
        loginActions(lm);
    }


    //    Utility methods
//methods used to show info related to login model
    private void printData(LoginModel lm) {
        System.out.println(lm);
    }
    private void loginActions(LoginModel lm) {
        LoginPage loginPage = new LoginPage(driver);

//        open login page
        System.out.println("Open login page");
        driver.get(baseUrl + loginPage.setPagePath());
        System.out.println(baseUrl + loginPage.setPagePath());

//        login
    loginPage.login(lm.getAccount().getEmail(), lm.getAccount().getPassword()); //getter from LoginModel and from AccountModel

        String expectedEmailErr = lm.getEmailError();
        String expectedPassErr = lm.getPasswordError();

        System.out.println("Verify expected errors present:\n Expected email error:" + expectedEmailErr);
        Assert.assertTrue(loginPage.checkErr(expectedEmailErr, "emailErr"));

        System.out.println("Expected password error:" + expectedPassErr);
        Assert.assertTrue(loginPage.checkErr(expectedPassErr, "passErr"));
    }

}
