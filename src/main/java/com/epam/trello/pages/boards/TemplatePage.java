package com.epam.trello.pages.boards;

import com.epam.trello.commons.CommonActions;
import com.epam.trello.pages.base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TemplatePage extends BasePage {
    WebDriver driver;

    public TemplatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String searchRequest = "Kanban";
    public final String expectedBoardName = "Kanban Template";
    @FindBy(xpath = "//div[contains(@class,'css-1hwfws3')]")
    private WebElement searchBox;
    @FindBy(xpath = "//button[contains(@class,'_15kBM1ZNn6-rXG')]")
    private WebElement useTemplateButton;
    @FindBy(xpath = "//input[@value='Create']")
    private WebElement templateCreateConfirmButton;

    /**
     * This field eats
     ***/

    public TemplatePage searchAndConnectKanbanBoard() throws InterruptedException {
        Actions actions = new Actions(driver);
        driver.get("https://trello.com/templates");
        CommonActions.explicitWaitOfOneElementVisible(searchBox);
        actions.moveToElement(searchBox).click().sendKeys(searchRequest).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(5000);
        CommonActions.explicitWaitOfOneElementVisible(useTemplateButton);
        useTemplateButton.click();
        Thread.sleep(10000);
        CommonActions.explicitWaitOfOneElementVisible(templateCreateConfirmButton);
        templateCreateConfirmButton.click();
        return this;
    }

}
