package ru.yandexmoney.tests.authorization;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import ru.yandexmoney.config.TestConfig;
import ru.yandexmoney.tests.BaseTest;

@Epic("Regression")
@Feature("Authorization")
@Story("Authorization Test")
public class AuthorizationTest extends BaseTest {

    @Test(groups = AUTHORIZATION, description = "Авторизация в Яндекс.Деньги, выход из аккаунта")
    public void authorizationInYandexMoneyAndExitFromAccount() {
        openHomePage()
                .navigateToPassportAuthorizationPage()
                .setLogin(TestConfig.login)
                .navigateToPasswordSet()
                .setPassword(TestConfig.password)
                .clickNextAndNavigateToAccountPage()
                .clickUserProfileAndOpenSidebarMenuPage()
                .logoutFromAccount();
    }
}
