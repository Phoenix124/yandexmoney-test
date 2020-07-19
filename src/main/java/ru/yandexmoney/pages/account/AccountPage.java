package ru.yandexmoney.pages.account;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandexmoney.pages.BasePage;

public class AccountPage extends BasePage {

    private final String ROOT_XPATH = "//div[contains(@class,'page-layout')]";

    @FindBy(xpath = "//img[contains(@class, 'user')]")
    private WebElement menuItem;

    public AccountPage() {
        waitForPageVisible(ROOT_XPATH);
    }

    @Step("Кликнуть по 'UserProfile' и открыть SidebarMenuPage")
    public SidebarMenuPage clickUserProfileAndOpenSidebarMenuPage() {
        clickElementUsingJs(menuItem);
        return new SidebarMenuPage();
    }
}
