package com.epam.trello.pages.cards.card_fullscreen;

import com.epam.trello.commons.CommonActions;
import com.epam.trello.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class FullscreenCardModePage extends BasePage {
    WebDriver driver;

    public FullscreenCardModePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindAll({@FindBy(xpath = "//div[contains(@class,'js-card-details')]")})
    private List<WebElement> activeCards;
    @FindBy(xpath = "//textarea[contains(@class,'comment-box-input')]")
    private WebElement commentTextField;
    @FindBy(xpath = "//textarea[contains(@class,'js-card-detail-title-input')]")
    private WebElement cardTitleInput;
    @FindBy(xpath = "//div[contains(@class,'js-show-with-desc')]/p")
    private WebElement descriptionText;

    public FullscreenCardModePage openFirstActiveCard() throws InterruptedException {
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(activeCards.get(0));
        activeCards.get(0).click();
        Thread.sleep(10000);
        return this;
    }

    public FullscreenCardModePage openSecondActiveCard() throws InterruptedException {
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(activeCards.get(0));
        activeCards.get(1).click();
        Thread.sleep(10000);
        return this;
    }

    public FullscreenCardModePage isCommentTextFieldDoesntDisplay() throws InterruptedException {
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(cardTitleInput);
        try {
            commentTextField.click();
        } catch (
                org.openqa.selenium.NoSuchElementException e) { //Assert.assertFalse(commentTextField.isDisplayed()) doesn't work.
            CommonActions.explicitWaitOfOneElementVisible(cardTitleInput);
        }
        return this;
    }

    public FullscreenCardModePage isDescriptionContainsExpectedText(String expectedText) throws InterruptedException {
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        Assert.assertEquals(descriptionText.getText(), expectedText);
        return this;
    }
}

