package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static utils.BrowserUtils.waitUntilElementIsClickable;

public class BasePage implements Page {
    public final Logger log = LogManager.getLogger(getClass());
    public WebDriver driver;
    public WebDriverWait wait;
    private String pageURL;
    protected String pageTitle;
    private String pagePath;
    private By acceptCookiesButton = By.cssSelector("[id='btn-cookie-allow']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Step
    public void clickAcceptCookieButton(){
        getBaseElement(acceptCookiesButton).click();
        log.info("Click accept cookies button");
        reloadPage();
    }

    @Step
    public void reloadPage(){
        driver.navigate().refresh();
        log.info("Reloading the page");
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    @Override
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
        return pageTitle = driver.getTitle();
    }

    public WebElement getBaseElement(By element) {
        waitUntilElementIsClickable(driver.findElement(element));
        return driver.findElement(element);
    }

    public WebElement getElementFromList(By element) {
        List<WebElement> elementsList = driver.findElements(element);
        List<WebElement> limitedElementsList = elementsList.stream()
                .limit(5)
                .collect(Collectors.toList());
        //        waitUntilElementIsClickable(elem);
        return limitedElementsList.get(0);
    }

    public WebElement getSuccessMessageElement(By element) {
        return driver.findElement(element);
    }
}
