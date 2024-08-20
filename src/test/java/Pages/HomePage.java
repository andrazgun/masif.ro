package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends BasePage {
    private String pageURL = "https://masif.ro/";
    private String loginPageIconSelector = "my-account";
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @Override
    public void verifyPageURL() {
        System.out.println("Page URL is: " + getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }
    public void goToLoginPage() {
        driver.findElement(By.className(loginPageIconSelector)).click();
    }

}
