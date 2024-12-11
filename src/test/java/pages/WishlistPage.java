package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class WishlistPage extends HomePage {
    private final String pagePath = "wishlist";
    private String pageURL = "https://masif.ro/wishlist";
    private String pageTitleSelector = "#maincontent > div.columns > div.column.main > div.page-title-wrapper > h1 > span";
    private By messageInfoEmptySelector = By.cssSelector("[class='message info empty']");
    private By productsComponentSelector = By.cssSelector("[data-row='product-item']");
    private By removeButton = By.cssSelector("[class='btn-remove action delete']");
    private By successMessage = By.cssSelector("[data-ui-id='message-success']");

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSuccessMessageElement() {
        return driver.findElement(successMessage);
    }

    @Override
    public String setPagePath() {
        return super.pageURL + this.pagePath;
    }

    @Override
    public void verifyPageURL() {
        log.info("Assert that page URL is: {}", getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }

    public String getPageTitleSelectorText() {
        return driver.findElement(By.cssSelector(pageTitleSelector)).getText();
    }

    public String getMessageInfoEmptyText() {
        return driver.findElement(messageInfoEmptySelector).getText();
    }

    public void clickRemoveButton() {
        getElementFromList(removeButton).click();
    }

    public WebElement getWishlistComponent() {
        return getElementFromList(productsComponentSelector);
    }
}