package com.epam.trello.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class GoogleSearchTest {
    @Given("the user opens {string}")
    public void openUrl(String url) {
    }
    @When("the user type {string} text in the {string} text box")
    public void typeTextInTextBox(String textValue, String textBoxName) {
    }
    @When("the user clicks the {string} button")
    public void clickButton(String buttonName) {
    }
    @When("page with results {string} is displayed")
    public void assertResultTextValue(String resultValue) {
    }
}
