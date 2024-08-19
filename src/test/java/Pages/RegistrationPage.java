package Pages;

import Utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class RegistrationPage extends BasePage {

    private String pageURL = "https://masif.ro/customer/account/create/";
    private String pageTitle = "Creeaza-ti cont | MASIF";
    private String firstNameInputSelector = "firstname"; //id
    private String lastNameInputSelector = "lastname";
    private String emailInputSelector = "email_address";
    private String passwordInputSelector = "password";
    private String passwordAgainInputSelector = "password-confirmation";
    private String passwordErrorMessageSelector = "password-error";
    private String passwordAgainErrorMessageSelector = "password-confirmation-error";
    private String submitButtonSelector = "//*[@class=\"create-account-container\"]//*[@class=\"primary\"]";
    private String goToLoginPage = "Intra in cont aici";
    String createAccountErrorSelector;
    String createAccountSuccessSelector;


    public RegistrationPage(WebDriver driver) {
        super(driver);
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

        WebElement submitButtonElement = driver.findElement(By.xpath(submitButtonSelector));

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
        body.sendKeys(Keys.PAGE_DOWN);
        SeleniumUtils.implicitWait(driver, 3);


        Actions actions = new Actions(driver);
        actions.moveToElement(submitButtonElement).click();

//        submitButtonElement.click();
//        SeleniumUtils.implicitWait(driver, 3);

    }
    public WebElement getPasswordErrorSelector() {
        return driver.findElement(By.id(passwordErrorMessageSelector));
    }
    public String getPasswordAgainErrorText() {
        return driver.findElement(By.id(passwordAgainInputSelector)).getText();
    }

    public WebElement getCreateAccountErrorSelector() {
        return driver.findElement(By.cssSelector(createAccountErrorSelector));
    }
    public void isCreateAccountErrorDisplayed() {
        Assert.assertTrue(getCreateAccountErrorSelector().isDisplayed());
        String loginErrorMessage = "";
        Assert.assertEquals(getCreateAccountErrorSelector().getText(),loginErrorMessage);
    }

    public String getCreateAccountSuccessSelector() {
        return driver.findElement(By.cssSelector(createAccountSuccessSelector)).getText();
    }


}
