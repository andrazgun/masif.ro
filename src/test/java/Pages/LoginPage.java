package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static Utils.BrowserUtils.submitButtonByCss;

//Page Object Model design pattern
public class LoginPage extends BasePage {
    private final String pagePath = "/customer/account/login/";
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
    public String setPagePath() {
        return this.pagePath;
    }
    @Override
    public void verifyPageURL() {
        logger.info("Assert that page URL is:" + getPageURL());
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

        usernameInput.clear();
        logger.info("Username input is cleared.");
        usernameInput.sendKeys(username);
        logger.info("Username is inserted.");
        passwordInput.clear();
        logger.info("Password input field is cleared.");
        passwordInput.sendKeys(password);
        logger.info("Password is inserted.");
//        clickCheckbox();
        submitButtonByCss(submitButtonSelector);
        logger.info("Submit button is clicked.");

    }

    public String getLoginErrorSelectorText() {
        return driver.findElement(By.cssSelector(loginErrorSelector)).getText();
    }
    public void isLoginErrorDisplayed() {
        logger.info("Assert that login error is displayed.");
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

    public boolean checkErr(String expectedErr, String errorType) {
        if (errorType.equalsIgnoreCase("emailErr")) {
            if (expectedErr.length() > 0) { // if text is displayed, execute if code block
                System.out.println("Actual email error:" + getEmailErrorSelectorText());
                return expectedErr.equals(getEmailErrorSelectorText());
            } else return true;
        } else if (errorType.equalsIgnoreCase("passErr")) {
            if (expectedErr.length() > 0) {
                System.out.println("Actual pass error:" + getPasswordErrorSelectorText());
                return expectedErr.equalsIgnoreCase(getPasswordErrorSelectorText());
            } else return true;
        }
        return false;
    }
}
