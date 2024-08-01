package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.RegistrationPage;
import org.testng.annotations.Test;

public class RegistrationTest extends LoginTest {

    @Test
    public void RegistrationBasicTest() {

        driver.get(baseUrl + "/customer/account/create/");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.verifyPage();


    }

}