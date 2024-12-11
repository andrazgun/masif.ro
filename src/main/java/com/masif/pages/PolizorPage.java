package com.masif.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.masif.utils.BrowserUtils.waitUntilElementIsVisible;

public class PolizorPage extends BasePage {

    private final String pagePath = "/scule-electrice/fierastraie-electrice/";
    private String pageURL = "https://masif.ro/scule-electrice/polizoare-profesionale-si-semiprofesionale/";
    private String pageTitleSelector = "#page-title-heading > span";
    private String addProductToCartSelector = "#maincontent > div.columns > div.column.main > div.products.wrapper.grid.products-grid > ol > li:nth-child(1) > div > div.product.details.product-item-details > div.product-item-inner > div > form > button";
    private By addToWishlistButton = By.cssSelector("[class='product-item-actions']");
    private By productList = By.cssSelector("[class='products list items product-items']");

    public PolizorPage(WebDriver driver) {
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

    public void clickAddToWishlistButton() {
        waitUntilElementIsVisible(driver.findElement(addToWishlistButton));
        getElementFromList(addToWishlistButton).click();
    }

    public void addProductToCart() {
        driver.findElement(By.cssSelector(addProductToCartSelector)).click();
    }
}
