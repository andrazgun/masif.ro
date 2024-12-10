package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static utils.BrowserUtils.*;

public class AccountPage extends BasePage {
    private final String pagePath = "/customer/account/";
    private String pageURL = "https://masif.ro/customer/account/";
    private String editAccountLinkSelector = "#maincontent > div.columns > div.column.main > div.block.block-dashboard-info > div.block-content > div.box.box-information > div.box-actions > a.action.edit";
    private String wishlistsLinkSelector = "#block-collapsible-nav > ul > li:nth-child(5) > a";
    private String logoutLinkSelector = "a[href='https://masif.ro/customer/account/logout/']";
    private String successMessage = "[data-ui-id='message-success']";

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessMessageText() {
        return driver.findElement(By.cssSelector(successMessage)).getText();
    }

    public String setPagePath() {
        return this.pagePath;
    }

    public void verifyPageURL() {
        System.out.println("Page URL is: " + getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }

    public void clickEditAccountLink() {
        driver.findElement(By.cssSelector(editAccountLinkSelector)).click();
    }

    public void clickWishlistsLink() {
        clickButtonByCss(wishlistsLinkSelector);
    }

    public void clickLogoutLink() {
        driver.findElement(By.cssSelector(logoutLinkSelector)).click();
    }
}