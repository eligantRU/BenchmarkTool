package org.benchmarktool;

class ResponseInfo {
    private final int status;
    private final int bytesCount;

    ResponseInfo(int status_, int bytesCount_) {
        status = status_;
        bytesCount = bytesCount_;
    }

    int status() {
        return status;
    }

    int bytesCount() {
        return bytesCount;
    }
}
