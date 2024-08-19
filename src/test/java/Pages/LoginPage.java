package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//Page Object Model design pattern
public class LoginPage extends BasePage {

    private String pageURL = "https://masif.ro/customer/account/login/";
    private String usernameInputSelector = "login[username]"; //name
    private String passwordInputSelector = "login[password]"; //name
    private String submitButtonSelector = "#login-form > fieldset > div.actions-toolbar > div.primary"; //css
    private String loginErrorSelector = "#maincontent > div.page.messages > div:nth-child(2) > div"; //css
    private String emailErrorSelector = "email-error";
    private String passwordErrorSelector = "pass-error";
    private String loginSuccessSelector = "#maincontent > div.columns > div.column.main > div.page-title-wrapper > h1 > span";
    private By checkboxNameSelector = By.cssSelector("#remember-me-box");
    private By checkboxButton = By.className("checkbox");


    public LoginPage(WebDriver driver)
    {
        super(driver);
    }
    @Override
    public void verifyPageURL() {
        System.out.println("Page URL is: " + getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }
    public WebElement getCheckboxNameSelector(){
        return driver.findElement(checkboxNameSelector);
    }
    public void clickCheckbox() {
        WebElement checkbox = getCheckboxNameSelector();
        Actions actions = new Actions(driver);
        actions.click(checkbox).build().perform();
    }
    public void login(String username, String password) {
        WebElement usernameInput = driver.findElement(By.name(usernameInputSelector));
        WebElement passwordInput = driver.findElement(By.name(passwordInputSelector));
        WebElement submitButton = driver.findElement(By.cssSelector(submitButtonSelector));

        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
//        clickCheckbox();
        submitButton.submit();
    }
    public String getLoginErrorSelectorText() {
        return driver.findElement(By.cssSelector(loginErrorSelector)).getText();
    }
    public void isLoginErrorDisplayed() {
        Assert.assertTrue(driver.findElement(By.cssSelector(loginErrorSelector)).isDisplayed());
    }
    public String getLoginSuccessSelectorText() {
        return driver.findElement(By.cssSelector(loginSuccessSelector)).getText();
    }
    public String getPasswordErrorSelectorText() {
        return driver.findElement(By.id(passwordErrorSelector)).getText();
    }
    public String getEmailErrorSelectorText() {
        return driver.findElement(By.id(emailErrorSelector)).getText();
    }

}
