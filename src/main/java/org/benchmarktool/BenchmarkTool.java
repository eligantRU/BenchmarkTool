package org.benchmarktool;

public class BenchmarkTool {
    public static void main(String[] args) {
        try {
            BenchmarkStats stats = new LoadMaker(CommandLineParser.parse(args)).launch();
            System.out.println(stats);
        } catch (Exception ex) {
            System.out.println(String.format(" >> Exception: '%s'", ex));
        }
    }
}
