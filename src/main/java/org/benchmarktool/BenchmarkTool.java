package org.benchmarktool;

public class BenchmarkTool {
    public static void main(String[] args) {
        try {
            System.out.println(new LoadMaker(CommandLineParser.parse(args)).launch());
        } catch (Exception ex) {
            System.out.println(String.format(" >> Exception: '%s'", ex));
        }
    }
}
