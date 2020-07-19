package ru.yandexmoney.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandexmoney.pages.passport.PassportAuthorizationPage;

public class HomePage extends BasePage {

    private static final String PAGE_NAME = "Home Page";
    private static final String ROOT_XPATH = "//div[@id='root']";

    @FindBy(xpath = "//div[contains(@class,'User')]//a")
    private WebElement signInButton;

    public HomePage() {
        waitForPageVisible(ROOT_XPATH);
    }

    @Step("Нажать на кнопку войти и перейти на 'PassportAuthorizationPage'")
    public PassportAuthorizationPage navigateToPassportAuthorizationPage() {
        clickElement(signInButton);
        return new PassportAuthorizationPage();
    }
}
