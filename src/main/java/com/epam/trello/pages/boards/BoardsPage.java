package com.epam.trello.pages.boards;

import com.epam.trello.commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.epam.trello.pages.base.BasePage;
import java.util.ArrayList;
import java.util.List;

public class BoardsPage extends BasePage {
    /*** Web elements, locators. **/
    private final String DEFAULT_WORKSPACE_URL = "https://trello.com/tenboardstestworkspace";
    private final String SECONDARY_WORKSPACE_URL = "https://trello.com/workspacename25";
    @FindAll({@FindBy(xpath = "//div[@class='board-tile-details-name']")})
    public List<WebElement> boardTitles; //need to be public for assert List preparations.
    WebDriver driver;
    @FindAll({@FindBy(xpath = "//ul[@class='boards-page-board-section-list']/li")})
    private List<WebElement> createBoardFromBoardsPageButton;
    @FindAll({@FindBy(xpath = "//a[contains(@class,'board-tile')]")})
    private List<WebElement> boardInstancesList;
    @FindAll({@FindBy(xpath = "//span[@class='board-tile-options']")})
    private List<WebElement> boardsStarIcon;
    @FindAll({@FindBy(xpath = "//span[@class='board-tile-options']/span")})
    private List<WebElement> boardsStarredIcon;
    @FindBy(xpath = "//a[contains(@class,'js-close-board')]")
    private WebElement rightSidebarCloseBoardButton;
    @FindBy(xpath = "//button[@aria-label='Add board']")
    private WebElement addBoardFromLeftNavigationDrawer;
    @FindBy(xpath = "//input[@value='Close']")
    private WebElement rightSidebarDialogBoxCloseBoardButton;
    @FindBy(xpath = "//input[@data-test-id='create-board-title-input']")
    private WebElement newBoardNameInput;
    @FindBy(xpath = "//button[@data-test-id='create-board-submit-button']")
    private WebElement newBoardSubmitButton;
    @FindBy(xpath = "//a[contains(@class,'_3QMgDmYO8j2CrA')]/span")
    private WebElement startFreeTrialButton;
    @FindBy(xpath = "//span[contains(@class,'icon-star')]")
    private WebElement starIconInTheBoardPage;
    @FindBy(xpath = "//div[@class='board-tile mod-add']/p/span")
    private WebElement createNewBoardSign;
    @FindBy(xpath = "//span[contains(@class,'starred')]")
    private WebElement boardStarredIcon;
    @FindBy(xpath = "//div[contains(@class,'css-1og2rpm')]")
    private WebElement sortByFilter;
    @FindBy(xpath = "//button[contains(@class,'_1Dp3s5P2VP237V')]")
    private WebElement filterByCollectionDropdown;
    @FindBy(xpath = "//button[contains(@class,'_1tVNVS4PNoFo0k')]")
    private WebElement filterByCollectionPremiumRequireButton;
    @FindBy(xpath = "//button[@data-test-id='popover-close']")
    private WebElement filterByCollectionPremiumRequireClosePopupButton;
    @FindBy(xpath = "//input[@id='search']")
    private WebElement boardsSearchBox;
    @FindAll({@FindBy(xpath = "//h3[contains(text(),'WORKSPACES')]//..//div[@class='board-tile-details-name']/div")})
    private List<WebElement> boardsPageAllWorkspacesBoards;
    @FindBy(xpath = "//button[@data-test-id='home-navigation-create-team-button']")
    private WebElement createWorkspaceLeftNaviDrawer;
    @FindBy(xpath = "//a[contains(@class,'js-hide-sidebar')]")
    private WebElement hideRightSidebar;
    @FindBy(xpath = "//a[contains(@class,'js-show-sidebar')]")
    private WebElement showRightSidebarButton;
    @FindBy(xpath = "//a[contains(@class,'js-open-more')]")
    private WebElement rightSidebarMoreButton;

    public BoardsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getDefaultWorkspaceUrl() {
        return DEFAULT_WORKSPACE_URL;
    }

    public String getSecondaryWorkspaceUrl() {
        return SECONDARY_WORKSPACE_URL;
    }

    /*** There are methods to make test steps code shorter **/

