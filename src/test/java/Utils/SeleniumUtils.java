package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Set;
import java.util.regex.Pattern;

public class SeleniumUtils {

    //    method for explicit wait
    public static WebElement waitForGenericElement(WebDriver driver, By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
//method for wait for some text to be displayed on webpage and return true if so
    public static Boolean waitforTextOnSite(WebDriver driver, By locator, Duration timeout, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        Pattern patt = Pattern.compile(expectedText);
        return wait.until(ExpectedConditions.textMatches(locator, patt));
    }

    //    generic method to find a cookie by name
    public static Boolean checkIfCookieExistsByName(WebDriver driver, String cookieName) {
        Set<Cookie> cookies = driver.manage().getCookies();
        Boolean found = false;
        for (Cookie c:cookies) {
            if (c.getName().equalsIgnoreCase(cookieName)) {
                return true;
            }
        }
        return false;
    }

    public static void takeScreenshot(WebDriver driver) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.getTime()); //convert timestamp to long
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(ConstantUtils.SCREENSHOT_DIRECTORY + "screenshot_" + timestamp.getTime() + ".png"));
        } catch (IOException e) {
            System.out.println("File not saved");
        }
    }


}
