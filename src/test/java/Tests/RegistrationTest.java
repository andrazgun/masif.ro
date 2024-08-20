package Tests;

import Pages.RegistrationPage;
import Utils.GenericUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


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
        Assert.assertEquals(registrationPage.getPasswordConfirmationErrorText(),"Adauga aceasi valoare din nou.");
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
        Assert.assertEquals(registrationPage.getPasswordConfirmationErrorText(),"Adauga aceasi valoare din nou.");
    }
    @Test
    public void RegistrationTest02() {

        driver.get(baseUrl + "/customer/account/create/");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.verifyPageURL();
        registrationPage.verifyPageTitle();
        registrationPage.createAccount(
                "",
                "",
                "",
                "",
                ""
        );
        System.out.println(registrationPage.getFirstnameErrorText());
        Assert.assertEquals(registrationPage.getFirstnameErrorText(),"Acesta este un câmp obligatoriu.");
        Assert.assertEquals(registrationPage.getLastnameErrorText(),"Acesta este un câmp obligatoriu.");
        Assert.assertEquals(registrationPage.getEmailErrorText(),"Acesta este un câmp obligatoriu.");
        Assert.assertEquals(registrationPage.getPasswordErrorText(),"Acesta este un câmp obligatoriu.");
        Assert.assertEquals(registrationPage.getPasswordConfirmationErrorText(),"Acesta este un câmp obligatoriu.");
    }



}