    public List<String> createCollectionOfFiveExpectedBoards() throws InterruptedException {
        List<String> expectedBoardNamesListener = new ArrayList<>();
        CommonActions.driver.get(getDefaultWorkspaceUrl());
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(createBoardFromBoardsPageButton.get(0));
        while (boardInstancesList.size() < 4) {
            CommonActions.driver.get(getDefaultWorkspaceUrl());
            CommonActions.explicitWaitOfOneElementVisible(createBoardFromBoardsPageButton.get(0));
            createBoardFromBoardsPageButton.get(0).click();
            String newBoardNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
            newBoardNameInput.sendKeys(newBoardNameTextFieldInputText);
            CommonActions.explicitWaitOfOneElementVisible(newBoardSubmitButton);
            Thread.sleep(3000); //Thread.sleep is necessary, because this inactive button is clickable.
            //and don't create a board.
            newBoardSubmitButton.click();
            expectedBoardNamesListener.add(newBoardNameTextFieldInputText);
        }
        return expectedBoardNamesListener;
    }

    public List<String> chooseMostRecentlyFromDropdownAndSaveToCollection() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(sortByFilter);
        CommonActions.selectDropdownMenuValueByPositionNumber(sortByFilter, 4);
        CommonActions.explicitWaitOfElementsListVisible(boardTitles);
        List<String> actualResultBoardNamesMost = new ArrayList<>();
        for (int i = 0; i < boardTitles.size(); i++) {
            actualResultBoardNamesMost.add(boardTitles.get(i).getText());
        }
        return actualResultBoardNamesMost;
    }

    public List<String> chooseLeastRecentlyFromDropdownAndSaveToCollection() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(sortByFilter);
        CommonActions.selectDropdownMenuValueByPositionNumber(sortByFilter, 1);
        CommonActions.explicitWaitOfElementsListVisible(boardTitles);
        List<String> actualResultBoardNamesLeast = new ArrayList<>();
        for (int i = 0; i < boardTitles.size(); i++) {
            actualResultBoardNamesLeast.add(boardTitles.get(i).getText());
        }
        return actualResultBoardNamesLeast;
    }

    public List<String> chooseAZFromDropdownAndSaveToCollection() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(sortByFilter);
        CommonActions.selectDropdownMenuValueByPositionNumber(sortByFilter, 2);
        CommonActions.explicitWaitOfElementsListVisible(boardTitles);
        List<String> actualResultBoardNamesAZ = new ArrayList<>();
        for (int i = 0; i < boardTitles.size(); i++) {
            actualResultBoardNamesAZ.add(boardTitles.get(i).getText());
        }
        return actualResultBoardNamesAZ;
    }

    public List<String> chooseZAFromDropdownAndSaveToCollection() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(sortByFilter);
        CommonActions.selectDropdownMenuValueByPositionNumber(sortByFilter, 3);
        CommonActions.explicitWaitOfElementsListVisible(boardTitles);
        List<String> actualResultBoardNamesZA = new ArrayList<>();
        for (int i = 0; i < boardTitles.size(); i++) {
            actualResultBoardNamesZA.add(boardTitles.get(i).getText());
        }
        return actualResultBoardNamesZA;
    }

    public List<String> saveBoardPageRecentBoardListToCollection() throws InterruptedException {
        CommonActions.driver.get(getDefaultWorkspaceUrl() + "/boards");
        CommonActions.explicitWaitOfElementsListVisible(boardsPageAllWorkspacesBoards);
        Thread.sleep(2000);
        List<String> actualResultBoardNames = new ArrayList<>();
        for (int i = 0; i < boardsPageAllWorkspacesBoards.size(); ++i) {
            actualResultBoardNames.add(boardsPageAllWorkspacesBoards.get(i).getText());
        }
        return actualResultBoardNames;
    }


    /*** Series of methods for deleting the board. Actions **/
    public BoardsPage openRightNaviDrawer() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(showRightSidebarButton);
        showRightSidebarButton.click();
        return this;
    }

    public BoardsPage clickMoreButton() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(rightSidebarMoreButton);
        rightSidebarMoreButton.click();
        return this;
    }

    public BoardsPage clickDeleteButtonAndConfirmIt() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(rightSidebarCloseBoardButton);
        rightSidebarCloseBoardButton.click();
        CommonActions.explicitWaitOfOneElementVisible(rightSidebarDialogBoxCloseBoardButton);
        rightSidebarDialogBoxCloseBoardButton.click();
        return this;
    }

    public BoardsPage hideExistingDrawer() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(hideRightSidebar);
        hideRightSidebar.click();
        return this;
    }

    /*** Series of methods for operations with a board list **/
    public BoardsPage openFirstExistingBoard() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(3000);
        CommonActions.explicitWaitOfOneElementVisible(boardInstancesList.get(0));
        boardInstancesList.get(0).click();
        return this;
    }

    public int countBoardsNumber() {
        return boardInstancesList.size();
    }

    public String getFirstBoardTitle() {
        return boardTitles.get(0).getText();
    }

    public BoardsPage isFirstBoardDisplays() {
        Assert.assertTrue(boardInstancesList.get(0).isDisplayed());
        return this;
    }

    public BoardsPage premiumAskingAssert() throws InterruptedException {
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(startFreeTrialButton);
        String startFreeTrialTextOnButton = startFreeTrialButton.getText();
        Assert.assertEquals(startFreeTrialTextOnButton, "Upgrade"); //free version ask for premium.
        return this;
    }

    public BoardsPage activatePremiumBoardFilter() throws InterruptedException {
        filterByCollectionDropdown.click();
        Assert.assertEquals(filterByCollectionPremiumRequireButton.getText(), "Upgrade to Premium");
        filterByCollectionPremiumRequireClosePopupButton.click();
        boardsSearchBox.sendKeys(chooseZAFromDropdownAndSaveToCollection().get(0));
        return this;
    }

    public BoardsPage checkSearchBoxFindingExistingBoard() {
        List<String> searchBoardResult = new ArrayList<>();
        for (int i = 0; i < countBoardsNumber(); i++) {
            countBoardsNumber();
            searchBoardResult.add(boardTitles.get(i).getText());
        }
        Assert.assertEquals(searchBoardResult.size(), 1);
        return this;
    }

    /*** Series of methods for creating the board. Actions **/
    public BoardsPage startCreateBoard() throws InterruptedException {
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(createBoardFromBoardsPageButton.get(0));
        createBoardFromBoardsPageButton.get(0).click();
        Thread.sleep(500);
        return this;
    }

    public BoardsPage isStartCreateBoardDisplays() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(createNewBoardSign);
        return this;
    }

    public BoardsPage typeRandomBoardTitle() throws InterruptedException {
        Thread.sleep(500);
        String newBoardNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        CommonActions.explicitWaitOfOneElementVisible(newBoardNameInput);
        newBoardNameInput.sendKeys(newBoardNameTextFieldInputText);
        return this;
    }


    public BoardsPage submitBoardSave() throws InterruptedException {
        Thread.sleep(3000); //The submit button is clickable and visible with inactive status.
        // The waiter means this element is active.
        // Need the Thread.sleep
        CommonActions.explicitWaitOfOneElementVisible(newBoardSubmitButton);
        newBoardSubmitButton.click();
        return this;
    }

    /*** Inside board actions **/
    public BoardsPage activateStarSign() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(starIconInTheBoardPage);
        starIconInTheBoardPage.click();
        return this;
    }

    public BoardsPage isStarDisplaysInsideBoard() {
        Assert.assertTrue(boardStarredIcon.isDisplayed());
        return this;
    }

    public BoardsPage isStarInteractiveInTheBoardList() throws InterruptedException {
        Thread.sleep(3000);
        CommonActions.explicitWaitOfOneElementVisible(boardsStarredIcon.get(0));
        boardsStarredIcon.get(0).click();
        Assert.assertFalse(boardsStarredIcon.get(0).isSelected());
        boardsStarredIcon.get(0).click();
        Assert.assertTrue(boardsStarredIcon.get(0).isDisplayed());
        boardsStarIcon.get(0).click();
        Assert.assertFalse(boardsStarredIcon.get(0).isSelected());
        return this;
    }

    public void deactivateStarSign() {
        boardStarredIcon.click();
    }

    /*** Left NaviDrawer operations **/


    public BoardsPage createWorkspaceFromLeftNaviDrawer() throws InterruptedException {
        Thread.sleep(10000);
        CommonActions.explicitWaitOfOneElementVisible(createWorkspaceLeftNaviDrawer);
        createWorkspaceLeftNaviDrawer.click();
        return this;
    }

    public BoardsPage navigateToNaviDrawerBoardList() {
        addBoardFromLeftNavigationDrawer.click();
        return this;
    }
}
