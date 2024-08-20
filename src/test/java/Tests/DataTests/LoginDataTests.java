package Tests.DataTests;

import Pages.LoginPage;
import Tests.BaseTest;
import Tests.ObjectModels.LoginModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LoginDataTests extends BaseTest {

    //    JSON Data Provider
    @DataProvider(name = "jsonDp")
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
    @Test(dataProvider = "jsonDp")
    public void loginWithJsonTest(LoginModel lm) {
        printData(lm);
        loginActions(lm);
    }

    //    Utility methods
//methods used to show info related to login model
    private void printData(LoginModel lm) {
//        System.out.println("Access:username " + lm.getAccount().getUsername() +
//                        "/password: " + lm.getAccount().getPassword());
//        System.out.println("User error: " + lm.getUserError());
//        System.out.println("Password error: " + lm.getPasswordError());
        System.out.println(lm); //alternative way to print above data using LoginModel > toString() method
    }
    private void loginActions(LoginModel lm) {
        LoginPage loginPage = new LoginPage(driver);

//        open login page
        System.out.println("Open login page");
        driver.get(baseUrl + "/customer/account/login/");

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
