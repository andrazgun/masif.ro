package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PolizorPageq extends BasePage {

    private final String pagePath = "/scule-electrice/polizoare-profesionale-si-semiprofesionale/";
    private String pageURL = "https://masif.ro/scule-electrice/polizoare-profesionale-si-semiprofesionale/";
    private String pageTitleSelector = "#page-title-heading > span"; //css
    private String addProductToWishlistSelector = "#maincontent > div.columns > div.column.main > div.products.wrapper.grid.products-grid > ol > li:nth-child(1) > div > div.product.photo.product-item-photo > div > div > a"; //css
    private String addProductToCartSelector = "#maincontent > div.columns > div.column.main > div.products.wrapper.grid.products-grid > ol > li:nth-child(1) > div > div.product.details.product-item-details > div.product-item-inner > div > form > button"; //css

    public PolizorPageq(WebDriver driver) {
        super(driver);
    }
    public String setPagePath() {
        return this.pagePath;
    }
    public void verifyPageURL() {
        System.out.println("Page URL is: " + getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }
    public String getPageTitleSelectorText() {
        return driver.findElement(By.cssSelector(pageTitleSelector)).getText();
    }
    public void addProductToWishlist() {
        driver.findElement(By.cssSelector(addProductToWishlistSelector)).click();
    }
    public void addProductToCart() {
        driver.findElement(By.cssSelector(addProductToCartSelector)).click();
    }
}
