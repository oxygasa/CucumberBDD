package com.epam.trello.commons;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import com.epam.trello.pages.boards.BoardsPage;
import com.epam.trello.pages.cards.header.CardsHeader;
import com.epam.trello.pages.login.LoginViaTrelloPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class CommonActions {

    public static WebDriver driver;

    /**
     * This is a browser setup method.
     * test.properties is a file for editing.
     **/

    static {
        switch (ConfigurationReader.get().platformAndBrowser()) {
            case "CHROME_WINDOWS":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "CHROME_LINUX":
                WebDriverManager wdm = WebDriverManager.chromedriver().linux().enableVnc().enableRecording();
                driver = wdm.create();
                break;
            case "FIREFOX_WINDOWS":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "EDGE_WINDOWS":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "SAFARI_MAC":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            default:
                Assert.fail("Incorrect browser name. Choose name of browser in src/main/java/commons/Config Browser name for now is: "
                        + ConfigurationReader.get().platformAndBrowser());
        }
        driver.manage().window().maximize();
    }

    /*** This method is a CTRL + V from buffer, when you can't getText().
     * For example click "an invitation link" protected web element and the link will save on the clipboard.
     * Then CTRL+V the link to any String or text-field by using this method. **/
    public static String getFromClipBoard() {
        try {
            return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (HeadlessException | UnsupportedFlavorException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /*** Convert any file to String **/
    public static String convertTextFileToString(String file_path) {
        String str = "";
        try {
            str = new String(
                    Files.readAllBytes(Paths.get(file_path)));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /*** New Browser tab opening within Url typing **/

    public static void openUrlInNewBrowserTab(String Url) throws InterruptedException {
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String window1 = driver.getWindowHandle();
        js.executeScript("window.open()");
        Set<String> currentWindows = driver.getWindowHandles();
        String window2 = null;

        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }
        ;
        driver.switchTo().window(window2);
        driver.get(Url);
    }

    /*** Previous Browser tab choosing without closing the current tab **/
    public static void getBackToThePreviousTab() throws InterruptedException {
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String window1 = driver.getWindowHandle();
        Set<String> currentWindows = driver.getWindowHandles();
        String window2 = null;
        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }
        ;
        driver.switchTo().window(window2);
    }

    /*** Close the previous tab of a Browser **/
    public static void closePreviousTab() throws InterruptedException {
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String window1 = driver.getWindowHandle();
        Set<String> currentWindows = driver.getWindowHandles();
        String window2 = null;
        for (String window : currentWindows) {
            if (!window.equals(window1)) {
                window2 = window;
                break;
            }
        }
        ;
        driver.switchTo().window(window2);
        driver.close();
    }


    /**
     * The same login, but by the second user.
     * For the workspace and board visibility tests.
     * For all other tests use the primary user.
     **/
    public static void loginIntoTrelloBySecondUserCredentials() throws InterruptedException {
        LoginViaTrelloPage loginViaTrelloPage = PageFactory.initElements(driver, LoginViaTrelloPage.class);
        try {
            driver.get(loginViaTrelloPage.TRELLO_LOGIN_PAGE);
            loginViaTrelloPage.username.sendKeys(loginViaTrelloPage.SECOND_USER_LOGIN_CREDENTIAL);
            Thread.sleep(2000);
            loginViaTrelloPage.submitButtonTrello.click();
            loginViaTrelloPage.password.sendKeys(loginViaTrelloPage.SECOND_USER_PASSWORD_CREDENTIAL);
            loginViaTrelloPage.submitButtonAtlassian.click();
            Thread.sleep(3000);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            driver.get(loginViaTrelloPage.TRELLO_LOGIN_PAGE);
            loginViaTrelloPage.username.sendKeys(loginViaTrelloPage.SECOND_USER_LOGIN_CREDENTIAL);
            Thread.sleep(2000);
            loginViaTrelloPage.submitButtonTrello.click();
            loginViaTrelloPage.password.sendKeys(loginViaTrelloPage.SECOND_USER_PASSWORD_CREDENTIAL);
            loginViaTrelloPage.submitButtonAtlassian.click();
            Thread.sleep(3000);
        }
    }


    /*** A explicit waiter, which do an assertion of presence 1 element **/
    public static void explicitWaitOfOneElementVisible(WebElement webElementName) throws InterruptedException {
        Thread.sleep(1500);
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(webElementName));
        Thread.sleep(600);
    }

    /*** An explicit waiter which do an assertion of presence elements list **/
    public static void explicitWaitOfElementsListVisible(List<WebElement> webElementsListName) throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOfAllElements(webElementsListName));
        Thread.sleep(1000);
    }

    /**
     * Method is creating 1 empty Board in any Workspace.
     **/
    public static void createOneRandomBoardInstance(String workspaceLink) throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        driver.get(workspaceLink);
        boardsPage.startCreateBoard()
                .typeRandomBoardTitle()
                .submitBoardSave();
    }

    /**
     * 1 instance "Board" is closing (safe delete).
     **/
    public static void closeOneBoardInstanceFromTheWorkspacePage(String workspaceLink) throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        driver.get(workspaceLink);
        boardsPage.openFirstExistingBoard();
        Thread.sleep(1500);
        try {
            /**
             * Don't stop if the Right Navigation Drawer is already opened.
             **/
            boardsPage.hideExistingDrawer()
                    .openRightNaviDrawer()
                    .clickMoreButton()
                    .clickDeleteButtonAndConfirmIt();

        } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.ElementNotInteractableException |
                 org.openqa.selenium.TimeoutException e) {
            /**
             * Within opening the Right Navigation Drawer
             **/
            boardsPage.openRightNaviDrawer()
                    .clickMoreButton()
                    .clickDeleteButtonAndConfirmIt();
        }
    }

    /**
     * All visible instances of "Board" are closing (safe delete).
     * It's need to keep 10 boards limit for free usage of the service.
     * It's need to clean space before fill List collections and Assert them after.
     **/
    public static void closeAllVisibleBoards(String workspaceLink) throws InterruptedException {
        BoardsPage boardsPage = PageFactory.initElements(driver, BoardsPage.class);
        PageFactory.initElements(driver, CardsHeader.class);
        driver.get(workspaceLink);
        Thread.sleep(500);
        try {
            while (boardsPage.countBoardsNumber() > 0) {
                CommonActions.closeOneBoardInstanceFromTheWorkspacePage(workspaceLink);
                driver.get(workspaceLink);
            }
        } catch (IndexOutOfBoundsException | org.openqa.selenium.StaleElementReferenceException e) {
            driver.get(workspaceLink);
        }
    }

    /**
     * Trello's dropdown menu values have been hidden by React Select feature.
     * This method is opening the dropdown menu and select the next value in the dropdown menu by Keys.ARROW_DOWN
     **/
    public static void selectDropdownMenuNextValue(WebElement dropdownMenuWebElementName) {
        dropdownMenuWebElementName.click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();
        dropdownMenuWebElementName.click();
    }

    public static void selectDropdownMenuValueByPositionNumber(WebElement dropdownMenuWebElementName, int countsOfPressArrowDownButton) {
        dropdownMenuWebElementName.click();
        Actions action = new Actions(driver);
        for (int i = 0; i < countsOfPressArrowDownButton; i++) {
            action.sendKeys(Keys.ARROW_DOWN).perform();
        }
        action.sendKeys(Keys.ENTER).perform();
        dropdownMenuWebElementName.click();
    }
}