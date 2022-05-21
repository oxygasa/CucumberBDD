package com.epam.trello.pages.login;

import com.epam.trello.commons.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.epam.trello.pages.base.BasePage;

public class LoginViaApplePage extends BasePage {
    WebDriver driver;

    public LoginViaApplePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public final String LOGIN_CREDENTIAL = "trellou0@gmail.com";
    public final String PASSWORD_CREDENTIAL = "sfjkg@Gygfuyg%%^$12521";
    @FindBy(id = "signInWithAppleButton")
    public WebElement appleOauthButton;
    @FindBy(id = "account_name_text_field")
    public WebElement appleIDLoginTextField;
    @FindBy(id = "sign-in")
    public WebElement appleIDLoginNextButton;
    @FindBy(id = "password_text_field")
    public WebElement appleIDPasswordTextField;
    @FindBy(id = "didnt-get-code")
    public WebElement apple2FADidntGetCodeLink;

    public LoginViaApplePage tryToLoginViaApple() throws InterruptedException {
        LoginViaTrelloPage loginViaTrelloPage = PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.manage().deleteAllCookies();
        driver.get(loginViaTrelloPage.TRELLO_LOGIN_PAGE);
        appleOauthButton.click();
        appleIDLoginTextField.sendKeys(LOGIN_CREDENTIAL);
        appleIDLoginNextButton.click();
        appleIDPasswordTextField.sendKeys(PASSWORD_CREDENTIAL);
        appleIDLoginNextButton.click();
        Thread.sleep(3000);
        CommonActions.explicitWaitOfOneElementVisible(apple2FADidntGetCodeLink);
        /*** BLOCKER: Apple ID uses 2FA sending SMS. **/
        Assert.assertTrue(apple2FADidntGetCodeLink.isDisplayed());
        return this;
    }
}
