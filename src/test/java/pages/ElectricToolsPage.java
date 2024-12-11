package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ElectricToolsPage extends HomePage {
    private final String pagePath = "/scule-electrice/";
//    private String pageURL = "https://masif.ro/scule-electrice/";
    private String pageTitleSelector = "#page-title-heading > span"; //css
    private String polizorPageSelector = "#maincontent > div.columns > div > ul > li:nth-child(1) > div > div.apptrian-subcategories-category-image > a";

    public ElectricToolsPage(WebDriver driver) {
        super(driver);
    }

    public String setPagePath() {
        return super.pageURL + this.pagePath;
    }

    @Override
    public void verifyPageURL() {
        System.out.println("Page URL is: " + getPageURL());
        Assert.assertEquals(getPageURL(), setPagePath());
    }

    public String getPageTitleSelectorText() {
        return driver.findElement(By.cssSelector(pageTitleSelector)).getText();
    }

    public void goToPolizorPage() {
        driver.findElement(By.cssSelector(polizorPageSelector)).click();
    }
}