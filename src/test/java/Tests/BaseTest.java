package Tests;

import Utils.*;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import java.time.Duration;

public class BaseTest {
    public static String browser = GenericUtils.getBrowserFromConfig(ConstantUtils.CONFIG_FILE);
    public static WebDriver driver = WebDriverFactory.getDriver(browser);

    public String baseUrl = GenericUtils.createBaseUrl(ConstantUtils.CONFIG_FILE);
    protected String dbHostname, dbPort, dbUser, dbPassword, dbSchema;
    protected Base64 base64 = new Base64();

@BeforeClass
public void clearCookies() {
    driver.manage().deleteAllCookies();
}
    @BeforeTest (alwaysRun = true)
    public void beforeTest() {
//        System.out.println(baseUrl);
        dbHostname = ConfigUtils.getGenericValue(ConstantUtils.CONFIG_FILE, "dbHostname","");
        dbUser = ConfigUtils.getGenericValue(ConstantUtils.CONFIG_FILE, "dbUser","");
        dbPassword = ConfigUtils.getGenericValue(ConstantUtils.CONFIG_FILE, "dbPassword","");
        dbPort = ConfigUtils.getGenericValue(ConstantUtils.CONFIG_FILE, "dbPort","");
        dbSchema = ConfigUtils.getGenericValue(ConstantUtils.CONFIG_FILE, "dbSchema","");
        driver.manage().window().maximize();

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
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

