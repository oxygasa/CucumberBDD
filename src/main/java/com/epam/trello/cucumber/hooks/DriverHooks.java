package com.epam.trello.cucumber.hooks;

import com.epam.trello.commons.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

public class DriverHooks extends DriverManager {

    @Before
    public void setupDriver() {
        DriverManager driverManager = new DriverManager();
        driverManager.startBrowser();
    }

    @After
    public void quitDriver() throws IOException {
        DriverManager driverManager = new DriverManager();
        driverManager.closeBrowser();
    }
}
