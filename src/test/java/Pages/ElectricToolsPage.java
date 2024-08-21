package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ElectricToolsPage extends BasePage {
    private final String pagePath = "/scule-electrice/";
    private String pageURL = "https://masif.ro/scule-electrice/";
    private String pageTitleSelector = "#page-title-heading > span"; //css
    private String polizorPageSelector = "#maincontent > div.columns > div > ul > li:nth-child(1) > div > div.apptrian-subcategories-category-name"; //css a[href

    public ElectricToolsPage(WebDriver driver) {
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
    public void goToPolizorPage() {
        driver.findElement(By.cssSelector(polizorPageSelector)).click();
    }
}
