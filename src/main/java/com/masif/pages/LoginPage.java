package com.masif.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static com.masif.utils.BrowserUtils.submitButtonByCss;

//Page Object Model design pattern
public class LoginPage extends BasePage {
    private final String pagePath = "/customer/account/login/";
    private String pageURL = "https://masif.ro/customer/account/login/";
    private String usernameInputSelector = "login[username]";
    private String passwordInputSelector = "login[password]";
    private String submitButtonSelector = "#login-form > fieldset > div.actions-toolbar > div.primary";
    private String loginErrorSelector = "#maincontent > div.page.messages > div:nth-child(2) > div";
    private String emailErrorSelector = "email-error";
    private String passwordErrorSelector = "pass-error";
    private String loginSuccessSelector = "#maincontent > div.columns > div.column.main > div.page-title-wrapper > h1 > span";
    private By checkboxNameSelector = By.cssSelector("#remember-me-box");
    private By checkboxButton = By.className("checkbox");
    private By pageTitleSelector = By.cssSelector("[data-ui-id='page-title-wrapper']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String setPagePath() {
        return this.pagePath;
    }

    @Override
    public void verifyPageURL() {
        log.info("Assert: PageURL is: {}", getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }

    public WebElement getCheckboxNameSelector() {
        return driver.findElement(checkboxNameSelector);
    }

    @Step
    public void clickCheckbox() {
        WebElement checkbox = getCheckboxNameSelector();
        Actions actions = new Actions(driver);
        actions.click(checkbox).build().perform();
    }

    @Step
    public void login(String username, String password) {
        WebElement usernameInput = driver.findElement(By.name(usernameInputSelector));
        WebElement passwordInput = driver.findElement(By.name(passwordInputSelector));

        usernameInput.clear();
        log.info("Username input field is cleared.");
        usernameInput.sendKeys(username);
        log.info("Username {} is inserted.", username);
        passwordInput.clear();
        log.info("Password input field is cleared.");
        passwordInput.sendKeys(password);
        log.info("Password {} is inserted.", password);
        submitButtonByCss(submitButtonSelector);
        log.info("Submit button is clicked.");
    }

    @Step
    public String getLoginErrorSelectorText() {
        return driver.findElement(By.cssSelector(loginErrorSelector)).getText();
    }

    public String getPageTitleText() {
        return driver.findElement(pageTitleSelector).getText();
    }

    public void isLoginErrorDisplayed() {
        log.info("Assert that login error is displayed.");
        Assert.assertTrue(driver.findElement(By.cssSelector(loginErrorSelector)).isDisplayed());
    }

    @Step
    public String getLoginSuccessSelectorText() {
        return driver.findElement(By.cssSelector(loginSuccessSelector)).getText();
    }

    public String getPasswordErrorSelectorText() {
        return driver.findElement(By.id(passwordErrorSelector)).getText();
    }

    public String getEmailErrorSelectorText() {
        return driver.findElement(By.id(emailErrorSelector)).getText();
    }

    @Step
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
