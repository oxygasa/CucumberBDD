package com.epam.trello.commons;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static final Logger LOG = LogManager.getRootLogger();
    private static final Properties properties = new Properties();
    private static ConfigurationReader instance;

    private ConfigurationReader() {
    }

    public static ConfigurationReader get() {
        if (instance == null) {
            instance = new ConfigurationReader();
            try {
                properties.load(new FileInputStream("src/main/resources/test.properties"));
            } catch (IOException e) {
                LOG.error("Properties were not loaded.");
            }

        }
        return instance;
    }

    public String platformAndBrowser() {
        return properties.getProperty("platform.and.browser");
    }

    public boolean clearCookies() {
        return BooleanUtils.toBoolean(properties.getProperty("clear.cookies"));
    }

    public boolean holdBrowserOpen() {
        return BooleanUtils.toBoolean(properties.getProperty("hold.browser.open"));
    }

    public boolean makeScreenshots() {
        return BooleanUtils.toBoolean(properties.getProperty("make.screenshots"));
    }
}
