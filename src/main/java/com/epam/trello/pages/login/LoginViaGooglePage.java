package com.epam.trello.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.epam.trello.pages.base.BasePage;

public class LoginViaGooglePage extends BasePage {
    WebDriver driver;

    public LoginViaGooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "googleButton")
    public WebElement googleOauthButton;
    @FindBy(id = "identifierId")
    public WebElement googleLoginTextField;
    @FindBy(id = "identifierNext")
    public WebElement googleLoginNextButton;
    @FindBy(id = "headingText")
    public WebElement googleSeleniumBlockerMessage;

    public LoginViaGooglePage tryToLoginViaGoogle() {
        LoginViaTrelloPage loginViaTrelloPage = PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.manage().deleteAllCookies();
        driver.get(loginViaTrelloPage.TRELLO_LOGIN_PAGE);
        googleOauthButton.click();
        googleLoginTextField.sendKeys(loginViaTrelloPage.LOGIN_CREDENTIAL);
        googleLoginNextButton.click();
        /*** BLOCKER: Google secure message about the prohibition og using Web Driver for login into the Google account. **/
        Assert.assertTrue(googleSeleniumBlockerMessage.isDisplayed());
        return this;
    }
}
