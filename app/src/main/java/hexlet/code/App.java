package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a "
                + "difference.")
public final class App implements Runnable {
    /**
     * Some text here.
     */
    @CommandLine.Parameters(index = "0",
            description = "Path to the first file")
    String filePath1;
    /**
     * Some text here.
     */
    @CommandLine.Parameters(index = "1",
            description = "Path to the second file")
    String filePath2;
    /**
     * Some text here.
     */

    @Option(names = {"-f", "--format"}, description = "Output format "
            + "[default: stylish]")
    protected static String format = "stylish";
    /**
     * Some text here.
     */
    @Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this help message and exit.")
    private boolean helpRequested;
    /**
     * Some text here.
     */
    @Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    private boolean versionRequested;

    @Override
    public void run() {
        Differ.generate(filePath1, filePath2, format);
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        CommandLine commandLine = new CommandLine(new App());
        // Add a subcommand for displaying help
        commandLine.addSubcommand("help", new CommandLine.HelpCommand());
        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }
}
