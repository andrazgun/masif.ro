package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class HomePage extends BasePage {
    private String pageURL = "https://masif.ro/";
    private String loginPageIconSelector = "my-account"; //id
    private String wishlistPageIconSelector = "my-wishlist"; //id
    private String productsNavigationSelector = "nav-trigger"; //class
    private String electricToolsSelector = "#html-body > div.page-wrapper > header > div.page-navigation > div > div > div.col-xs-12.col-sm-3.col-md-3.col-lg-2.left-navigation > nav > ul > li.main-menu__item.main-menu__item--parent.scule-electrice.column3.image.parent--level0 > a"; //css
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @Override
    public void verifyPageURL() {
        logger.info("Assert that page URL is:" + getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }
    public void goToLoginPage() {
        driver.findElement(By.className(loginPageIconSelector)).click();
        logger.info("Click on Login link");
    }
    public void goToWishlistPage() {
        driver.findElement(By.className(wishlistPageIconSelector)).click();
    }
    public void goToProductsNavigation() {
        driver.findElement(By.className(productsNavigationSelector)).click();
    }
    public void goToElectricTools() {
//        driver.findElement(By.cssSelector(electricToolsSelector)).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector(electricToolsSelector))).perform();
        driver.findElement(By.cssSelector(electricToolsSelector)).click();
    }

}
