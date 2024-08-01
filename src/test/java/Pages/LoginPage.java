package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

//Page Object Model design pattern
public class LoginPage extends HomePage {

    private String pageURL = "https://masif.ro/customer/account/login/";
    private String usernameInputSelector = "login[username]"; //name
    private String passwordInputSelector = "login[password]"; //name
    private String submitButtonSelector = "#login-form > fieldset > div.actions-toolbar > div.primary"; //css
    private String loginErrorSelector = "#maincontent > div.page.messages > div:nth-child(2) > div"; //css
    private By checkboxNameSelector = By.cssSelector("#remember-me-box");
    private By checkboxButton = By.className("checkbox");


    public LoginPage(WebDriver driver) {super(driver);
    }
    @Override
    public void verifyPage() {
        System.out.println("Page URL is: " + getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }
    private WebElement getCheckboxNameSelector(){
        return driver.findElement(checkboxNameSelector);
    }
    public void isCheckboxIsDisplayed() {
        Assert.assertTrue(getCheckboxNameSelector().isDisplayed()); //assert if checkbox is displayed
    }
    private String getUsernameInputElement() {
        return driver.findElement(By.tagName("login[password]")).getText();
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
        submitButton.click();
    }

    public WebElement getLoginErrorSelector() {
        return driver.findElement(By.cssSelector(loginErrorSelector));
    }
    public void isLoginErrorDisplayed() {
        Assert.assertTrue(getLoginErrorSelector().isDisplayed());
        String loginErrorMessage = "Contul cu care ai încercat să te autentifici e incorect sau a fost dezactivat temporar. Te rugăm să aștepți și să încerci mai târziu.";
        Assert.assertEquals(getLoginErrorSelector().getText(),loginErrorMessage);
    }







}
