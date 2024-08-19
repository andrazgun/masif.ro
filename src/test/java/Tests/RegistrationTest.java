package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.RegistrationPage;
import Utils.GenericUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class RegistrationTest extends BaseTest {
    @Test
    public void RegistrationBasicTest() {

        driver.get(baseUrl + "/customer/account/create/");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.verifyPageURL();
        registrationPage.verifyPageTitle();
        registrationPage.createAccount(
                GenericUtils.createRandomStringTwo(5),
                GenericUtils.createRandomStringTwo(5),
                GenericUtils.createRandomEmail(5),
                GenericUtils.createRandomStringTwo(8),
                GenericUtils.createRandomStringTwo(8)
                );
//        Assert.assertEquals(registrationPage.getPasswordAgainErrorText(),"Adauga aceasi valoare din nou.");
    }

    @Test
    public void RegistrationTest01() {

        driver.get(baseUrl + "/customer/account/create/");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.verifyPageURL();
        registrationPage.verifyPageTitle();
        registrationPage.createAccount(
                "firstName",
                "lastName",
                "agtest@yopmail.com",
                "123ABCabc@",
                "123ABCabc@!"
        );
        Assert.assertEquals(registrationPage.getPasswordAgainErrorText(),"Adauga aceasi valoare din nou..");
    }

}