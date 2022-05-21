package com.epam.trello.commons;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class DriverManager {
    WebDriver driver;

    public WebDriver startBrowser() {
        driver = CommonActions.driver;
        return driver;
    }

    public void closeBrowser() throws IOException {
        if (ConfigurationReader.get().makeScreenshots() && !(driver == null)) {
            Date date = new Date();
            String currentTime = String.valueOf(date.getTime());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File bufferedScreenshotFile = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(bufferedScreenshotFile, new File("./screenshots/" + currentTime + ".png"));
        }
        if (!ConfigurationReader.get().holdBrowserOpen()) {
            if (ConfigurationReader.get().clearCookies()) {
                assert driver != null;
                driver.manage().deleteAllCookies();
            }
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    }
}

