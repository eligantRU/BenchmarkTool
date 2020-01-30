package org.benchmarktool.response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResponseInfoTest {
    static private final int TEST_STATUS = 200;
    static private final int TEST_BYTES_COUNT = 1024;
    private ResponseInfo info;

    @BeforeEach
    void init() {
        info = new ResponseInfo(TEST_STATUS, TEST_BYTES_COUNT);
    }

    @Test
    void checkStatus() {
        Assertions.assertEquals(info.status(), TEST_STATUS);
    }

    @Test
    void checkBytesCount() {
        Assertions.assertEquals(info.bytesCount(), TEST_BYTES_COUNT);
    }
}