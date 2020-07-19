package ru.yandexmoney.pages.passport;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandexmoney.pages.BasePage;
import ru.yandexmoney.pages.account.AccountPage;

public class PassportAuthorizationPage extends BasePage {

    private final String ROOT_XPATH = "//div[@class='passp-auth']";

    @FindBy(xpath = "//input[@name='login']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@name='passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement nextButton;

    public PassportAuthorizationPage() {
        waitForPageVisible(ROOT_XPATH);
    }

    @Step("Ввести логин {0}")
    public PassportAuthorizationPage setLogin(String login) {
        loginField.sendKeys(login);
        return this;
    }

    @Step("Ввести пароль {0}")
    public PassportAuthorizationPage setPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Нажать 'Next' и перейти к вводу пароля")
    public PassportAuthorizationPage navigateToPasswordSet() {
        clickElementUsingJs(nextButton);
        return this;
    }

    @Step("Нажать 'Next' и перейти на AccountPage")
    public AccountPage clickNextAndNavigateToAccountPage() {
        clickElementUsingJs(nextButton);
        return new AccountPage();
    }
}
