package ru.yandexmoney.pages.account;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandexmoney.pages.BasePage;
import ru.yandexmoney.pages.HomePage;

public class SidebarMenuPage extends BasePage {

    private final String ROOT_XPATH = "//div[contains(@class, 'StyledRoot')]";

    @FindBy(xpath = "(" + ROOT_XPATH + "//button)[2]")
    private WebElement exitButton;

    public SidebarMenuPage() {
        waitForPageVisible(ROOT_XPATH);
    }

    @Step("Кликнуть по 'exit button' и перейти на HomePage")
    public HomePage logoutFromAccount() {
        clickElementUsingJs(exitButton);
        return new HomePage();
    }

}
