package com.epam.trello.pages.cards.header;

import com.epam.trello.commons.CommonActions;
import com.epam.trello.pages.base.BasePage;
import com.epam.trello.pages.boards.BoardsPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CardsHeader extends BasePage {
    WebDriver driver;
    @FindBy(xpath = "//button[@data-test-id='board-views-switcher-button']")
    private WebElement boardViewSwitcherButton;
    @FindBy(xpath = "//button[@data-test-id='board-views-upsell-prompt-upgrade-bc-button']")
    private WebElement switchBoardViewPremiumAsking;
    @FindBy(xpath = "//div[contains(@class,'js-rename-board')]")
    private WebElement boardRenameInput;
    @FindBy(xpath = "//h1[contains(@class,'board-header-btn-text')]")
    private WebElement boardTitleFor2ndUser;
    @FindBy(xpath = "//a[contains(@class,'js-open-org-menu')]")
    private WebElement openWorkspaceMenu;
    @FindBy(xpath = "//a[contains(@class,'js-change-org')]")
    private WebElement changeWorkspaceMenu;
    @FindBy(xpath = "//select[@class='js-org']")
    private WebElement changeWorkspaceDropdownMenu;
    @FindBy(xpath = "//input[contains(@class,'js-submit')]")
    private WebElement changeWorkspaceSubmitButton;
    @FindBy(xpath = "//a[@class='js-view-org']")
    private WebElement viewWorkspaceMenu;
    @FindBy(id = "//a[@id='permission-level']/..")
    private WebElement workspaceVisibleButton;
    @FindBy(xpath = "//a[@id='permission-level']//span[@class='board-header-btn-text']")
    private WebElement workspaceVisibleButtonText;
    @FindBy(xpath = "//a[@name='public']")
    private WebElement publicBoardPermissionSelect;
    @FindBy(xpath = "//a[@name='private']")
    private WebElement privateBoardPermissionSelect;
    @FindBy(xpath = "//a[@name='org']")
    private WebElement workspaceBoardPermissionSelect;
    @FindBy(xpath = "//button[contains(@class,'js-submit')]")
    private WebElement publicBoardPermissionConfirmButton;
    @FindBy(xpath = "//button[@data-test-id='header-member-menu-button']")
    private WebElement avatarIcon;
    @FindBy(xpath = "//button[@data-test-id='header-member-menu-logout']")
    private WebElement logoutButton;
    @FindBy(xpath = "//input[@data-test-id='header-search-input']")
    private WebElement searchBox;
    @FindBy(xpath = "//button[@data-test-id='filter-popover-button']")
    private WebElement filterPopoverButton;
    @FindBy(xpath = "//input[contains(@class,'nch-textfield__input')]")
    private WebElement filerKeywordInput;
    @FindBy(xpath = "//div[contains(@title,'assigned')]")
    private WebElement cardsAssignedToMeCheckBox;
    @FindBy(xpath = "//span[@data-test-id='board-view-option']/..")
    private WebElement BoardViewSwitcherPremiumBoardButton;
    @FindBy(xpath = "//button[@data-test-id='board-share-button']")
    private WebElement boardShareButton;
    @FindAll({@FindBy(xpath = "//div[@class='D7o35mpYYtXnpz']")})
    private List<WebElement> filterCheckboxesList;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'css-ufz0vj-control')]")})
    private List<WebElement> colorsDropdown;
    private String expectedBoardName;
    @FindAll({@FindBy(xpath = "//a[contains(@class,'icon-close dark-hover')]")})
    private List<WebElement> cancelEditingCardButton;
    @FindAll({@FindBy(xpath = "//span[contains(@class,'js-card-name')]")})
    private List<WebElement> cardTitleInput;
    @FindBy(xpath = "//div[@class='_1TqwtTDcAPw_Dc']/button")
    private WebElement inviteLinkClipboard;
    @FindBy(xpath = "//input[@data-test-id='add-members-input']")
    private WebElement addMemberInput;
    @FindBy(xpath = "//button[@data-test-id='board-invite-modal-close-button']")
    private WebElement closeInviteWindow;
    @FindBy(xpath = "//button[@id='logout-submit']")
    private WebElement confirmlogout;
    @FindBy(xpath = "//div[@class='invitation-link-description']")
    private WebElement invitationDescription;
    @FindBy(xpath = "//button[contains(@class,'js-login')]")
    private WebElement inviteStatusLoginButton;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'js-list-draggable-board-members')]/a")})
    private List<WebElement> memberList;
    @FindBy(xpath = "//li[@class='_16b-lFr0zo4Zdb']")
    private WebElement changePermissionButton;
    @FindBy(xpath = "//div[@data-test-id='board-share-modal-title']")
    private WebElement permissionEditWindow;

    public CardsHeader(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public CardsHeader tryToChangeBoardViewAsFreeUser() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(boardViewSwitcherButton);
        boardViewSwitcherButton.click();
        /*** A Board is opened. A module with a Premium asking is displayed.**/
        Assert.assertTrue(switchBoardViewPremiumAsking.isDisplayed());
        return this;
    }

    public CardsHeader renameBoard() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        Actions actions = new Actions(driver);
        CommonActions.explicitWaitOfOneElementVisible(boardRenameInput);
        String boardNameTextFieldInputText = RandomStringUtils.randomAlphanumeric(10);
        actions.click(boardRenameInput).sendKeys(boardNameTextFieldInputText).sendKeys(Keys.ENTER).build().perform();
        String expectedTitleAfterRename = boardRenameInput.getText();
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        System.out.println("expected" + expectedTitleAfterRename);
        System.out.println("actual" + boardsPage.getFirstBoardTitle());
        Assert.assertEquals(boardsPage.getFirstBoardTitle(), expectedTitleAfterRename);
        return this;
    }

    public String getBoardName() {
        return boardRenameInput.getText();
    }

    public CardsHeader checkTheBoardNameIsExpected(String expectedBoardName) throws InterruptedException {
        Thread.sleep(4000);
        CommonActions.explicitWaitOfOneElementVisible(boardRenameInput);
        Assert.assertEquals(boardRenameInput.getText(), expectedBoardName);
        return this;
    }

    public CardsHeader changeWorkspace() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(openWorkspaceMenu);
        openWorkspaceMenu.click();
        CommonActions.explicitWaitOfOneElementVisible(changeWorkspaceMenu);
        changeWorkspaceMenu.click();
        CommonActions.selectDropdownMenuNextValue(changeWorkspaceDropdownMenu);
        CommonActions.explicitWaitOfOneElementVisible(changeWorkspaceSubmitButton);
        changeWorkspaceSubmitButton.click();
        Thread.sleep(3000);
        openWorkspaceMenu.click();
        viewWorkspaceMenu.click();
        return this;
    }

    public String getVisibleWorkspaceButtonText() {
        return workspaceVisibleButtonText.getText();
    }

    public CardsHeader selectPrivateVisible() throws InterruptedException {
        workspaceVisibleButton.click();
        CommonActions.explicitWaitOfOneElementVisible(privateBoardPermissionSelect);
        privateBoardPermissionSelect.click();
        return this;
    }

    public CardsHeader selectPublicVisible() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(workspaceVisibleButton);

        this.expectedBoardName = boardRenameInput.getText();
        workspaceVisibleButton.click();
        CommonActions.explicitWaitOfOneElementVisible(publicBoardPermissionSelect);
        publicBoardPermissionSelect.click();
        CommonActions.explicitWaitOfOneElementVisible(publicBoardPermissionConfirmButton);
        publicBoardPermissionConfirmButton.click();
        return this;
    }


    public CardsHeader logoutFromTrello() throws InterruptedException {
        Thread.sleep(3000);
        avatarIcon.click();
        CommonActions.explicitWaitOfOneElementVisible(logoutButton);
        logoutButton.click();
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(confirmlogout);
        confirmlogout.click();
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        Thread.sleep(500);
        return this;
    }

    public CardsHeader selectRandomFilterDetails() throws InterruptedException {
        filterPopoverButton.click();
        cardsAssignedToMeCheckBox.click();//assigned to me
        filterCheckboxesList.get(2).click();//no date
        String expectedInput = cardTitleInput.get(0).getText();
        filerKeywordInput.sendKeys(expectedInput);
        Assert.assertEquals(cardTitleInput.get(0).getText(), expectedInput);
        return this;
    }

    public CardsHeader isInviteLinkValid() throws InterruptedException {
        Thread.sleep(500);
        CommonActions.explicitWaitOfOneElementVisible(boardShareButton);
        boardShareButton.click();
        Thread.sleep(3000);
        CommonActions.explicitWaitOfOneElementVisible(inviteLinkClipboard);
        for (int i = 0; i < 4; i++) {
            inviteLinkClipboard.click();
            Thread.sleep(1000);
        }
        String inviteLinkValue = CommonActions.getFromClipBoard();
        closeInviteWindow.click();
        logoutFromTrello();
        driver.get(inviteLinkValue);
        Thread.sleep(500);
        CommonActions.explicitWaitOfOneElementVisible(invitationDescription);
        Assert.assertTrue(invitationDescription.isDisplayed());
        return this;
    }

    public CardsHeader startLoginAfterInvite() throws InterruptedException {
        Thread.sleep(5000);
        CommonActions.explicitWaitOfOneElementVisible(inviteStatusLoginButton);
        inviteStatusLoginButton.click();
        Thread.sleep(3000);
        return this;
    }

    public CardsHeader checkTheHigherAndSelfPermissionsAreInactive() throws InterruptedException {
        Thread.sleep(3000);
        CommonActions.explicitWaitOfOneElementVisible(memberList.get(0));
        memberList.get(0).click();
        CommonActions.explicitWaitOfOneElementVisible(changePermissionButton);
        changePermissionButton.click();
        try {
            Assert.assertFalse(permissionEditWindow.isDisplayed());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            CommonActions.explicitWaitOfOneElementVisible(memberList.get(1));
        }

        CommonActions.explicitWaitOfOneElementVisible(memberList.get(1));
        memberList.get(1).click();
        CommonActions.explicitWaitOfOneElementVisible(changePermissionButton);
        changePermissionButton.click();
        try {
            Assert.assertFalse(permissionEditWindow.isDisplayed());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            CommonActions.explicitWaitOfOneElementVisible(changePermissionButton);
        }
        return this;
    }
}
