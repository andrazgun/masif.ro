package com.masif.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationPath = Constants.SCREENSHOT_DIRECTORY
                + "screenshot_"
                + testName + " "
                + timestamp + ".png";
        try {
            FileUtils.copyFile(screenshotFile,
                    new File(destinationPath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Screenshot capture failed.");
        }
        return destinationPath;
    }

    public static void takeScreenshot(WebDriver driver) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.getTime()); //convert timestamp to long
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(Constants.SCREENSHOT_DIRECTORY + "screenshot_" + timestamp.getTime() + ".png"));
        } catch (IOException e) {
            System.out.println("File not saved");
        }
    }
}
