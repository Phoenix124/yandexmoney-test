package ru.yandexmoney.helpers;

import io.qameta.allure.Step;

public final class AssertHelper {

    private AssertHelper() {
        //
    }

    @Step("{stepName}")
    public static void assertTrue(String stepName, boolean value) {
        org.testng.Assert.assertTrue(value);
    }

    @Step("{stepName}")
    public static void assertTrue(String stepName, boolean value, String errorMessage) {
        org.testng.Assert.assertTrue(value, errorMessage);
    }

    @Step("{stepName}")
    public static void assertFalse(String stepName, boolean value) {
        org.testng.Assert.assertFalse(value);
    }

    @Step("{stepName}")
    public static void assertFalse(String stepName, boolean value, String errorMessage) {
        org.testng.Assert.assertFalse(value, errorMessage);
    }

    @Step("{stepName}")
    public static void assertEquals(String stepName, int actual, int expected) {
        org.testng.Assert.assertEquals(actual, expected);
    }

    @Step("{stepName}")
    public static void assertEquals(String stepName, int actual, int expected, String errorMessage) {
        org.testng.Assert.assertEquals(actual, expected, errorMessage);
    }

    @Step("{stepName}")
    public static void assertEquals(String stepName, long actual, long expected) {
        org.testng.Assert.assertEquals(actual, expected);
    }

    @Step("{stepName}")
    public static void assertEquals(String stepName, long actual, long expected, String errorMessage) {
        org.testng.Assert.assertEquals(actual, expected, errorMessage);
    }

    @Step("{stepName}")
    public static void assertEquals(String stepName, Object actual, Object expected) {
        org.testng.Assert.assertEquals(actual, expected);
    }

    @Step("{stepName}")
    public static void assertEquals(String stepName, Object actual, Object expected, String errorMessage) {
        org.testng.Assert.assertEquals(actual, expected, errorMessage);
    }

    @Step("{stepName}")
    public static void assertNotEquals(String stepName, Object actual, Object expected) {
        org.testng.Assert.assertNotEquals(actual, expected);
    }

    @Step("{stepName}")
    public static void assertNotEquals(String stepName, Object actual, Object expected, String errorMessage) {
        org.testng.Assert.assertNotEquals(actual, expected, errorMessage);
    }

    @Step("{stepName}")
    public static void assertEquals(String stepName, double actual, double expected, double delta) {
        org.testng.Assert.assertEquals(actual, expected, delta);
    }

    @Step("{stepName}")
    public static void assertEquals(String stepName, double actual, double expected, double delta, String errorMessage) {
        org.testng.Assert.assertEquals(actual, expected, delta, errorMessage);
    }
}
