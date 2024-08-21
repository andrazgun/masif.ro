package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import static Tests.BaseTest.driver;

public class BrowserUtils {
    public static WebDriver getBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome" : {
                 return WebDriverManager.getInstance(browser).getWebDriver();
            }
            case "firefox" : {
                WebDriverManager.firefoxdriver().setup();
                return WebDriverManager.getInstance(browser).getWebDriver();
            }
            case "edge" : {
                WebDriverManager.edgedriver().setup();
                return WebDriverManager.getInstance(browser).getWebDriver();
            }
            default : {
                System.out.println("Driver not supported");
                return null;
            }
        }
    }

    public static WebDriver getBrowserFromConfigFile(String browser, String configfile) {

        switch (browser.toLowerCase()) {
            case "chrome" : {
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("incognito"); //start in incognito
                options.addArguments("disable-popup-blocking");



//                 Setting download directory
                if(GenericUtils.isDownloadDirectoryEnabled(configfile)) {
                    Map<String, Object> chromePref = new HashMap<>();
                    chromePref.put("download.default_directory", "src//test//java//Resources");
                    options.setExperimentalOption("prefs", chromePref);
                    }


                return new ChromeDriver(options);
            }
            case "firefox" : {
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions options = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile();
                if(GenericUtils.isDownloadDirectoryEnabled(configfile)) {
                    profile.setPreference("browser.download.dir", ConstantUtils.DOWNLOAD_DIRECTORY);
                }

                options.setProfile(profile);

                return new FirefoxDriver(options);
            }
            case "edge" : {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            default : {
                System.out.println("Driver not supported");
                return WebDriverManager.getInstance(browser).getWebDriver();
            }

        }

    }

    public static void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public static void scrollScreenDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, document.documentElement.clientHeight)");
    }

    public static void waitUntilElementIsClickable(WebElement element) {
        scrollIntoView(element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementIsVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void submitButtonByCss(String css) {
        WebElement submitButton = driver.findElement(By.cssSelector(css));
        waitUntilElementIsClickable(submitButton);
        submitButton.submit();
    }

}
