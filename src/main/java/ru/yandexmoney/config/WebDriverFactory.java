package ru.yandexmoney.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import ru.yandexmoney.enums.Browser;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    private static final ThreadLocal<WebDriverFactory> instance = new ThreadLocal<>();
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriverFactory getInstance() {
        if (instance.get() == null) {
            synchronized (WebDriverFactory.class) {
                instance.set(new WebDriverFactory());
            }
        }
        return instance.get();
    }

    private WebDriverFactory() {
        logger.info("Create Instance WebDriverFactory");
        createDriverInstance();
    }

    public WebDriver getDriver() {
        if (driver.get() == null) {
            createDriverInstance();
        }
        return driver.get();
    }

    public void quitDriver() {
        driver.get().quit();
        driver.remove();
    }

    private void createDriverInstance() {
        logger.info("Create Instance WebDriver");
        WebDriver webDriver = null;
        Browser browserName = Browser.valueOf(PropertiesConfig.properties.getProperty("DRIVER"));

        switch (browserName) {
            case CHROME:
                logger.info("Start Chrome browser");
                webDriver = new ChromeDriver(WebDriverConfig.prepareChrome());
                break;
            case SAFARI:
                logger.info("Start Safari browser");
                webDriver = new SafariDriver(WebDriverConfig.prepareSafari());
                break;
            case FIREFOX:
                logger.info("Start Firefox browser");
                webDriver = new FirefoxDriver(WebDriverConfig.prepareFirefox());
                break;
            default:
                Assert.fail("No browser name is specified");
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(TestConfig.IMPLICITLY_WAIT_IN_SECONDS, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(TestConfig.IMPLICITLY_WAIT_IN_SECONDS, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(TestConfig.IMPLICITLY_WAIT_IN_SECONDS, TimeUnit.SECONDS);

        driver.set(webDriver);
    }
}
