package com.epam.trello.pages.cards.right_navigation_draver;

import com.epam.trello.pages.base.BasePage;
import com.epam.trello.pages.boards.BoardsPage;
import com.epam.trello.pages.cards.header.CardsHeader;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.epam.trello.commons.CommonActions;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.epam.trello.pages.cards.card_list.CardListPage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RightNavigationDrawer extends BasePage {
    WebDriver driver;

    public RightNavigationDrawer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindAll({@FindBy(xpath = "//li[@class='board-menu-navigation-item']")})
    private List<WebElement> moreMenuList;
    @FindBy(xpath = "//span[contains(@class,'js-editing-members-description')]/..")
    private WebElement editDescriptionButton;
    @FindBy(xpath = "//div[contains(@class,'description-edit')]/textarea")
    private WebElement editDescriptionTextArea;
    @FindBy(xpath = "//p[contains(@class, 'js-hide-with-desc')]")
    private WebElement editDescriptionPreEditingArea;
    @FindBy(xpath = "//input[contains(@class,'mod-submit')]")
    private WebElement submitDescriptionButton;
    @FindBy(xpath = "//div[contains(@class,'js-desc')]//h1")
    private WebElement descFirstLevelTitle;
    @FindBy(xpath = "//div[contains(@class,'js-desc')]//h2")
    private WebElement descSecondLevelTitle;
    @FindBy(xpath = "//div[contains(@class,'js-desc')]//strong")
    private WebElement descBoldText;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'js-desc')]//em")})
    private List<WebElement> descItalicText;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'js-desc')]//ul/li")})
    private List<WebElement> descBullet;
    @FindBy(xpath = "//div[contains(@class,'js-desc')]//a[@target='_blank']")
    private WebElement descLink;
    @FindBy(xpath = "//div[contains(@class,'js-desc')]//img")
    private WebElement descImage;
    @FindBy(xpath = "//body/div[@id='trello-root']")
    private WebElement boardBackground;
    @FindAll({@FindBy(xpath = "//li[@class='board-menu-navigation-item']")})
    private List<WebElement> mainMenuListOnDrawer;
    @FindAll({@FindBy(xpath = "//ul[contains(@class,'pop-over-list')]/li")})
    private List<WebElement> settingsMenuList;
    @FindBy(xpath = "//select[@class='js-org']")
    private WebElement workspaceSelector;
    @FindBy(xpath = "//select[@name='org-id']")
    private WebElement workspaceOptions;
    @FindBy(xpath = "//a[contains(@href,'workspacename25')]//span[@class='board-header-btn-text']")
    private WebElement actualWorkspaceName;
    @FindBy(xpath = "//div[@class='js-loaded']/input")
    private WebElement submitWorkspaceButton;
    @FindBy(xpath = "//div[@class='sticker-removing']")
    private WebElement stickerRemoveButton;
    @FindBy(xpath = "//button[@data-test-id='collections-upgrade-pill']/..")
    private WebElement collectionsUpgradeToPremiumButton;
    @FindBy(xpath = "//button[@data-test-id='templates-upgrade-pill']/..")
    private WebElement templatesUpgradeToPremiumButton;
    @FindBy(xpath = "//button[contains(@class,'_2m_u7Mih_gK082')]/..")
    private WebElement customFieldsUpgradeToPremiumButton;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'list-card-cover')]")})
    private List<WebElement> createdCovers;
    @FindAll({@FindBy(xpath = "//ul[@class='pop-over-list']/li")})
    private List<WebElement> commentPermissionOptions;
    @FindAll({@FindBy(xpath = "//ul[@class='pop-over-list']/li")})
    private List<WebElement> addRemovePermOptions;
    @FindBy(xpath = "//button[@data-test-id='collections-upgrade-prompt']")
    private WebElement firstPremiumModule;
    @FindBy(xpath = "//button[contains(@class,'_2QS-_EUQY6wswi')]")
    private WebElement secondPremiumModule;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'js-card-details')]")})
    private List<WebElement> archivedCardList;
    @FindAll({@FindBy(xpath = "//a[@class='js-reopen']")})
    private List<WebElement> reopenCards;
    @FindBy(xpath = "//a[@download='trello-board-qr-code.png']")
    private WebElement QRcodeDownloadButton;
    @FindBy(xpath = "//li[contains(@class,'mod-background')]")
    private WebElement changeBackgroundButton;
    @FindAll({@FindBy(xpath = "//div[@class='image']")})
    private List<WebElement> backgroudTypeList;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'photo-attribution-component')]")})
    private List<WebElement> photosByUnsplashTM;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'js-draggable-sticker')]")})
    private List<WebElement> stickersByGiphyTM;
    @FindBy(xpath = "//a[contains(@class,'js-reopen')]/..")
    private WebElement reopenList;
    @FindBy(xpath = "//a[contains(@class,'archive-controls-switch')]")
    private WebElement archiveListSwitchButton;
    @FindBy(xpath = "//input[@id='boardEmail']")
    private WebElement generatedMailField;
    @FindBy(xpath = "//input[@id='boardNewTitle']")
    private WebElement clonedBoardNewTitleField;
    @FindBy(xpath = "//div[@class='js-qr-code']//a")
    private WebElement linkToQR;
    @FindBy(xpath = "//input[@id='id-short-url']")
    private WebElement boardUrl;
    @FindBy(xpath = "//input[contains(@class,'js-submit')]")
    private WebElement submitCloneBoardButton;
    @FindBy(xpath = "//pre[contains(@style,'word-wrap')]")
    private WebElement json;
    @FindBy(xpath = "//a[@class='js-export-json']/..")
    private WebElement exportToJson;
    @FindBy(xpath = "//div[@class='app__select-photos']")
    private WebElement qrReader;
    @FindBy(xpath = "//input[@id='result']")
    private WebElement qrResultLink;
    @FindAll({@FindBy(xpath = "//div[contains(@class,'js-list-header')]")})
    private List<WebElement> listTitles;
    @FindAll({@FindBy(xpath = "//span[contains(@class,'js-card-name')]")})
    private List<WebElement> cardTitles;
    @FindAll({@FindBy(xpath = "//div[@class='phenom-desc']")})
    private List<WebElement> actionList;

    private RightNavigationDrawer navigateToDrawerMainMenu() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        boardsPage.openRightNaviDrawer();
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            boardsPage.openRightNaviDrawer();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            boardsPage.openRightNaviDrawer();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread.sleep(1000);
        return this;
    }

    private RightNavigationDrawer navigateToSettingsSectionOfMoreMenuWithinDrawerOpeningStatusChecking() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            navigateToSettingsMoreSection();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            navigateToSettingsMoreSection();
        }
        return this;
    }

    private RightNavigationDrawer navigateToMoreSectionWithinDrawerOpeningStatusChecking() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    private RightNavigationDrawer navigateToSettingsMoreSection() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        boardsPage.openRightNaviDrawer()
                .clickMoreButton();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(0).click();
        return this;
    }

    public RightNavigationDrawer changeWorkspace() throws InterruptedException {
        navigateToSettingsSectionOfMoreMenuWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
        settingsMenuList.get(0).click();
        Select workspaceDropdown = new Select(workspaceSelector);
        workspaceDropdown.selectByIndex(1);
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(submitWorkspaceButton);
        submitWorkspaceButton.click();
        Thread.sleep(2000);
        Assert.assertEquals(actualWorkspaceName.getText(), "Workspace name");
        settingsMenuList.get(0).click();
        workspaceDropdown.selectByIndex(0);
        Thread.sleep(4000);
        CommonActions.explicitWaitOfOneElementVisible(submitWorkspaceButton);
        submitWorkspaceButton.click();
        return this;
    }

    public RightNavigationDrawer disableCover() throws InterruptedException {
        navigateToSettingsSectionOfMoreMenuWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
        settingsMenuList.get(1).click();
        Assert.assertFalse(createdCovers.get(0).isDisplayed());
        return this;
    }

    public RightNavigationDrawer changeCommentPermission() throws InterruptedException {
        navigateToSettingsSectionOfMoreMenuWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
        settingsMenuList.get(2).click();
        CommonActions.explicitWaitOfOneElementVisible(commentPermissionOptions.get(1));
        commentPermissionOptions.get(1).click();
        return this;
    }

    public RightNavigationDrawer addRemovePermTurnOff() throws InterruptedException {
        navigateToSettingsSectionOfMoreMenuWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
        settingsMenuList.get(3).click();
        CommonActions.explicitWaitOfOneElementVisible(addRemovePermOptions.get(1));
        addRemovePermOptions.get(1).click();
        return this;
    }

    public RightNavigationDrawer inviteOnlyTurnOn() throws InterruptedException {
        navigateToSettingsSectionOfMoreMenuWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(settingsMenuList.get(0));
        settingsMenuList.get(4).click();
        return this;
    }

    public RightNavigationDrawer navigateToDisallowedBoard(String disallowedBoardUrl) throws InterruptedException {
        driver.get(disallowedBoardUrl);
        return this;
    }

    public RightNavigationDrawer tryToActivateCollections() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(collectionsUpgradeToPremiumButton);
        collectionsUpgradeToPremiumButton.click();
        boardsPage.premiumAskingAssert();
        return this;
    }

    public RightNavigationDrawer tryToActivateTemplate() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(templatesUpgradeToPremiumButton);
        templatesUpgradeToPremiumButton.click();
        boardsPage.premiumAskingAssert();
        return this;
    }

    public RightNavigationDrawer tryToActivateCustomFields() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        navigateToDrawerMainMenu();
        CommonActions.explicitWaitOfOneElementVisible(customFieldsUpgradeToPremiumButton);
        customFieldsUpgradeToPremiumButton.click();
        boardsPage.premiumAskingAssert();
        return this;
    }

    public RightNavigationDrawer tryToUpgradeUserFirstModule() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(firstPremiumModule);
        firstPremiumModule.click();
        boardsPage.premiumAskingAssert();
        return this;
    }

    public RightNavigationDrawer tryToUpgradeUserSecondModule() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        navigateToDrawerMainMenu();
        CommonActions.explicitWaitOfOneElementVisible(secondPremiumModule);
        secondPremiumModule.click();
        boardsPage.premiumAskingAssert();
        return this;
    }

    public RightNavigationDrawer startWatchBoard() throws InterruptedException {
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(4).click();
        return this;
    }

    public RightNavigationDrawer copyAndNavigateToBoardLink() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        String expectedBoardName = boardsPage.getFirstBoardTitle();
        boardsPage.openFirstExistingBoard();
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        String expectedBoardUrl = boardUrl.getAttribute("value");
        driver.get(expectedBoardUrl);
        String actualBoardName = cardsHeader.getBoardName();
        Assert.assertEquals(actualBoardName, expectedBoardName);
        return this;
    }

    private void findDownloadedQrCodeImage(File rootFile, List<File> fileList) {
        if (rootFile.isDirectory()) {
            File[] directoryFiles = rootFile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.getName().toLowerCase().startsWith("trello-board-qr-code")) {
                        fileList.add(file);
                    }
                }
            }
        }
    }

    private void deleteRepeatingQRImagesFromFS() {
        for (File myFile : Objects.requireNonNull(new File("C:\\Users\\user\\Downloads").listFiles()))
            if (myFile.getName().contains("trello-board-qr-code")) myFile.delete();
    }

    private void analiseFileSystemAboutExistingQRImagesAndDeleteThem() throws InterruptedException {
        CommonActions.explicitWaitOfOneElementVisible(linkToQR);
        linkToQR.click();
        CommonActions.explicitWaitOfOneElementVisible(QRcodeDownloadButton);
        ArrayList<File> fileList = new ArrayList<>();
        findDownloadedQrCodeImage(new File("C:\\Users\\user\\Downloads"), fileList);
    }

    private void downloadQRcode() throws InterruptedException {
        QRcodeDownloadButton.click();
        Thread.sleep(5000);
    }

    public RightNavigationDrawer copyAndNavigateToBoardQR() throws InterruptedException, IOException, NotFoundException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        CardsHeader cardsHeader = PageFactory.initElements(driver, CardsHeader.class);
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        String expectedBoardName = boardsPage.getFirstBoardTitle();
        boardsPage.openFirstExistingBoard();
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        analiseFileSystemAboutExistingQRImagesAndDeleteThem();
        deleteRepeatingQRImagesFromFS();
        downloadQRcode();
        ArrayList<File> fileList1 = new ArrayList<>();
        findDownloadedQrCodeImage(new File("C:\\Users\\user\\Downloads"), fileList1);
        File qrCodeInFiles = new File(fileList1.get(fileList1.size() - 1).getPath());
        BufferedImage bufferedImage = ImageIO.read(qrCodeInFiles);
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
        Result result = new MultiFormatReader().decode(binaryBitmap);
        String expectedBoardUrl = result.getText();
        driver.get(expectedBoardUrl);
        String actualBoardName = cardsHeader.getBoardName();
        Assert.assertEquals(actualBoardName, expectedBoardName);
        return this;
    }

    public RightNavigationDrawer copyBoard() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(5).click();
        Thread.sleep(1000);
        CommonActions.explicitWaitOfOneElementVisible(clonedBoardNewTitleField);
        Select workspaceDropdown = new Select(workspaceOptions);
        workspaceDropdown.selectByIndex(1);
        Thread.sleep(2000);
        String newBoardName = RandomStringUtils.randomAlphanumeric(10);
        clonedBoardNewTitleField.sendKeys(newBoardName);
        submitCloneBoardButton.click();
        Thread.sleep(500);
        submitCloneBoardButton.click();
        driver.get(boardsPage.getSecondaryWorkspaceUrl());
        Assert.assertEquals(boardsPage.getFirstBoardTitle(), newBoardName);
        return this;
    }


    public RightNavigationDrawer isJsonValid() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        driver.get(boardsPage.getDefaultWorkspaceUrl());
        String expectedBoardName = boardsPage.getFirstBoardTitle();
        boardsPage.openFirstExistingBoard();
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(6).click();
        exportToJson.click();
        Assert.assertTrue(json.getText().contains(expectedBoardName));
        return this;
    }

    private RightNavigationDrawer navigateToArchive() throws InterruptedException {
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(2).click();
        Thread.sleep(5000);
        CommonActions.explicitWaitOfOneElementVisible(archivedCardList.get(0));
        return this;
    }

    private RightNavigationDrawer navigateToEmailGenerator() throws InterruptedException {
        navigateToMoreSectionWithinDrawerOpeningStatusChecking();
        CommonActions.explicitWaitOfOneElementVisible(moreMenuList.get(0));
        moreMenuList.get(3).click();
        Thread.sleep(5000);
        CommonActions.explicitWaitOfOneElementVisible(generatedMailField);
        return this;
    }

    public RightNavigationDrawer returnAllCardsFromArchive() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
            CommonActions.explicitWaitOfOneElementVisible(firstPremiumModule);
            firstPremiumModule.click();
            boardsPage.premiumAskingAssert();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            navigateToArchive();
            CommonActions.explicitWaitOfOneElementVisible(reopenCards.get(0));
            while (reopenCards.size() > 0) {
                reopenCards.get(0).click();
            }
        }
        return this;
    }

    public RightNavigationDrawer returnListOfCardsFromArchive() throws InterruptedException {
        archiveListSwitchButton.click();
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(reopenList);
        reopenList.click();
        return this;
    }

    public RightNavigationDrawer generateMailAddressByTrello() throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            Thread.sleep(2000);
            boardsPage.hideExistingDrawer();
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton();
            CommonActions.explicitWaitOfOneElementVisible(firstPremiumModule);
            firstPremiumModule.click();
            boardsPage.premiumAskingAssert();
        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            navigateToEmailGenerator();
        }
        return this;
    }

    public RightNavigationDrawer editAboutThisBoardDescription() throws InterruptedException {
        navigateToDrawerMainMenu();
        CommonActions.explicitWaitOfOneElementVisible(mainMenuListOnDrawer.get(0));
        mainMenuListOnDrawer.get(0).click();
        CommonActions.explicitWaitOfOneElementVisible(editDescriptionPreEditingArea);
        editDescriptionPreEditingArea.click();
        CommonActions.explicitWaitOfOneElementVisible(editDescriptionTextArea);
        editDescriptionTextArea.clear();
        String separator = File.separator;
        String path = "." + separator + "src" + separator + "test" + separator + "resources" + separator + "TRE049TestCaseMockText.txt";
        String descriptionText = CommonActions.convertTextFileToString(path);
        editDescriptionTextArea.sendKeys(descriptionText);
        submitDescriptionButton.click();
        Assert.assertEquals(descFirstLevelTitle.getText(), "Making Scrambled Eggs: A Primer");
        Assert.assertEquals(descSecondLevelTitle.getText(), "1.1: Preparation");
        Assert.assertEquals(descBoldText.getText(), "Carefully");
        Assert.assertEquals(descItalicText.get(0).getText(), "vigorously");
        Assert.assertEquals(descItalicText.get(1).getText(), "Optional:");
        Assert.assertEquals(descBullet.get(0).getText(), "Eggs");
        Assert.assertEquals(descLink.getAttribute("href"), "https://example.com/scrambled-eggs.pdf");
        Assert.assertEquals(descImage.getAttribute("src"), "https://mvnrepository.com/assets/images/392dffac024b9632664e6f2c0cac6fe5-logo.png");
        return this;
    }

    public RightNavigationDrawer changeBoardBackground() throws InterruptedException {
        String backgroundBefore = boardBackground.getAttribute("style");
        navigateToDrawerMainMenu();
        CommonActions.explicitWaitOfOneElementVisible(changeBackgroundButton);
        changeBackgroundButton.click();
        CommonActions.explicitWaitOfOneElementVisible(backgroudTypeList.get(0));
        backgroudTypeList.get(0).click();
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(photosByUnsplashTM.get(0));
        Random random = new Random();
        photosByUnsplashTM.get(random.nextInt(10)).click();
        Thread.sleep(5000);
        CommonActions.explicitWaitOfOneElementVisible(boardBackground);
        String backgroundAfter = boardBackground.getAttribute("style");
        Assert.assertNotEquals(backgroundAfter, backgroundBefore);
        return this;
    }

    public RightNavigationDrawer selectStickers() throws InterruptedException {
        navigateToDrawerMainMenu();
        CommonActions.explicitWaitOfOneElementVisible(mainMenuListOnDrawer.get(1));
        mainMenuListOnDrawer.get(1).click();
        Thread.sleep(2000);
        CommonActions.explicitWaitOfOneElementVisible(stickersByGiphyTM.get(0));
        Random random = new Random();
        Actions actions = new Actions(driver);
        actions.dragAndDrop(stickersByGiphyTM.get(random.nextInt(10)), CardListPage.cardTitle.get(0))
                .moveToElement(CardListPage.cardTitle.get(0))
                .click(stickerRemoveButton);
        return this;
    }

    public RightNavigationDrawer checkActivityIsInteractive() throws InterruptedException {
        String expectedListTitle = listTitles.get(0).getText();
        String expectedCardTitle = cardTitles.get(0).getText();
        navigateToDrawerMainMenu();
        String actualCardAndListTitles = actionList.get(0).getText();
        String actualBoardStatusInfo = actionList.get(2).getText();
        System.out.println("expectedListTitle: " + expectedListTitle + ". expectedCardTitle: " + expectedCardTitle + ". actualCardTitle: " + actualCardAndListTitles);
        Assert.assertEquals(actualCardAndListTitles, "trello rafs added " + expectedCardTitle + " to " + expectedListTitle);
        System.out.println(actualBoardStatusInfo);
        Assert.assertEquals(actualBoardStatusInfo, "trello rafs added this board to tenboardstestworkspace");
        return this;
    }

    public String getTrelloGeneratedMail() {
        return generatedMailField.getAttribute("value");
    }
}