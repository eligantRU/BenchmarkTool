package org.benchmarktool;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.*;

public class BenchmarkStats {
    private List<Long> timers = Collections.synchronizedList(new ArrayList<>());
    private AtomicInteger success = new AtomicInteger();
    private AtomicInteger fails = new AtomicInteger();
    private AtomicLong totalBytesCount = new AtomicLong();

    void addTimer(long time) {
        timers.add(time);
    }

    void incrementFails() {
        fails.incrementAndGet();
    }

    void incrementSuccess() {
        success.incrementAndGet();
    }

    public int getFails() {
        return fails.get();
    }

    public int getSuccesses() {
        return success.get();
    }

    public long getTimersSum() {
        return timers.stream().mapToLong(Long::longValue).sum();
    }

    void addBytes(long count) {
        totalBytesCount.addAndGet(count);
    }

    public long getTotalBytesCount() {
        return totalBytesCount.get();
    }

    public OptionalDouble getTimersAverage() {
        return timers.stream().mapToLong(Long::longValue).average();
    }

    public long getPercentileBy(double percentile) {
        timers.sort(Comparator.comparingLong(Long::longValue));
        int index = (int) Math.ceil((percentile / (double) 100) * (double) timers.size());
        if (index == -1) {
            return 0;
        }
        return timers.get(index - 1);
    }
}
