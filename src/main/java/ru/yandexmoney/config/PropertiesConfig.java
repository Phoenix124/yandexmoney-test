package ru.yandexmoney.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.fail;

public final class PropertiesConfig {

    public static Properties properties;

    static {
        File globalConfigFile = new File("src/main/resources/config.properties");
        File localConfigFile = new File("src/main/resources/local.properties");

        Properties globalProperties = new Properties();
        Properties localProperties = new Properties();

        try {
            globalProperties.load(new FileInputStream(globalConfigFile));

            properties = new Properties();
            properties.putAll(globalProperties);

            if (localConfigFile.exists()) {
                localProperties.load(new FileInputStream(localConfigFile));
                properties.putAll(localProperties);
            }

        } catch (IOException e) {
            fail("Error open config file: " + e.getMessage());
        }
    }
}
