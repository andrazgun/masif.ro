package tests;

import pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WishlistTest extends BaseTest {
    @Test(
            description = "empty wishlist test",
            groups = {"Regression"},
            enabled = true
    )
    public void WishlistTest01() {
        driver.get(baseUrl);
        HomePage homePage = new HomePage(driver);
        homePage.verifyPageURL();
        homePage.goToWishlistPage();
        WishlistPage wishlistPage = new WishlistPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("agtest1@yopmail.com", "Test1234");
        wishlistPage.verifyPageURL();
        Assert.assertEquals(wishlistPage.getPageTitleSelectorText(), "Favorite");
        Assert.assertEquals(wishlistPage.getMessageInfoEmptyText(),"Nu ai nici un articol în lista de dorințe");
        homePage.goToProductsNavigation();
        homePage.goToElectricTools();
        ElectricToolsPage electricToolsPage = new ElectricToolsPage(driver);
        electricToolsPage.verifyPageURL();
        Assert.assertEquals(electricToolsPage.getPageTitleSelectorText(),"Scule electrice");
        electricToolsPage.goToPolizorPage();
        PolizorPage polizorPage = new PolizorPage(driver);
        polizorPage.verifyPageURL();
        Assert.assertEquals(polizorPage.getPageTitleSelectorText(), "Polizoare profesionale si semiprofesionale");
        polizorPage.addProductToWishlist();
        driver.get(baseUrl+wishlistPage.setPagePath());
        wishlistPage.isProductComponentDisplayed();
        Assert.assertEquals(wishlistPage.getMessageInfoEmptyText(),"Nu ai nici un articol în lista de dorințe");
    }
}
