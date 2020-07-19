package ru.yandexmoney.config;

import io.netty.util.internal.SystemPropertyUtil;
import ru.yandexmoney.enums.Locale;

import java.util.Properties;

public final class TestConfig {

    public static final Properties properties = PropertiesConfig.properties;
    public static final int IMPLICITLY_WAIT_IN_SECONDS;
    public static final int IMPLICITLY_WAIT_SHORT_TIMEOUT_IN_SECONDS;

    public static final String url;
    public static final int threadCount;
    public static final Locale locale;
    public static final String login;
    public static final String password;

    static {
        String threadCountParam = properties.getProperty("THREAD_COUNT_PARAM");
        String urlParam = properties.getProperty("URL_PARAM");
        String localeParam = properties.getProperty("LOCALE_PARAM");
        String loginParam = properties.getProperty("USER_LOGIN");
        String passwordParam = properties.getProperty("USER_PASSWORD");

        String threadCountFromParams = SystemPropertyUtil.get(threadCountParam);
        String urlFromParams = SystemPropertyUtil.get(urlParam);
        String localeFromParams = SystemPropertyUtil.get(localeParam);
        String loginFromParams = SystemPropertyUtil.get(loginParam);
        String passwordFromParams = SystemPropertyUtil.get(passwordParam);

        threadCount = threadCountFromParams != null && !threadCountFromParams.equals("")
                ? Integer.parseInt(threadCountFromParams)
                : 1;

        url = urlFromParams != null && !urlFromParams.equals("")
                ? urlFromParams
                : properties.getProperty("URL_PARAM");

        locale = localeFromParams != null && !localeFromParams.equals("")
                ? Locale.valueOf(localeFromParams.toUpperCase())
                : Locale.valueOf(properties.getProperty("LOCALE").toUpperCase());

        if (loginFromParams != null && !loginFromParams.equals("")) {
            login = loginFromParams;
        } else {
            throw new IllegalArgumentException("Login is empty");
        }

        if (passwordFromParams != null && !passwordFromParams.equals("")) {
            password = passwordFromParams;
        } else {
            throw new IllegalArgumentException("Password is empty");
        }

        IMPLICITLY_WAIT_IN_SECONDS = Integer.parseInt(properties.getProperty("IMPLICITLY_WAIT_IN_SECONDS"));
        IMPLICITLY_WAIT_SHORT_TIMEOUT_IN_SECONDS = Integer.parseInt(properties.getProperty("IMPLICITLY_WAIT_SHORT_TIMEOUT_IN_SECONDS"));
    }
}
