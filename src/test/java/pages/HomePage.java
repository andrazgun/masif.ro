package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class HomePage extends BasePage {
    protected String pageURL = "https://masif.ro/";
    protected String pagePath = "";
    private String loginIcon = "[class='my-account']"; //id
    private String wishlistIcon = "[class='my-wishlist']"; //id
    private String productsNavigationSelector = "nav-trigger"; //class
    private String cartIcon = "[data-block='minicart']";
    private String minicartCloseButton = "[id='btn-minicart-close']";
    private String emptyCartSelector = "[class='subtitle empty']";
    private String electricToolsSelector = "#html-body > div.page-wrapper > header > div.page-navigation > div > div > div.col-xs-12.col-sm-3.col-md-3.col-lg-2.left-navigation > nav > ul > li.main-menu__item.main-menu__item--parent.scule-electrice.column3.image.parent--level0 > a"; //css
//    private By acceptCookiesButton = By.cssSelector("[id='btn-cookie-allow']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String setPagePath() {
        return pageURL + this.pagePath;
    }

    @Override
    public void verifyPageURL() {
        log.info("Assert that page URL is: {}", getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }

    @Step
    public void clickAcceptCookieButton(){
        super.clickAcceptCookieButton();
    }

    public String getEmptyCartSelectorText() {
        return driver.findElement(By.cssSelector(emptyCartSelector)).getText();
    }

    @Step
    public void clickLoginIcon() {
        List<WebElement> elements = driver.findElements(By.cssSelector(loginIcon));
        elements.get(0).click();
        log.info("Click Login icon");
    }

    @Step
    public void clickCartIcon() {
        driver.findElement(By.cssSelector(cartIcon)).click();
        log.info("Click Cart icon");
    }

    @Step
    public WebElement minicartCloseButton() {
        return driver.findElement(By.cssSelector(minicartCloseButton));
    }

    @Step
    public void clickMinicartCloseButton() {
        driver.findElement(By.cssSelector(minicartCloseButton)).click();
        log.info("Click Minicart close button");
    }

    @Step
    public void clickWishlistIcon() {
        List<WebElement> elements = driver.findElements(By.cssSelector(wishlistIcon));
        elements.get(0).click();
        log.info("Click on Wishlist icon");
    }

    @Step
    public void goToProductsNavigation() {
        driver.findElement(By.className(productsNavigationSelector)).click();
    }

    @Step
    public void goToElectricTools() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector(electricToolsSelector))).perform();
        driver.findElement(By.cssSelector(electricToolsSelector)).click();
    }
}
