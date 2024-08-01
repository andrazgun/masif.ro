package Pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

    public WebDriver driver;

    public BasePage (WebDriver driver) {
        this.driver = driver;
    }
    public String getPageURL() {
        return driver.getCurrentUrl();
    }

}
