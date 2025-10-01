package com.masif.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.masif.pages.ElectricToolsPage;
import com.masif.pages.HomePage;

@Feature("Cart tests")
public class CartTests extends BaseTest {
    private HomePage homePage = new HomePage(driver);
    private ElectricToolsPage electricToolsPage = new ElectricToolsPage(driver);

    @Step
    @Test(
            description = "Empty cart test for Guest",
            groups = {"Smoke"}
    )
    public void CartEmptyTest() {
        driver.get(baseUrl);
        homePage.verifyPageURL();
        homePage.clickCartIcon();
        Assert.assertEquals(homePage.getEmptyCartSelectorText(),
                "Nu ai niciun articol în coșul de cumpărăturii");
        homePage.clickMinicartCloseButton();
        Assert.assertFalse(homePage.minicartCloseButton().isDisplayed());
    }

    @Step
    @Test(
            description = "Empty cart test for Guest",
            groups = {"Smoke"}
    )
    public void CartNotEmptyTest() {
        driver.get(electricToolsPage.setPagePath());
        electricToolsPage.verifyPageURL();
    }
}
