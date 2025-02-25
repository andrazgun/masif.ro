package com.masif.tests;

import com.masif.pages.AccountPage;
import com.masif.pages.HomePage;
import com.masif.pages.LoginPage;
import com.masif.tests.objectModels.LoginModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Epic("Smoke Tests")
@Feature("Login Positive Tests")
public class LoginPositiveTest extends BaseTest {
    @DataProvider(name = "jsonDataProvider")
    public Iterator<Object[]> jsonDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        File f = new File("src\\test\\resources\\data\\loginPositiveTestData.json");
        LoginModel[] lms = objectMapper.readValue(f, LoginModel[].class);
        for (LoginModel lm : lms)
            dp.add(new Object[]{lm});
        return dp.iterator();
    }

    private void printData(LoginModel lm) {
        System.out.println(lm);
    }

    @Test(
            dataProvider = "jsonDataProvider",
            description = "Login positive test with JSON data provider",
            groups = {"Regression"}
    )
    public void loginPositiveWithJsonDataProviderTest(LoginModel lm) {
        printData(lm);
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.verifyPageURL();
        homePage.clickLoginIcon();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPageURL();
        Assert.assertTrue(loginPage.getCheckboxNameSelector().isDisplayed());
        loginPage.login(lm.getAccount().getEmail(), lm.getAccount().getPassword());
        System.out.println(loginPage.getLoginSuccessSelectorText());
        Assert.assertEquals(loginPage.getLoginSuccessSelectorText(), "Contul meu");
        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickLogoutLink();
    }
}
