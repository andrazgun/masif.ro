package Tests;

import Pages.RegistrationPage;
import Utils.AllureTestListener;
import Utils.GenericUtils;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({AllureTestListener.class})
//@Epic("Smoke Tests")
@Feature("Registration Negative Tests")
public class RegistrationNegativeTest extends BaseTest {
    @Test(
            description = "Negative registration test with invalid email and invalid password",
            groups = {"Smoke"}
    )
            public void RegistrationTest01() {
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
    @Test(
            description = "Negative registration test with invalid password",
            groups = {"Smoke"}
    )
    public void RegistrationTest02() {
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
            description = "Negative registration test with empty data",
            groups = {"Smoke"}
    )
    public void RegistrationTest03() {
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