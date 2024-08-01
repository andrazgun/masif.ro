package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

//Page Object Model design pattern
public class LoginPage extends HomePage {

    private String pageURL = "https://masif.ro/customer/account/login/";

    private String usernameLabelSelector = "field email special required"; //class
    private String passwordLabelSelector = "field password special required"; //class
    private String usernameInputSelector = "#email"; //id
    private String passwordInputSelector = "password"; //id
    private String submitButtonSelector = "actions-toolbar"; //class
    private String checkboxSelector = "#remember-me-box"; //id
    private String usernameErrorSelector = "error";
    private String loginErrorSelector = "#maincontent > div.page.messages > div:nth-child(2) > div > div";
    private By checkboxName = By.cssSelector("#remember-me-box");
    private By checkboxButton = By.className("checkbox");


    public LoginPage(WebDriver driver) {super(driver);
    }
    @Override
    public void verifyPage() {
        System.out.println("Page URL is: " + getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }
    public void verifyIfCheckboxIsDisplayed() {
        Assert.assertTrue(getCheckboxButton().isDisplayed()); //assert if checkbox is displayed
    }

    private WebElement getCheckboxButton(){
        return driver.findElement(checkboxName);
    }

    public void login(String username, String password) {
        WebElement usernameInput = driver.findElement(By.id(usernameInputSelector));
        WebElement passwordInput = driver.findElement(By.id(passwordInputSelector));
        WebElement submitButton = driver.findElement(By.id(submitButtonSelector));

        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        submitButton.click();
    }
    public void clickCheckbox() {
        getCheckboxButton().click();
    }



}
