package org.benchmarktool;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;

class CommandLineParser {
    static BenchmarkOption parse(String[] args) throws Exception {
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
