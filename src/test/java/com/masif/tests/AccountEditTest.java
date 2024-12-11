package com.masif.tests;

import io.qameta.allure.Feature;
import com.masif.pages.AccountEditPage;
import com.masif.pages.AccountPage;
import com.masif.pages.HomePage;
import com.masif.pages.LoginPage;
import com.masif.utils.GenericUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Account: Editing first name, last name")
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
        homePage.clickLoginIcon();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPageURL();
        Assert.assertTrue(loginPage.getCheckboxNameSelector().isDisplayed());
        loginPage.login("agtest1@yopmail.com", "Test1234");
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickEditAccountLink();
        AccountEditPage accountEditPage = new AccountEditPage(driver);
        accountEditPage.editAccount(GenericUtils.createRandomStringTwo(7),GenericUtils.createRandomStringTwo(7));
        Assert.assertEquals(accountPage.getSuccessMessageText(),"Ați salvat informațiile de cont");
    }
}
