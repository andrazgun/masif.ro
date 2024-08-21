package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static Utils.BrowserUtils.clickSubmitButtonByCss;

public class AccountEditPage extends BasePage {
    private final String pagePath = "/customer/account/edit/";
    private String pageURL = "https://masif.ro/customer/account/edit/";
    private String firstNameSelector = "firstname"; //id
    private String lastNameSelector = "lastname"; //id
    public AccountEditPage(WebDriver driver) {
        super(driver);
    }
    private String saveButtonSelector = "#form-validate > div > div.primary > button"; //css
    public String setPagePath() {
        return this.pagePath;
    }
    public void verifyPageURL() {
        System.out.println("Page URL is: " + getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }
    public void editAccount(String firstName, String lastName){

        WebElement firstNameInput = driver.findElement(By.id(firstNameSelector));
        WebElement lastNameInput = driver.findElement(By.id(lastNameSelector));

        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        clickSubmitButtonByCss(saveButtonSelector);
    }
}
