package org.benchmarktool.settings;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SettingsTest {
    static private final String TEST_URL = "http://ya.ru";
    static private final Long TEST_NUM = 101L;
    static private final Long TEST_CONCURRENCY_LEVEL = 2L;
    static private final Long TEST_TIMEOUT = 1000L;
    private Settings settings;

    @BeforeEach
    void init() throws Exception {
        settings = new Settings(TEST_URL, TEST_NUM, TEST_CONCURRENCY_LEVEL, TEST_TIMEOUT);
    }

    @Test
    void checkUrl() {
        Assertions.assertEquals(settings.url(), TEST_URL);
    }

    @Test
    void checkNum() {
        Assertions.assertEquals(settings.num(), TEST_NUM);
    }

    @Test
    void checkConcurrency() {
        Assertions.assertEquals(settings.concurrency(), TEST_CONCURRENCY_LEVEL);
    }

    @Test
    void checkTimeout() {
        Assertions.assertEquals(settings.timeout(), TEST_TIMEOUT);
    }

    @Test
    void checkConstructorUrl() {
        assertThrows(Exception.class, () -> new Settings("", TEST_NUM, TEST_CONCURRENCY_LEVEL, TEST_TIMEOUT));
    }

    @Test
    void checkConstructorNum() {
        assertThrows(Exception.class, () -> new Settings(TEST_URL, 0L, TEST_CONCURRENCY_LEVEL, TEST_TIMEOUT));
    }

    @Test
    void checkConstructorConcurrency() {
        assertThrows(Exception.class, () -> new Settings(TEST_URL, TEST_NUM, 0L, TEST_TIMEOUT));
    }

    @Test
    void checkConstructorTimeout() {
        assertThrows(Exception.class, () -> new Settings(TEST_URL, TEST_NUM, TEST_CONCURRENCY_LEVEL, 0L));
    }
}