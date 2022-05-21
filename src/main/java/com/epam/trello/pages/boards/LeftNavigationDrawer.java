package com.epam.trello.pages.boards;

import com.epam.trello.commons.CommonActions;
import com.epam.trello.pages.base.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LeftNavigationDrawer extends BasePage {
    @FindBy(xpath = "//a[@title='Table']")
    public WebElement tableButton;
    @FindBy(xpath = "//button[@data-test-id='workspace-navigation-expand-button']")
    public WebElement expander;
    @FindBy(xpath = "//button[@data-test-id='workspace-navigation-collapse-button']")
    private WebElement collapser;
    @FindBy(xpath = "//button[@aria-label='Add board']")
    public WebElement addBoard;
    @FindAll({@FindBy(xpath = "//ul[@data-test-id='collapsible-list-items']//a")})
    public List<WebElement> workspacesAndBoardsList;
    @FindAll({@FindBy(xpath = "//button[contains(@class,'1De3PKu4pMyBCW ')]")})
    public List<WebElement> boardActionsMenu;
    @FindBy(xpath = "//button[contains(@aria-label,'Close board')]")
    public WebElement closeBoardMenu;
    @FindAll({@FindBy(xpath = "//button[contains(@class,'_3HfJ71CiQ9nm2y _1Tu9wiuW4Te8Rx')]")})
    public List<WebElement> confirmCloseBoardButton;
    @FindBy(xpath = "//button[@data-test-id='boards-list-show-more-button']")
    public WebElement showMore;
    WebDriver driver;

    public LeftNavigationDrawer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LeftNavigationDrawer expandLeftNaviDrawer() throws InterruptedException {
        try {
            CommonActions.explicitWaitOfOneElementVisible(expander);
            expander.click();
        } catch (org.openqa.selenium.TimeoutException e) {
            Thread.sleep(100);
        }
        return this;
    }

    public LeftNavigationDrawer createBoardInstance() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(CommonActions.driver, BoardsPage.class);
        addBoard.click();
        boardsPage.typeRandomBoardTitle().submitBoardSave();
        return this;
    }

    public LeftNavigationDrawer closeAllVisibleBoardsViaLeftNaviDrawer() {
        BoardsPage boardsPage = PageFactory.initElements(CommonActions.driver, BoardsPage.class);
        CommonActions.driver.get(boardsPage.getDefaultWorkspaceUrl());
        while (workspacesAndBoardsList.size() > 1) {
            CommonActions.driver.get(boardsPage.getDefaultWorkspaceUrl());
            Actions actions = new Actions(CommonActions.driver);
            try {
                CommonActions.explicitWaitOfOneElementVisible(workspacesAndBoardsList.get(0));
                //2 modules (a workspace module, a board module) have 1 size-changeable List<WebElement>
                actions.moveToElement(workspacesAndBoardsList.get(2)).build().perform();
            } catch (IndexOutOfBoundsException | InterruptedException e) {
                try {
                    showMore.click();
                    CommonActions.explicitWaitOfOneElementVisible(workspacesAndBoardsList.get(0));
                    actions.moveToElement(workspacesAndBoardsList.get(2)).build().perform();
                } catch (NoSuchElementException | InterruptedException f) {
                    break; // boards are successfully closed.
                }
            }
            try {
                CommonActions.explicitWaitOfOneElementVisible(boardActionsMenu.get(0));
                actions.moveToElement(boardActionsMenu.get(0)).click().build().perform();
                CommonActions.explicitWaitOfOneElementVisible(closeBoardMenu);
                closeBoardMenu.click();
                CommonActions.explicitWaitOfOneElementVisible(confirmCloseBoardButton.get(1));
                confirmCloseBoardButton.get(1).click();
            } catch (NoSuchElementException | IndexOutOfBoundsException | InterruptedException f) {
                break; // boards are successfully closed.
            }
        }
        return this;
    }

    public LeftNavigationDrawer selectWorkspaceTable() throws InterruptedException {
        Thread.sleep(5000);
        CommonActions.explicitWaitOfOneElementVisible(tableButton);
        tableButton.click();
        return this;
    }
}
