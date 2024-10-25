package Tests;

import Utils.*;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.time.Duration;

public class BaseTest {
    public static String browser = GenericUtils.getBrowserFromConfig(ConstantUtils.CONFIG_FILE);
    public static WebDriver driver = WebDriverFactory.getDriver(browser);
    public String baseUrl = GenericUtils.createBaseUrl(ConstantUtils.CONFIG_FILE);
    protected String dbHostname, dbPort, dbUser, dbPassword, dbSchema;
    protected Base64 base64 = new Base64();

    @BeforeTest
//            (
//            groups = {"Smoke","Regression"}
//            )
    public void beforeTest() {
//        System.out.println(baseUrl);
        dbHostname = ConfigUtils.getGenericValue(ConstantUtils.CONFIG_FILE, "dbHostname","");
        dbUser = ConfigUtils.getGenericValue(ConstantUtils.CONFIG_FILE, "dbUser","");
        dbPassword = ConfigUtils.getGenericValue(ConstantUtils.CONFIG_FILE, "dbPassword","");
        dbPort = ConfigUtils.getGenericValue(ConstantUtils.CONFIG_FILE, "dbPort","");
        dbSchema = ConfigUtils.getGenericValue(ConstantUtils.CONFIG_FILE, "dbSchema","");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }
    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterTest(enabled = true)
    public void afterTest() {
        driver.quit();

    }

}

