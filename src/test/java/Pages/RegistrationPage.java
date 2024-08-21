package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import static Utils.BrowserUtils.*;
import static Utils.SeleniumUtils.implicitWait;

public class RegistrationPage extends BasePage {
    private final String pagePath = "/customer/account/create/";
    private String pageURL = "https://masif.ro/customer/account/create/";
    private String pageTitle = "Creeaza-ti cont | MASIF";
    private String firstNameInputSelector = "firstname"; //id
    private String lastNameInputSelector = "lastname";
    private String emailInputSelector = "email_address";
    private String passwordInputSelector = "password";
    private String passwordAgainInputSelector = "password-confirmation";
    private String firstNameErrorSelector = "firstname-error"; //id
    private String lastNameErrorSelector = "lastname-error"; //id
    private String emailErrorSelector = "email_address-error"; //id
    private String passwordErrorSelector = "password-error";
    private String passwordConfirmationErrorSelector = "password-confirmation-error";
//    private String submitButtonSelector = "//*[@class=\"create-account-container\"]//*[@class=\"primary\"]"; //xpath
    private String submitButtonSelector = "#form-validate > div.actions-toolbar"; //css
    private String goToLoginPage = "Intra in cont aici";
    String createAccountErrorSelector;
    String createAccountSuccessSelector;


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    public String setPagePath() {
        return this.pagePath;
    }
    public void verifyPageURL() {
        System.out.println("Page URL is: " + getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }
    public String getPageTitle() {
        return pageTitle = driver.getTitle();
    }
    public void verifyPageTitle() {
        System.out.println("Page title is: " + getPageTitle());
        Assert.assertEquals(getPageTitle(), pageTitle);
    }

    public void createAccount(String firstName,
                              String lastName,
                              String email,
                              String password,
                              String passwordAgain) {
        WebElement body = driver.findElement(By.tagName("body"));
        WebElement firstnameElement = driver.findElement(By.id(firstNameInputSelector));
        WebElement lastNameElement = driver.findElement(By.id(lastNameInputSelector));
        WebElement emailInputElement = driver.findElement(By.id(emailInputSelector));
        WebElement passwordInputElement = driver.findElement(By.id(passwordInputSelector));
        WebElement passwordAgainInputElement = driver.findElement(By.id(passwordAgainInputSelector));

        firstnameElement.clear();
        firstnameElement.sendKeys(firstName);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
        emailInputElement.clear();
        emailInputElement.sendKeys(email);
        passwordInputElement.clear();
        passwordInputElement.sendKeys(password);
        passwordAgainInputElement.clear();
        passwordAgainInputElement.sendKeys(passwordAgain);
//        body.sendKeys(Keys.PAGE_DOWN);
        submitButtonByCss(submitButtonSelector);
        implicitWait(driver, 5);
    }
    public String getFirstnameErrorText() {
        WebElement firstnameErrorElement = driver.findElement(By.id(firstNameErrorSelector));
        return firstnameErrorElement.getText();
    }
    public String getLastnameErrorText() {
        WebElement lastnameErrorElement = driver.findElement(By.id(lastNameErrorSelector));
        return lastnameErrorElement.getText();
    }
    public String getEmailErrorText() {
        WebElement emailErrorElement = driver.findElement(By.id(emailErrorSelector));
        return emailErrorElement.getText();
    }
    public String getPasswordErrorText() {
        WebElement passwordErrorElement = driver.findElement(By.id(passwordErrorSelector));
        return passwordErrorElement.getText();
    }
    public String getPasswordConfirmationErrorText() {
        WebElement passwordErrorText = driver.findElement(By.id(passwordConfirmationErrorSelector));
        return passwordErrorText.getText();
    }

}
