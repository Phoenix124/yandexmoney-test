package ru.yandexmoney.tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import ru.yandexmoney.config.TestConfig;
import ru.yandexmoney.config.WebDriverFactory;
import ru.yandexmoney.helpers.WebElementHelpers;
import ru.yandexmoney.listeners.BaseTestListener;
import ru.yandexmoney.listeners.SuiteListener;
import ru.yandexmoney.pages.HomePage;
import ru.yandexmoney.tests.config.ITestGroup;

import java.lang.reflect.Method;

@Listeners({SuiteListener.class, BaseTestListener.class})
public class BaseTest implements ITestGroup {

    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void before(Method method, ITestContext ctx, ITestResult testResult) {
        logger.info("Before test method start. Test: {}", method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void after(Method method, ITestResult testResult) {
        logger.info("After test method start. Test: {}", method.getName());

        attachErrorDataIfNeed(testResult);
        WebDriverFactory.getInstance().quitDriver();

        logger.info("After test method end. Test: {}", method.getName());
    }

    @Step("Открыть главную страницу")
    public HomePage openHomePage() {
        getWebDriver().get(TestConfig.url);
        return new HomePage();
    }

    public void attachErrorDataIfNeed(ITestResult testResult) {
        boolean isNeedAttachingFiles = WebDriverFactory.getInstance().getDriver() != null
                && testResult != null
                && !testResult.isSuccess();

        if (isNeedAttachingFiles) {
            logger.warn("Attaching a screenshot with an error");
            WebElementHelpers.attachScreenshot("error-screenshot.png");
            logger.warn("Attaching a page source with an error");
            WebElementHelpers.attachPageSource("error-page-source.html");
        }
    }

    public WebDriver getWebDriver() {
        WebDriver driver = WebDriverFactory.getInstance().getDriver();

        if (driver == null) {
            throw new NullPointerException("WebDriver is null");
        }

        return driver;
    }
}
