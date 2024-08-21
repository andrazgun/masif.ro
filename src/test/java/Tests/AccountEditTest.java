package Tests;

import Pages.AccountEditPage;
import Pages.AccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utils.GenericUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountEditTest extends BaseTest {
    @Test(
            description = "Edit firstName and lastName",
//            enabled = false,
            groups = {"Regression"}
    )
    public void AccountEditTest01() {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.verifyPageURL();
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPageURL();
        Assert.assertTrue(loginPage.getCheckboxNameSelector().isDisplayed());
        loginPage.login("agtest1@yopmail.com", "Test1234");
        AccountPage accountPage = new AccountPage(driver);
        accountPage.verifyPageURL();
        accountPage.goToEditAccountPage();
        AccountEditPage accountEditPage = new AccountEditPage(driver);
        accountEditPage.verifyPageURL();
        accountEditPage.editAccount(GenericUtils.createRandomStringTwo(7),GenericUtils.createRandomStringTwo(7));
        accountPage.verifyPageURL();
    }

}
