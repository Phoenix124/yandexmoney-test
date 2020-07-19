package ru.yandexmoney.helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import ru.yandexmoney.config.TestConfig;
import ru.yandexmoney.config.WebDriverFactory;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

public final class WebElementHelpers {

    private WebElementHelpers() {
        //
    }

    public static FluentWait<?> getWaiter() {
        return new FluentWait<>(WebElementHelpers.class)
                .withTimeout(Duration.ofSeconds(TestConfig.IMPLICITLY_WAIT_IN_SECONDS));
    }

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] attachScreenshot(String screenshotName) {
        byte[] screenshot = null;
        try {
            screenshot = ((TakesScreenshot) WebDriverFactory
                    .getInstance()
                    .getDriver())
                    .getScreenshotAs(OutputType.BYTES);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenshot;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static byte[] attachPageSource(String filename) {
        byte[] data = null;
        try {
            data = WebDriverFactory
                    .getInstance()
                    .getDriver()
                    .getPageSource()
                    .getBytes(StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void waitDocumentReady() {
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverFactory.getInstance().getDriver();
        getWaiter().until(f -> executor.executeScript("return document.readyState").equals("complete"));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverFactory.getInstance().getDriver();
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private static boolean isElementNoMotion(WebElement element) {
        try {
            int firstPointHash = element.getLocation().hashCode();
            int secondPointHash = element.getLocation().hashCode();
            return firstPointHash == secondPointHash;
        } catch (Exception | Error e) {
            return false;
        }
    }
}
