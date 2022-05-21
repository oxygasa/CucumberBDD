package com.epam.trello.pages.cards.power_ups;

import com.epam.trello.commons.CommonActions;
import com.epam.trello.pages.base.BasePage;
import com.epam.trello.pages.boards.BoardsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PowerUpsPage extends BasePage {
    WebDriver driver;

    public PowerUpsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@data-test-id='board-header-plugin-button']")
    public WebElement powerUpsButton;
    @FindBy(xpath = "//button[contains(@class,'_3Ik0JLsERwh6Ui')]")
    public WebElement confirmRedirectToPowerUpsPage;
    @FindBy(xpath = "//a[contains(@class,'trello-option')]")
    public WebElement madeByTrelloButton;
    @FindBy(xpath = "//span[@title='Jira']/../..//button[contains(@class,'nch-button--primary')]")
    public WebElement addPowerUpJiraButton;
    @FindBy(xpath = "//span[@title='Jira']/../..//a[contains(@class,'directory-settings-button')]")
    public WebElement powerUpJiraSettingButton;
    @FindBy(xpath = "//a[contains(@class,'js-close-directory')]/..")
    public WebElement closePowerUpsPageButton;
    @FindBy(xpath = "//button[contains(@class,'twKazOW7wj1Ow')]")
    public WebElement installedPowerUpOpeningButton;
    @FindBy(xpath = "//div[contains(@class,'pop-over')]//div[@class='no-back']")
    public WebElement installedPowerUpInteractiveWindow;

    public PowerUpsPage openPowUpsStore() throws InterruptedException {
        Thread.sleep(5000);
        CommonActions.explicitWaitOfOneElementVisible(powerUpsButton);
        powerUpsButton.click();
        CommonActions.explicitWaitOfOneElementVisible(confirmRedirectToPowerUpsPage);
        confirmRedirectToPowerUpsPage.click();
        Thread.sleep(8000);
        return this;
    }

    public PowerUpsPage installJira() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(madeByTrelloButton);
        madeByTrelloButton.click();
        CommonActions.explicitWaitOfOneElementVisible(addPowerUpJiraButton);
        addPowerUpJiraButton.click();
        Assert.assertTrue(powerUpJiraSettingButton.isDisplayed());
        closePowerUpsPageButton.click();
        return this;
    }

    public PowerUpsPage IsJiraInstalled() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        installedPowerUpOpeningButton.click();
        CommonActions.explicitWaitOfOneElementVisible(installedPowerUpInteractiveWindow);
        Assert.assertTrue(installedPowerUpInteractiveWindow.isDisplayed());
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        return this;
    }

}
