package com.masif.tests;

import io.qameta.allure.Epic;
import com.masif.pages.RegistrationPage;
import com.masif.utils.GenericUtils;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Smoke Tests")
@Feature("Registration Negative Tests")
public class RegistrationNegativeTest extends BaseTest {
    @Test(
            description = "Negative registration test with invalid email and invalid password",
            groups = {"Regression"}
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
        Assert.assertEquals(registrationPage.getPasswordConfirmationErrorText(), "Adauga aceasi valoare din nou.");
    }

    @Test(
            description = "Negative registration test with invalid password",
            groups = {"Regression"}
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
        Assert.assertEquals(registrationPage.getPasswordConfirmationErrorText(), "Adauga aceasi valoare din nou.");
    }

    @Test(
            description = "Negative registration test with empty data",
            groups = {"Regression"}
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
        Assert.assertEquals(registrationPage.getFirstnameErrorText(), "Acesta este un câmp obligatoriu.");
        Assert.assertEquals(registrationPage.getLastnameErrorText(), "Acesta este un câmp obligatoriu.");
        Assert.assertEquals(registrationPage.getEmailErrorText(), "Acesta este un câmp obligatoriu.");
        Assert.assertEquals(registrationPage.getPasswordErrorText(), "Acesta este un câmp obligatoriu.");
        Assert.assertEquals(registrationPage.getPasswordConfirmationErrorText(), "Acesta este un câmp obligatoriu.");
    }
}