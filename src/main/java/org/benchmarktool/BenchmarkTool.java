package org.benchmarktool;

public class BenchmarkTool {
    public static void main(String[] args) {
        try {
            BenchmarkOption option = CommandLineParser.parse(args);
            System.out.println(String.format("%s %d %d %d", option.url(), option.num(), option.concurrency(), option.timeout()));
        } catch (Exception ex) {
            System.out.println(String.format(" >> Exception: '%s'", ex));
        }
    }
}
