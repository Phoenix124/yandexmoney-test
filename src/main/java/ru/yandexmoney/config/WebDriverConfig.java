package ru.yandexmoney.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariOptions;

final class WebDriverConfig {

    static ChromeOptions prepareChrome() {
        WebDriverManager.chromedriver().clearPreferences().setup();
        System.setProperty("webdriver.chrome.driver", WebDriverManager.chromedriver().getBinaryPath());

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);

        options.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability("showChromedriverLog ", true);
        options.setCapability("setWebContentsDebuggingEnabled", true);
        options.setCapability("recreateChromeDriverSessions", true);
        options.setCapability("pageLoadStrategy", "normal");

        options.addArguments("test-type");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        return options;
    }

    static SafariOptions prepareSafari() {
        SafariOptions options = new SafariOptions();
        options.setAutomaticInspection(true);
        options.setCapability("safari.cleanSession", true);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return options;
    }

    static FirefoxOptions prepareFirefox() {
        WebDriverManager.firefoxdriver().setup();
        String firefoxBinaryPath = WebDriverManager.firefoxdriver().getBinaryPath();
        System.setProperty("webdriver.gecko.driver", firefoxBinaryPath);

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox");
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setLogLevel(FirefoxDriverLogLevel.INFO);

        return options;
    }
}
