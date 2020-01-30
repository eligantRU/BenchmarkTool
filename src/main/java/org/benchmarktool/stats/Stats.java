package org.benchmarktool.stats;

import org.benchmarktool.settings.Settings;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.*;

public class Stats {
    private List<Long> timers = Collections.synchronizedList(new ArrayList<>());
    private AtomicInteger success = new AtomicInteger();
    private AtomicInteger fails = new AtomicInteger();
    private AtomicLong totalBytesCount = new AtomicLong();
    private final Settings settings;
    private long totalTime;

    public Stats(Settings settings_) {
        settings = settings_;
    }

    public void addTimer(long time) {
        timers.add(time);
    }

    public void incrementFails() {
        fails.incrementAndGet();
    }

    public void incrementSuccess() {
        success.incrementAndGet();
    }

    public void addBytes(long count) {
        totalBytesCount.addAndGet(count);
    }

    public void setTotalTime(long totalTime_) {
        totalTime = totalTime_;
    }

    private long getPercentileBy(int percentile) {
        if (timers.isEmpty())
        {
            return 0;
        }

        timers.sort(Comparator.comparingLong(Long::longValue));
        int index = (int) Math.ceil((percentile / 100.) * timers.size());
        return timers.get(index - 1);
    }

    @Override
    public String toString() {
        OptionalDouble artOpt = timers.stream().mapToLong(Long::longValue).average();
        double art = artOpt.isPresent() ? artOpt.getAsDouble() : 0.;
        return String.format("Concurrency level: %d%n" +
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
                "100%%: %d%n", settings.concurrency(), totalTime, settings.num(), fails.get(), totalBytesCount.get(),
                0.001 * timers.stream().mapToLong(Long::longValue).sum() / settings.concurrency(), art,
                getPercentileBy(50), getPercentileBy(80), getPercentileBy(90), getPercentileBy(95),
                getPercentileBy(99), getPercentileBy(100));
    }
}
