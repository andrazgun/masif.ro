package tests;

import utils.*;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.time.Duration;

public class BaseTest {
    public static String browser = GenericUtils.getBrowserFromConfig(Constants.CONFIG_FILE);
    public static WebDriver driver = WebDriverFactory.getDriver(browser);

    public String baseUrl = GenericUtils.createBaseUrl(Constants.CONFIG_FILE);
    protected String dbHostname, dbPort, dbUser, dbPassword, dbSchema;
    protected Base64 base64 = new Base64();

@BeforeClass
public void clearCookies() {
    driver.manage().deleteAllCookies();
}
    @BeforeTest (alwaysRun = true)
    public void beforeTest() {
        dbHostname = ConfigUtils.getGenericValue(Constants.CONFIG_FILE, "dbHostname","");
        dbUser = ConfigUtils.getGenericValue(Constants.CONFIG_FILE, "dbUser","");
        dbPassword = ConfigUtils.getGenericValue(Constants.CONFIG_FILE, "dbPassword","");
        dbPort = ConfigUtils.getGenericValue(Constants.CONFIG_FILE, "dbPort","");
        dbSchema = ConfigUtils.getGenericValue(Constants.CONFIG_FILE, "dbSchema","");
        driver.manage().window().maximize();
    }
    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterTest(enabled = false)
    public void afterTest() {
            driver.quit();
    }

    @AfterClass(enabled = false)
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}

