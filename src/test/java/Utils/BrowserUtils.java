package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.util.HashMap;
import java.util.Map;

public class BrowserUtils {

    public static WebDriver getBrowser(String browser, String configfile) {

        switch (browser.toLowerCase()) {
            case ("chrome") : {
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("incognito"); //start in incognito
                options.addArguments("disable-popup-blocking");



//                 Setting download directory
                if(GenericUtils.isDownloadDirectoryEnabled(ConstantUtils.DOWNLOAD_DIRECTORY)) {
                    Map<String, Object> chromePref = new HashMap<>();
                    chromePref.put("download.default_directory", "src//test//java//Resources");
                    options.setExperimentalOption("prefs", chromePref);
                    }


                return new ChromeDriver(options);
            }
            case ("firefox") : {
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions options = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile();
                if(GenericUtils.isDownloadDirectoryEnabled(ConstantUtils.DOWNLOAD_DIRECTORY)) {
                    profile.setPreference("browser.download.dir", ConstantUtils.DOWNLOAD_DIRECTORY);
                }

                options.setProfile(profile);

                WebDriver driver = new FirefoxDriver(options);

                return driver;
            }
            case ("edge") : {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            default : {
                System.out.println("Driver not supported");
                return WebDriverManager.getInstance(browser).getWebDriver();
            }

        }

    }


}
