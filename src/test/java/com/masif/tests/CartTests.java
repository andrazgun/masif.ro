package com.masif.tests;

import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.masif.pages.ElectricToolsPage;
import com.masif.pages.HomePage;

@Feature("Cart tests")
public class CartTests extends BaseTest {

    @Test(
            description = "Empty cart test for Guest",
            groups = {"Smoke"}
    )
    public void CartEmptyTest() {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.verifyPageURL();
        homePage.clickCartIcon();
        Assert.assertEquals(homePage.getEmptyCartSelectorText(),
                "Nu ai niciun articol în coșul de cumpărăturii");
        homePage.clickMinicartCloseButton();
        Assert.assertFalse(homePage.minicartCloseButton().isDisplayed());
    }

    @Test(
            description = "Empty cart test for Guest",
            groups = {"Smoke"}
    )
    public void CartNotEmptyTest() {
        ElectricToolsPage electricToolsPage = new ElectricToolsPage(driver);
        driver.get(electricToolsPage.setPagePath());
        electricToolsPage.verifyPageURL();

    }

}
