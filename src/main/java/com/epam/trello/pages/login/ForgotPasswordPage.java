package com.epam.trello.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.epam.trello.pages.base.BasePage;

public class ForgotPasswordPage extends BasePage {
    WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul//a[@href='/forgot']")
    public WebElement forgotPasswordLink;
    @FindBy(id = "email")
    public WebElement forgotPasswordEmailMandatory;
    @FindBy(id = "submit")
    public WebElement submitButton;
    @FindBy(id = "aa-forgot-link")
    public WebElement atlassianSettingsLink;
    @FindBy(xpath = "//div/p[@class]")
    public WebElement atlassianForgotPasswordConfirmation;
    @FindBy(className = "css-19r5em7")
    public WebElement atlassianSubmitButton;

    public ForgotPasswordPage askPasswordRestore() {
        LoginViaTrelloPage loginViaTrelloPage = PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.manage().deleteAllCookies();
        driver.get(loginViaTrelloPage.TRELLO_LOGIN_PAGE);
        forgotPasswordLink.click();
        forgotPasswordEmailMandatory.sendKeys(loginViaTrelloPage.LOGIN_CREDENTIAL);
        submitButton.submit();
        atlassianSettingsLink.click();
        atlassianSubmitButton.click();
        /*** BLOCKER: Haven't accessed to a mail service. **/
        Assert.assertEquals(atlassianForgotPasswordConfirmation.
                getText(), loginViaTrelloPage.LOGIN_CREDENTIAL);
        return this;
    }
}