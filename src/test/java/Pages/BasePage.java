package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    protected String pageURL;
    protected String pageTitle;

    public BasePage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//        PageFactory.initElements(driver, this); //use of PageFactory to make page elements objects when that specific object is needed to interact with
    }

    public String getPageURL() {
        return pageURL = driver.getCurrentUrl();
    }
    public void verifyPageURL() {
        System.out.println("Page URL is: " + pageURL);
        Assert.assertEquals(getPageURL(), pageURL);
    }
    public String getPageTitle() {
        System.out.println("Page title is: " + pageTitle);
        return pageTitle = driver.getTitle();
    }

}
