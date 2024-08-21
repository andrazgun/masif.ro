package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class WishlistPage extends BasePage {
    private final String pagePath = "/wishlist";
    private String pageURL = "https://masif.ro/wishlist";
    private String pageTitleSelector = "#maincontent > div.columns > div.column.main > div.page-title-wrapper > h1 > span"; //css
    private String messageInfoEmptySelector = "#wishlist-view-form > div.message.info.empty"; //css
    public WishlistPage(WebDriver driver) {
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
    public String getMessageInfoEmptyText() {
        return driver.findElement(By.cssSelector(messageInfoEmptySelector)).getText();
    }



}
