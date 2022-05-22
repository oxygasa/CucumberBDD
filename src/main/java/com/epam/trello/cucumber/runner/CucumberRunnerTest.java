package com.epam.trello.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-reports",
                "json:target/cucumber-reports/CucumberTests.xml"},
        monochrome = true,
        tags = "@alltests, @smoke",
        glue = "com.epam.trello.steps",
        features = "./src/test/resources/features"
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
}
