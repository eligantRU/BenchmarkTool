package org.benchmarktool.stats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.benchmarktool.settings.Settings;
import org.junit.jupiter.api.Test;

class StatsTest {
    static private final String TEST_URL = "http://ya.ru";
    static private final Long TEST_NUM = 101L;
    static private final Long TEST_CONCURRENCY_LEVEL = 2L;
    static private final Long TEST_TIMEOUT = 1000L;

    @Test
    void check() throws Exception {
        Settings settings = new Settings(TEST_URL, TEST_NUM, TEST_CONCURRENCY_LEVEL, TEST_TIMEOUT);
        Stats stats = new Stats(settings);
        stats.addBytes(16);
        stats.addBytes(32);
        stats.incrementSuccess();
        stats.incrementSuccess();
        stats.incrementSuccess();
        stats.incrementFails();
        stats.incrementFails();
        stats.addTimer(10);
        stats.addTimer(20);
        stats.addTimer(30);
        stats.addTimer(40);
        stats.addTimer(50);
        stats.setTotalTime(100);
        assertEquals(stats.toString(),
                String.format("Concurrency level: %d%n" +
                        "Total time: %dms%n" +
                        "Requests count: %d%n" +
                        "Fails count: %d%n" +
                        "Received bytes: %d%n" +
                        "RPS: %.1f%n" +
                        "ART: %.1fms%n" +
                        "50%%: %d%n" +
                        "80%%: %d%n" +
                        "90%%: %d%n" +
                        "95%%: %d%n" +
                        "99%%: %d%n" +
                        "100%%: %d%n", TEST_CONCURRENCY_LEVEL, 100, TEST_NUM, 2, 48, 0.1, 30., 30, 40, 50, 50, 50, 50));
    }
}
