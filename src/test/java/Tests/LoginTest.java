package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import Utils.AllureTestListener;
import Utils.GenericUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Listeners({AllureTestListener.class})
@Epic("Smoke Tests")
@Feature("Login Tests")
public class LoginTest extends BaseTest {
    @DataProvider(name = "loginData")
    public Iterator<Object[]> loginDataProvider() {
        Collection<Object[]> dp = new ArrayList<>();
//         negative login - invalid email (empty)
        dp.add(new String[] {GenericUtils.createRandomStringTwo(0), GenericUtils.createRandomStringTwo(8), "Acesta este un câmp obligatoriu."});
//        negative login - valid email, invalid password (empty)
        dp.add(new String[] {GenericUtils.createRandomStringTwo(8), GenericUtils.createRandomStringTwo(8), "Introdu o adresă email validă (Ex: johndoe@domain.com)."});
//        positive login - valid email, valid password
        dp.add(new String[] {GenericUtils.createRandomStringTwo(8) + "@@yopmail.com", GenericUtils.createRandomStringTwo(8), "Introdu o adresă email validă (Ex: johndoe@domain.com)."});
        return dp.iterator();
    }
    @Test(
            description = "Negative login test with data provider",
            enabled = true,
            dataProvider = "loginData",
            groups = {"Smoke"}
    )
    public void LoginTestWithDataProvider(String username, String password, String assertMessage) {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.verifyPageURL();
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPageURL();
        Assert.assertTrue(loginPage.getCheckboxNameSelector().isDisplayed());
        System.out.println("URL is " + driver.getCurrentUrl());
        loginPage.login(username, password);
        System.out.println("Email error message is: " + loginPage.getEmailErrorSelectorText());
        Assert.assertEquals(loginPage.getEmailErrorSelectorText(), assertMessage);
    }
    @Test(
            description = "Negative login test with valid email, valid password but without existing account",
            groups = {"Regression"}
    )
    public void LoginNegativeTest01() {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.verifyPageURL();
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPageURL();
        Assert.assertTrue(loginPage.getCheckboxNameSelector().isDisplayed());
        loginPage.login("agtest2@yopmail.com", "Test1234");
        loginPage.isLoginErrorDisplayed();
    }
    @Test(
            description = "Negative login test with empty email, empty password",
            groups = {"Regression"}
    )
    public void LoginNegativeTest02() {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.verifyPageURL();
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPageURL();
        Assert.assertTrue(loginPage.getCheckboxNameSelector().isDisplayed());
        loginPage.login(GenericUtils.createRandomStringTwo(0), GenericUtils.createRandomStringTwo(0));
        System.out.println("Email error text is: " + loginPage.getEmailErrorSelectorText());
        Assert.assertEquals(loginPage.getEmailErrorSelectorText(),"Acesta este un câmp obligatoriu.");
        System.out.println("Password error text is: " + loginPage.getPasswordErrorSelectorText());
        Assert.assertEquals(loginPage.getPasswordErrorSelectorText(), "Acesta este un câmp obligatoriu.");
    }
    @Test(
            description = "Negative login test with valid email, empty password",
            groups = {"Regression"}
    )
    public void LoginNegativeTest03() {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.verifyPageURL();
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPageURL();
        Assert.assertTrue(loginPage.getCheckboxNameSelector().isDisplayed());
        loginPage.login(GenericUtils.createRandomStringTwo(4) + "@yopmail.com", GenericUtils.createRandomStringTwo(0));
        System.out.println("Password error text is: " + loginPage.getPasswordErrorSelectorText());
        Assert.assertEquals(loginPage.getPasswordErrorSelectorText(), "Acesta este un câmp obligatoriu.");
    }
    @Test(
            description = "Negative login test with valid email, valid password but without existing account",
            groups = {"Regression"}
    )
    public void LoginNegativeTest04() {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.verifyPageURL();
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPageURL();
        Assert.assertTrue(loginPage.getCheckboxNameSelector().isDisplayed());
        loginPage.login(GenericUtils.createRandomStringTwo(4) + "@yopmail.com", GenericUtils.createRandomStringTwo(4));
        System.out.println("Login error message is: " + loginPage.getLoginErrorSelectorText());
        Assert.assertEquals(loginPage.getLoginErrorSelectorText(), "Contul cu care ai încercat să te autentifici e incorect sau a fost dezactivat temporar. Te rugăm să aștepți și să încerci mai târziu.");
    }
    @Test (
            enabled = false,
            groups = {"Smoke"},
            dependsOnGroups = {"Regression"}
    )
    public void LoginPositiveTest() {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.verifyPageURL();
        homePage.goToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPageURL();
        Assert.assertTrue(loginPage.getCheckboxNameSelector().isDisplayed());
        loginPage.login("agtest1@yopmail.com", "Test1234");
        System.out.println(loginPage.getLoginSuccessSelectorText());
        Assert.assertEquals(loginPage.getLoginSuccessSelectorText(),"Contul meu");

    }
}