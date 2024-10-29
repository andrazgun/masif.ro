package Tests;

import Pages.AccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utils.AllureTestListener;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({AllureTestListener.class})
@Epic("Smoke Tests")
@Feature("Login Positive Tests")
public class LoginPositiveTest extends BaseTest {
    @Test(
            description = "Positive login test",
            enabled = true,
            groups = {"Smoke"}
    )
    public void LoginPositiveTest01() {
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

        AccountPage accountPage = new AccountPage(driver);
        accountPage.logOut();

    }
}
