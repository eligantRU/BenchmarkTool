package org.benchmarktool.loadgenerator;

import com.google.common.base.Stopwatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.io.IOException;

import org.benchmarktool.httpgetter.HttpGetter;
import org.benchmarktool.response.ResponseInfo;
import org.benchmarktool.settings.Settings;
import org.benchmarktool.stats.Stats;
import org.jetbrains.annotations.NotNull;

// --url="https://www.sketch.com/404error" --num=10 --concurrency=4 --timeout=1000
// --url="http://ya.ru" --num=5 --concurrency=4 --timeout=2000

public class LoadGenerator {
    private final ExecutorService pool;
    private final Settings option;

    public LoadGenerator(@NotNull Settings option_) {
        option = option_;
        pool = Executors.newFixedThreadPool(option.concurrency());
    }

    public Stats launch() throws InterruptedException {
        Stats stats = new Stats(option);

        Stopwatch totalWatch = Stopwatch.createStarted();
        AtomicInteger index = new AtomicInteger();
        IntStream.range(0, option.num()).forEach(n -> pool.execute(() -> {
            Stopwatch watch = Stopwatch.createStarted();
            try {
                HttpGetter getter = new HttpGetter(option.url(), option.timeout());
                ResponseInfo info = getter.emit();

                watch.stop();
                long elapsed = watch.elapsed(TimeUnit.MILLISECONDS);

                stats.addBytes(info.bytesCount());
                stats.addTimer(elapsed);

                int major_code = info.status() / 100;
                if (2 <= major_code && major_code <= 3) {
                    System.out.println(String.format("[%d] OK: %dms", index.incrementAndGet(), elapsed));
                    stats.incrementSuccess();
                } else {
                    System.out.println(String.format("[%d] Failure: %dms", index.incrementAndGet(), elapsed));
                    stats.incrementFails();
                }
            } catch (IOException e) {
                watch.stop();
                stats.incrementFails();
                System.out.println(String.format(" >> ThreadPull exception: '%s'", e.getMessage()));
            }
        }));
        totalWatch.stop();
        stats.setTotalTime(totalWatch.elapsed(TimeUnit.MILLISECONDS));

        pool.shutdown();
        pool.awaitTermination(option.timeout() * option.num() / option.concurrency(), TimeUnit.MILLISECONDS);

        return stats;
    }
}
