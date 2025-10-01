package com.masif.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.masif.utils.BrowserUtils.getDriver;

public class AllureTestListener implements ITestListener {
    private WebDriver driver = getDriver();

//    // Pass WebDriver instance to the listener

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] saveScreenshotOnFailure() {
        if (driver != null) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);}
        return new byte[0]; // the method safely returns an empty byte array instead of causing a NullPointerException.
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (driver != null) {
            ScreenshotUtils.takeScreenshot(driver);
            // Capture and attach the screenshot to the Allure Report
            saveScreenshotOnFailure();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Optional: Add logic for success
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Optional: Add logic for skipped tests
    }
}
