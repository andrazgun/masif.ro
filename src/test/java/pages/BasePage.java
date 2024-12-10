package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class BasePage implements Page{
    public final Logger log = LogManager.getLogger(getClass());
    public WebDriver driver;
    public WebDriverWait wait;
    private String pageURL;
    protected String pageTitle;
    private String pagePath;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    public void verifyPageURL() {
        log.info("Assert that page URL is: {}", pageURL);
        Assert.assertEquals(getPageURL(), pageURL);
    }

    @Override
    public String setPagePath() {
        return pageURL + this.pagePath;
    }

    public String getPageTitle() {
        log.info("Page title is: {}", pageTitle);
        System.out.println("Page title is: " + pageTitle);
        return pageTitle = driver.getTitle();
    }
}
