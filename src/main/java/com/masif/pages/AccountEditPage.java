package com.masif.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static com.masif.utils.BrowserUtils.submitButtonByCss;

public class AccountEditPage extends BasePage {
    private final String pagePath = "/customer/account/edit/";
    private String pageURL = "https://masif.ro/customer/account/edit/";
    private String firstNameSelector = "firstname";
    private String lastNameSelector = "lastname";
    private String saveButtonSelector = "#form-validate > div > div.primary > button";

    public AccountEditPage(WebDriver driver) {
        super(driver);
    }

    public String setPagePath() {
        return this.pagePath;
    }

    @Override
    public void verifyPageURL() {
        log.info("Page URL is: {}", getPageURL());
        Assert.assertEquals(getPageURL(), pageURL);
    }

    @Step
    public void editAccount(String firstName, String lastName) {

        WebElement firstNameInput = driver.findElement(By.id(firstNameSelector));
        WebElement lastNameInput = driver.findElement(By.id(lastNameSelector));

        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        log.info("Random first name: {}", firstName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        log.info("Random last name: {}", lastName);
        submitButtonByCss(saveButtonSelector);
        log.info("Save button is clicked.");
    }
}
