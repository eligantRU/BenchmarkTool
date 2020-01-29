package org.benchmarktool;

public class BenchmarkTool {
    public static void main(String[] args) {
        try {
            BenchmarkOption option = CommandLineParser.parse(args);
            HttpGetter getter = new HttpGetter(option.url(), option.timeout());
            System.out.println(getter.statusCode() + " " + getter.bytesCount());
        } catch (Exception ex) {
            System.out.println(String.format(" >> Exception: '%s'", ex));
        }
    }
}
