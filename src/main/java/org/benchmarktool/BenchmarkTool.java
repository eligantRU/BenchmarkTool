package org.benchmarktool;

import org.benchmarktool.loadgenerator.LoadGenerator;
import org.benchmarktool.parser.CommandLineParser;

public class BenchmarkTool {
    public static void main(String[] args) {
        try {
            System.out.println(new LoadGenerator(CommandLineParser.parse(args)).launch());
        } catch (Exception ex) {
            System.out.println(String.format(" >> Exception: '%s'", ex));
        }
    }
}
