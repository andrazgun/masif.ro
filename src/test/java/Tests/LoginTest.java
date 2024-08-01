package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void LoginBasicTest() throws InterruptedException {

        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.verifyPage();
        homePage.goToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPage();
        loginPage.verifyIfCheckboxIsDisplayed();
//        loginPage.login("agtest1@yopmail.com", "Test123");

    }

}