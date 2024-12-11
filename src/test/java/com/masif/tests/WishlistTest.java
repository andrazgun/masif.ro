package com.masif.tests;

import com.masif.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WishlistTest extends BaseTest {

    private HomePage homePage = new HomePage(driver);
    private WishlistPage wishlistPage = new WishlistPage(driver);
    private LoginPage loginPage = new LoginPage(driver);
    private ElectricToolsPage electricToolsPage = new ElectricToolsPage(driver);
    private PolizorPage polizorPage = new PolizorPage(driver);
    private AccountPage accountPage = new AccountPage(driver);

    @Test(
            description = "Wishlist test for registered user",
            groups = {"Regression"},
            enabled = true
    )
    public void WishlistTest01() {
        driver.get(baseUrl);
        homePage.verifyPageURL();
        homePage.clickAcceptCookieButton();
        homePage.clickWishlistIcon();
        loginPage.login("agtest1@yopmail.com", "Test1234");
        wishlistPage.verifyPageURL();
        Assert.assertEquals(wishlistPage.getPageTitleSelectorText(), "Favorite");
        homePage.goToProductsNavigation();
        homePage.goToElectricTools();
        electricToolsPage.verifyPageURL();
        Assert.assertEquals(electricToolsPage.getPageTitleSelectorText(), "Scule electrice");
        electricToolsPage.clickPolizorCategory();
        polizorPage.verifyPageURL();
        Assert.assertEquals(polizorPage.getPageTitleSelectorText(), "Polizoare profesionale si semiprofesionale");
        polizorPage.clickAddToWishlistButton();
        driver.get(wishlistPage.setPagePath());
        wishlistPage.verifyPageURL();
        Assert.assertTrue(wishlistPage.getWishlistComponent().isDisplayed());
        wishlistPage.clickRemoveButton();
        Assert.assertEquals(wishlistPage.getMessageInfoEmptyText(), "Nu ai nici un articol în lista de dorințe");
    }
}
