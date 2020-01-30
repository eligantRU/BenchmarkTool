package org.benchmarktool.settings;

import org.jetbrains.annotations.NotNull;

public class Settings {
    private final String url;
    private final int num;
    private final int concurrency;
    private final int timeout;

    public Settings(@NotNull String url_, Long num_, Long concurrency_, Long timeout_) throws Exception {
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
        num = Math.toIntExact(num_);
        concurrency = Math.toIntExact(concurrency_);
        timeout = Math.toIntExact(timeout_);
    }

    public String url() {
        return url;
    }

    public int num() {
        return num;
    }

    public int concurrency() {
        return concurrency;
    }

    public int timeout() {
        return timeout;
    }
}
