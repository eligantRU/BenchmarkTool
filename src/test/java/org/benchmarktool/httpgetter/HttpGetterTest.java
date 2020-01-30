package org.benchmarktool.httpgetter;

import static org.junit.jupiter.api.Assertions.*;

import org.benchmarktool.response.ResponseInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class HttpGetterTest {
    @Test
    void checkResponseWithValidUrl() throws IOException {
        ResponseInfo info = new HttpGetter("http://ya.ru", 10000).emit();
        assertEquals(info.status(), 200);
    }

    @Test
    void checkResponseWithNotValidUrl() throws IOException {
        ResponseInfo info = new HttpGetter("https://www.sketch.com/404error", 10000).emit();
        assertEquals(info.status(), 404);
    }
}
