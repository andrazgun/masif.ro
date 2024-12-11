package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ElectricToolsPage extends HomePage {
    private final String pagePath = "scule-electrice/";
    //    private String pageURL = "https://masif.ro/scule-electrice/";
    private String pageTitleSelector = "#page-title-heading > span"; //css
    private By polizorPageCategory = By.cssSelector("[class='apptrian-subcategories-category-wrapper']");

    public ElectricToolsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String setPagePath() {
        return super.pageURL + this.pagePath;
    }

    @Override
    public void verifyPageURL() {
        System.out.println("Page URL is: " + getPageURL());
        Assert.assertEquals(getPageURL(), setPagePath());
    }

    @Step
    public String getPageTitleSelectorText() {
        return driver.findElement(By.cssSelector(pageTitleSelector)).getText();
    }

    public void clickPolizorCategory() {
        getElementFromList(polizorPageCategory).click();
    }
}
