package Pages;

import org.openqa.selenium.WebDriver;

public class AccountPage extends HomePage {

    private String pageURL = "https://masif.ro/customer/account/";


    public AccountPage(WebDriver driver) {
        super(driver);
    }
}
