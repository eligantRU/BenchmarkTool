package org.benchmarktool;

import org.apache.commons.cli.*;

public class BenchmarkTool {
    public static void main(String[] args) {
        try {
            BenchmarkOption option = parseCommandLineArguments(args);
            System.out.println(String.format("%s %d %d %d", option.url(), option.num(), option.concurrency(), option.timeout()));
        } catch (Exception ex) {
            System.out.println(String.format(" >> Exception: '%s'", ex));
        }
    }

    private static BenchmarkOption parseCommandLineArguments(String[] args) throws Exception {
        int DEFAULT_TIMEOUT = 30000;

        CommandLine line = (new DefaultParser()).parse(createOptions(), args);
        return new BenchmarkOption(
                (String) line.getParsedOptionValue("url"),
                ((Long) line.getParsedOptionValue("num")),
                ((Long) line.getParsedOptionValue("concurrency")),
                line.hasOption("timeout") ? (Long) line.getParsedOptionValue("timeout") : DEFAULT_TIMEOUT);
    }

    private static Options createOptions() {
        Options options = new Options();
        options.addOption(Option.builder()
                .longOpt("url")
                .desc("Target URL")
                .hasArg()
                .type(String.class)
                .required()
                .build()
        );
        options.addOption(Option.builder()
                .longOpt("num")
                .desc("Requests count")
                .hasArg()
                .type(Number.class)
                .required()
                .build()
        );
        options.addOption(Option.builder()
                .longOpt("concurrency")
                .desc("Threads count")
                .hasArg()
                .type(Number.class)
                .required()
                .build()
        );
        options.addOption(Option.builder()
                .longOpt("timeout")
                .desc("Timeout, default=30s")
                .hasArg()
                .type(Number.class)
                .build()
        );
        return options;
    }
}
