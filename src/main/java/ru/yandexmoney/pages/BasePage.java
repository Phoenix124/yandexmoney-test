package ru.yandexmoney.pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandexmoney.config.TestConfig;
import ru.yandexmoney.config.WebDriverFactory;
import ru.yandexmoney.helpers.WebElementHelpers;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.fail;
import static ru.yandexmoney.config.TestConfig.IMPLICITLY_WAIT_IN_SECONDS;

public abstract class BasePage {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    protected WebDriver driver;

    public BasePage() {
        this.driver = WebDriverFactory.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    @Step()
    protected void waitForPageVisible(WebElement element) {
        String pageName = getClass().getSimpleName();
        LOGGER.info("Wait page: " + pageName);
        Allure.getLifecycle().updateStep(step -> step.setName("Дождаться загрузки страницы " + pageName));

        try {
            WebElementHelpers.waitDocumentReady();
            waitForElementVisible(element);
        } catch (Exception e) {
            String errorMessage = pageName + " was not loaded after " + IMPLICITLY_WAIT_IN_SECONDS + " seconds.\n";
            fail(errorMessage);
            e.printStackTrace();
        }

        LOGGER.info("Page " + pageName + " loaded");
    }

    @Step()
    protected void waitForPageVisible(String rootPageLocator) {
        String pageName = getClass().getSimpleName();
        LOGGER.info("Wait page: " + pageName);
        Allure.getLifecycle().updateStep(step -> step.setName("Дождаться загрузки страницы " + pageName));

        try {
            WebElementHelpers.waitDocumentReady();
            waitForElementVisible(By.xpath(rootPageLocator));
        } catch (Exception e) {
            String errorMessage = pageName + " was not loaded after " + IMPLICITLY_WAIT_IN_SECONDS + " seconds.\n";
            fail(errorMessage);
            e.printStackTrace();
        }

        LOGGER.info("Page " + pageName + " loaded");
    }

    protected void clickElement(WebElement element) {
        waitForElementClickable(element);
        element.click();
    }

    protected void clickElementUsingJs(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverFactory.getInstance().getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    protected void waitForElementClickable(WebElement element) {
        getWaiter().until(elementToBeClickable(element));
    }

    protected void waitForElementVisible(WebElement element) {
        getWaiter().until(visibilityOf(element));
    }

    protected void waitForElementVisible(WebElement element, String errorMessage) {
        getWaiter(errorMessage).until(visibilityOf(element));
    }

    protected void waitForElementVisible(By findStrategy) {
        getWaiter().until(visibilityOfElementLocated(findStrategy));
    }

    protected void waitForElementVisible(By findStrategy, String errorMessage) {
        getWaiter(errorMessage).until(visibilityOfElementLocated(findStrategy));
    }

    protected void waitForElementVisibleNot(By findStrategy) {
        waitUntilFunctionIsTrue(func -> !isElementExist(findStrategy));
    }

    protected void waitElementsMoreThen(By findStrategy, int elementCount) {
        getWaiter().until(numberOfElementsToBeMoreThan(findStrategy, elementCount));
    }

    protected boolean isElementExist(By locator) {
        List allElements = driver.findElements(locator);
        return allElements != null && !allElements.isEmpty();
    }

    protected void waitUntilFunctionIsTrue(Function<WebDriver, Object> function) {
        getWaiter().until(function);
    }

    protected void waitUntilFunctionIsTrue(Function<WebDriver, Object> function, String errorMessage) {
        getWaiter(errorMessage).until(function);
    }

    private FluentWait<WebDriver> getWaiter() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(IMPLICITLY_WAIT_IN_SECONDS))
                .pollingEvery(Duration.ofMillis(TestConfig.IMPLICITLY_WAIT_SHORT_TIMEOUT_IN_SECONDS))
                .ignoring(ElementNotInteractableException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(InvalidElementStateException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }

    private FluentWait<WebDriver> getWaiter(String errorMessage) {
        return getWaiter()
                .withMessage(errorMessage + "\nWait timeout: " + IMPLICITLY_WAIT_IN_SECONDS + " seconds");
    }
}
