package Pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

public class BasePage {

    public WebDriver driver;
    @Getter
    public String pageURL;

    public BasePage (WebDriver driver) {
        this.driver = driver;
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }
}
