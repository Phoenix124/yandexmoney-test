package ru.yandexmoney.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.util.Arrays;

public class BaseTestListener extends TestListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(BaseTestListener.class);

    @Override
    public void onTestStart(ITestResult testResult) {
        logger.info("Test: {} started", testResult.getName());
        logger.info("Test description: {}", testResult.getMethod().getDescription());
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        logger.error("Test: {} finished with status {}", testResult.getName(), "Error");
        logger.error("Message: {}", testResult.getThrowable().getMessage());

        transformAnErrorToAssertionError(testResult);
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        logger.error("Test: {} finished with status {}", testResult.getName(), "Skipped");
        transformAnErrorToAssertionError(testResult);
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        logger.info("Test: {} finished with status {}", testResult.getName(), "Success");
    }

    private synchronized void transformAnErrorToAssertionError(ITestResult testResult) {
        Throwable throwable = testResult.getThrowable();
        String testResultMessage = throwable.getMessage();
        StackTraceElement[] stackTrace = throwable.getStackTrace();

        String[] errorMessages = {
                "Expected condition failed",
                "Can't locate an element",
                "Returned value cannot be converted to WebElement",
                "Element is not currently visible and may not be manipulated"
        };

        AssertionError assertionError = new AssertionError(testResultMessage);

        Arrays.stream(errorMessages)
                .filter(message -> testResultMessage != null && testResultMessage.contains(message))
                .findFirst()
                .ifPresent(message -> {
                    ITestResult result = Reporter.getCurrentTestResult();

                    result.setThrowable(assertionError);
                    result.getThrowable().setStackTrace(stackTrace);
                    result.setStatus(ITestResult.FAILURE);
                });
    }
}
