package com.epam.trello.pages.register;

import com.epam.trello.commons.CommonActions;
import com.epam.trello.pages.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TempEmailReceiver extends BasePage {
    WebDriver driver;

    public TempEmailReceiver(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String TEMP_MAIL_PAGE_URL = "https://temp-mail.io/";
    @FindBy(id = "email")
    private WebElement randomTempEmail;
    @FindAll({@FindBy(xpath = "//li[contains(@class,'message list-complete')]")})
    private List<WebElement> incomeBoxMailListButtons;
    @FindBy(xpath = "//a[contains(text(),'Workspace')]")
    private WebElement joinWorkspaceList;

    public String createTempEmailInstance() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            driver.get(TEMP_MAIL_PAGE_URL);
        }
        CommonActions.explicitWaitOfOneElementVisible(randomTempEmail);
        return randomTempEmail.getAttribute("title");
    }

    public String generatedTempEmail() throws InterruptedException {
        return createTempEmailInstance();
    }

    public TempEmailReceiver sendMail(String sendToMail, String message) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            driver.get(TEMP_MAIL_PAGE_URL);
            CommonActions.explicitWaitOfOneElementVisible(randomTempEmail);

        }
        return this;
    }
}
