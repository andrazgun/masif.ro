package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class BasePage {
    public static final Logger logger = LogManager.getLogger(BasePage.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public String pagePath = "";
    private String pageURL;
    protected String pageTitle;
    public BasePage (WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//        PageFactory.initElements(driver, this); //use of PageFactory to make page elements objects when that specific object is needed to interact with
    }
//    public String getPagePath() { return pagePath;}
    public String setPagePath() {
        return this.pagePath;
    }
    public String getPageURL() {
        return pageURL = driver.getCurrentUrl();
    }
    public void verifyPageURL() {
        logger.info("Assert that page URL is: " + pageURL);
        Assert.assertEquals(getPageURL(), pageURL);
    }
    public String getPageTitle() {
        logger.info("Page title is: " + pageTitle);
        System.out.println("Page title is: " + pageTitle);
        return pageTitle = driver.getTitle();
    }


}
