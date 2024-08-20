package Tests;

import Pages.RegistrationPage;
import Utils.GenericUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {
    @Test
    public void RegistrationBasicTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get(baseUrl + registrationPage.setPagePath());
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
        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get(baseUrl + registrationPage.setPagePath());
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
    @Test(
            description = "Negative registration test with empty email, empty password",
            enabled = true,
            groups = {"Smoke"}
    )
    public void RegistrationTest02() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get(baseUrl + registrationPage.setPagePath());
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