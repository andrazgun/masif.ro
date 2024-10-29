package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static Utils.BrowserUtils.clickButtonByCss;

public class AccountPage extends BasePage {
    private final String pagePath = "/customer/account/";
    private String pageURL = "https://masif.ro/customer/account/";
    private String editAccountPageSelector = "#maincontent > div.columns > div.column.main > div.block.block-dashboard-info > div.block-content > div.box.box-information > div.box-actions > a.action.edit";
    private String goToWishlistsSelector = "#block-collapsible-nav > ul > li:nth-child(5) > a";
    private String exitAccountSelector = "#block-collapsible-nav > ul > li:nth-child(9) > a";
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public String setPagePath() {
        return this.pagePath;
    }
    public void verifyPageURL() {
        System.out.println("Page URL is: " + getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }
    public void goToEditAccountPage() {
        driver.findElement(By.cssSelector(editAccountPageSelector)).click();
    }
    public void goToWishlists() {
        clickButtonByCss(goToWishlistsSelector);
    }

    public String getExitButtonText() {
        return driver.findElement(By.cssSelector(exitAccountSelector)).getText();
    }
    public void logOut() {
        clickButtonByCss(exitAccountSelector);
    }

}