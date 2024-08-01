package Pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegistrationPage extends HomePage {

    private String pageURL = "https://masif.ro/customer/account/create/";


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    public void verifyPage() {
        System.out.println("Page URL is: " + getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }

}
