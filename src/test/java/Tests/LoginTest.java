package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void LoginBasicTest() {

        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.verifyPage();
        homePage.goToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPage();
        loginPage.isCheckboxIsDisplayed();
        loginPage.login("agtest1@yopmail.com", "Test123");
        loginPage.isLoginErrorDisplayed();

//        RegistrationPage registrationPage = new RegistrationPage(driver);
//        registrationPage.verifyPage();

    }

}