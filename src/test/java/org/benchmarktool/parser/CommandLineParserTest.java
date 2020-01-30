package org.benchmarktool.parser;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.benchmarktool.settings.Settings;
import org.junit.jupiter.api.Test;

class CommandLineParserTest {
    @Test
    void checkDefaultTimeout() throws Exception {
        String[] args = {"--url=http://ya.ru", "--num=10", "--concurrency=2"};
        assertTrue(areSettingsEqual(CommandLineParser.parse(args), new Settings("http://ya.ru", 10L, 2L, (long) CommandLineParser.DEFAULT_TIMEOUT)));
    }

    @Test
    void checkCustomTimeout() throws Exception {
        String[] args = {"--url=http://ya.ru", "--num=10", "--concurrency=2", "--timeout=1"};
        assertTrue(areSettingsEqual(CommandLineParser.parse(args), new Settings("http://ya.ru", 10L, 2L, 1L)));
    }

    @Test
    void checkNoWorkingWithoutNonTimeoutArgument() {
        String[] args = {};
        assertThrows(Exception.class, () -> CommandLineParser.parse(args));
    }

    boolean areSettingsEqual(Settings lhs, Settings rhs) {
        return (lhs.url().equals(rhs.url()))
                && (lhs.num() == rhs.num())
                && (lhs.concurrency() == rhs.concurrency())
                && (lhs.timeout() == rhs.timeout());
    }
}