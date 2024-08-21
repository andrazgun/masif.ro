package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountPage extends BasePage {
    private final String pagePath = "/customer/account/";
    private String pageURL = "https://masif.ro/customer/account/";
    private String editAccountPageSelector = "#maincontent > div.columns > div.column.main > div.block.block-dashboard-info > div.block-content > div.box.box-information > div.box-actions > a.action.edit";
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

}