package org.benchmarktool;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

class BenchmarkOption {
    private String url;
    private Long num;
    private Long concurrency;
    private Long timeout;

    @Contract(pure = true)
    BenchmarkOption(String url_, Long num_, Long concurrency_, Long timeout_) throws Exception {
        if (url_.isEmpty())
        {
            throw new Exception("'url' should be non-empty");
        }
        if (num_ < 1)
        {
            throw new Exception("'num' should be greater than 0");
        }
        if (concurrency_ < 1)
        {
            throw new Exception("'concurrency' should be greater than 0");
        }
        if (timeout_ < 1)
        {
            throw new Exception("'timeout' should be greater than 0");
        }

        url = url_;
        num = num_;
        concurrency = concurrency_;
        timeout = timeout_;
    }

    String url() {
        return url;
    }

    Long num() {
        return num;
    }

    Long concurrency() {
        return concurrency;
    }

    Long timeout() {
        return timeout;
    }
}
