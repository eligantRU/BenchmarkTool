package org.benchmarktool;

import com.google.common.base.Stopwatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.io.IOException;

import org.jetbrains.annotations.NotNull;

// --url="https://www.sketch.com/404error" --num=10 --concurrency=4 --timeout=1000
// --url="http://ya.ru" --num=5 --concurrency=4 --timeout=2000

class LoadMaker {
    private final ExecutorService pool;
    private final BenchmarkOption option;

    LoadMaker(@NotNull BenchmarkOption option_) {
        option = option_;
        pool = Executors.newFixedThreadPool(option.concurrency());
    }

    BenchmarkStats launch() throws InterruptedException {
        BenchmarkStats stats = new BenchmarkStats();

        AtomicInteger index = new AtomicInteger();
        LongStream.range(0, option.num()).forEach(i -> pool.execute(() -> {
            Stopwatch  watch = Stopwatch.createStarted();
            try {
                HttpGetter getter = new HttpGetter(option.url(), option.timeout());
                ResponseInfo info = getter.emit();

                stats.addBytes(info.bytesCount());
                watch.stop();
                long elapsed = watch.elapsed(TimeUnit.MILLISECONDS);

                int major_code = info.status() % 100;
                if (2 <= major_code && major_code <= 3) {
                    System.out.println(String.format("Failure %d: %d ms", index.incrementAndGet(), elapsed));
                    stats.incrementFails();
                    return;
                }

                stats.addTimer(elapsed);

                System.out.println(String.format("OK %d: %d ms", index.incrementAndGet(), elapsed));
                stats.incrementSuccess();
            } catch (IOException e) {
                watch.stop();
                stats.incrementFails();
                System.out.println(String.format(" >> Exception: '%s'", e.getMessage()));
            }
        }));

        pool.shutdown();
        pool.awaitTermination(option.num() / option.concurrency() * option.timeout(), TimeUnit.MILLISECONDS);
        return stats;
    }
}