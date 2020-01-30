package org.benchmarktool.response;

public class ResponseInfo {
    private final int status;
    private final int bytesCount;

    public ResponseInfo(int status_, int bytesCount_) {
        status = status_;
        bytesCount = bytesCount_;
    }

    public int status() {
        return status;
    }

    public int bytesCount() {
        return bytesCount;
    }
}
