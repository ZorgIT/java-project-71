package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a " +
                "difference. \n filepath1 path to first file \n filepath2 " +
                "path to second file")
public class App implements Runnable {
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean helpRequested;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionRequested;

    @Override
    public void run() {
        // Your code to compare and show the difference of two configuration files goes here...
        Differ.generate();
        System.out.println("Comparing two configuration files...");
    }
    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new App());
        commandLine.addSubcommand("help", new CommandLine.HelpCommand()); // Add a subcommand for displaying help
        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }
}
