package com.epam.trello.pages.register;

import com.epam.trello.commons.CommonActions;
import com.epam.trello.pages.login.LoginViaTrelloPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TempEmailSender {
    WebDriver driver;

    public TempEmailSender(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String MAIL_SENDER_URL = "https://emkei.cz/";
    @FindBy(id = "fromname")
    private WebElement senderNameTextBox;
    @FindBy(id = "from")
    private WebElement senderEmailTextBox;
    @FindBy(id = "rcpt")
    private WebElement receiverEmailTextBox;
    @FindBy(id = "cc")
    private WebElement sendCopyToTextBox;
    @FindBy(id = "subject")
    private WebElement emailTitleTextBox;
    @FindBy(id = "text")
    private WebElement emailBodyTextBox;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement sendEmailButton;

    public TempEmailSender sendEmail(String mailTo, String mailTitle, String mailBody) throws InterruptedException {
        LoginViaTrelloPage loginViaTrelloPage = PageFactory.initElements(driver, LoginViaTrelloPage.class);
        driver.get(MAIL_SENDER_URL);
        Thread.sleep(1000);
        CommonActions.explicitWaitOfOneElementVisible(senderNameTextBox);
        senderNameTextBox.sendKeys("TrelloUser");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('section1').setAttribute('style','display:')");
        Thread.sleep(3000);
        CommonActions.explicitWaitOfOneElementVisible(sendCopyToTextBox);
        sendCopyToTextBox.sendKeys(loginViaTrelloPage.getSecondUserLoginCredential());
        senderEmailTextBox.sendKeys(loginViaTrelloPage.getLoginCredential());
        receiverEmailTextBox.sendKeys(mailTo);
        emailTitleTextBox.sendKeys(mailTitle);
        emailBodyTextBox.sendKeys(mailBody);
        sendEmailButton.click();
        return this;
    }
}
