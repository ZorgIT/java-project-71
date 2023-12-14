package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a " +
                "difference. \n \tfilepath1 \tpath to first file \n " +
                "\tfilepath2 \tpath to second file")
public class App implements Runnable {

    @Option(names = {"-f", "--format"}, description = "Output format " +
            "[default: stylish]")
    String format = "stylish";
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean helpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionRequested;

    @Override
    public void run() {
        String json1 = "{\n" +
                "  \"host\": \"hexlet.io\",\n" +
                "  \"timeout\": 50,\n" +
                "  \"proxy\": \"123.234.53.22\",\n" +
                "  \"follow\": false\n" +
                "}";
        String json2 = "{\n" +
                "  \"timeout\": 20,\n" +
                "  \"verbose\": true,\n" +
                "  \"host\": \"hexlet.io\"\n" +
                "}";

        try {
            Differ.generate(json1, json2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.printf("Comparing two configuration files with format: " +
                "%s%n", format);
    }
    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new App());
        commandLine.addSubcommand("help", new CommandLine.HelpCommand()); // Add a subcommand for displaying help
        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }
}
