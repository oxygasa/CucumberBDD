package com.epam.trello.pages.login;

import com.epam.trello.commons.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.epam.trello.pages.base.BasePage;

public class LoginViaMicrosoftPage extends BasePage {
    WebDriver driver;

    public LoginViaMicrosoftPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public final String LOGIN_CREDENTIAL = "trellou0@outlook.com";
    public final String PASSWORD_CREDENTIAL = "Trellouser999Te!42";
    @FindBy(id = "msftButton")
    public WebElement microsoftOauthButton;
    @FindBy(id = "i0116")
    public WebElement microsoftLoginTextField;
    @FindBy(id = "idSIButton9")
    public WebElement microsoftLoginNextButton;
    @FindBy(id = "i0118")
    public WebElement microsoftPasswordTextField;
    @FindBy(id = "idSIButton9")
    public WebElement microsoftSignInButton;
    @FindBy(id = "idBtn_Back")
    public WebElement buttonNoAboutSavingSession;

    public LoginViaMicrosoftPage tryToLoginViaMicrosoft() throws InterruptedException {
        LoginViaTrelloPage loginViaTrelloPage = PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.manage().deleteAllCookies();
        driver.get(loginViaTrelloPage.TRELLO_LOGIN_PAGE);
        followTheLoginProcessOnMSLogPage();
        CommonActions.explicitWaitOfOneElementVisible(loginViaTrelloPage.avatarName);
        loginViaTrelloPage.avatarName.click();
        /*** Expected result: Access granted. The Boards page is opened. The Username is according to [Login credential]
         **/
        Assert.assertEquals(loginViaTrelloPage.avatarEmail.getText(), LOGIN_CREDENTIAL);
        return this;
    }

    public LoginViaMicrosoftPage followTheLoginProcessOnMSLogPage() throws InterruptedException {
        microsoftOauthButton.click();
        CommonActions.explicitWaitOfOneElementVisible(microsoftLoginTextField);
        microsoftLoginTextField.sendKeys(LOGIN_CREDENTIAL);
        microsoftLoginNextButton.click();
        CommonActions.explicitWaitOfOneElementVisible(microsoftPasswordTextField);
        microsoftPasswordTextField.sendKeys(PASSWORD_CREDENTIAL);
        microsoftSignInButton.click();
        CommonActions.explicitWaitOfOneElementVisible(buttonNoAboutSavingSession);
        buttonNoAboutSavingSession.click();
        return this;
    }
}
