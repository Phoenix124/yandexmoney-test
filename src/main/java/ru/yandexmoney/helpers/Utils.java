package ru.yandexmoney.helpers;

import org.openqa.selenium.Cookie;
import ru.yandexmoney.config.WebDriverFactory;
import ru.yandexmoney.enums.OSType;
import ru.yandexmoney.pages.BasePage;

import java.util.Locale;
import java.util.Set;

import static org.testng.Assert.fail;

public class Utils {

    /**
     * detect the operating system from the os.name System property
     *
     * @return - the operating system detected
     */
    public static OSType getOperatingSystemType() {
        OSType detectedSystem;
        String system = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);

        if ((system.contains("mac")) || (system.contains("darwin"))) {
            detectedSystem = OSType.MACOS;
        } else if (system.contains("win")) {
            detectedSystem = OSType.WINDOWS;
        } else if (system.contains("nux")) {
            detectedSystem = OSType.LINUX;
        } else {
            detectedSystem = OSType.OTHER;
        }

        return detectedSystem;
    }

    /**
     * determines if the user is authorized in the application
     *
     * @return - the true if the user is logged in
     */
    public static boolean isUserAuthorized() {
        return getCookies()
                .stream()
                .anyMatch(cookie -> cookie.getName().contains("WILDAUTH"));
    }

    /**
     * get Application Cookies
     *
     * @return - Set<Cookie>
     */
    public static Set<Cookie> getCookies() {
        return WebDriverFactory
                .getInstance()
                .getDriver()
                .manage()
                .getCookies();
    }

    /**
     * create new Page Instance
     *
     * @return - Application Page Object
     */
    public static <T extends BasePage> T newPageInstance(Class<T> pageClass) {
        T pageObject = null;

        try {
            pageObject = pageClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            fail("Can't create page object: " + e.getMessage());
        }

        return pageObject;
    }
}
