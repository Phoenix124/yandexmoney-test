package ru.yandexmoney.listeners;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

    private static final Logger logger = LoggerFactory.getLogger(SuiteListener.class);

    static {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    @Override
    public void onStart(ISuite suite) {
        logger.info("Suite start");
    }

    @Override
    public void onFinish(ISuite suite) {
        logger.info("Suite finish");
    }
}
