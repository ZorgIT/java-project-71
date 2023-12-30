package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


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
    private String format = "stylish";
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
        try {
            String file1 = readFile(filePath1);
            String file2 = readFile(filePath2);
            String[] contentType = filePath1.split("\\.");
            showDiff(Differ.generate(file1, file2, contentType[1]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String readFile(final String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }

    private void showDiff(List<List<String>> difference) {
        if (this.format.equals("stylish")) {
            System.out.println("\n{");
            for (List<String> line : difference) {
                System.out.printf("  %s %s: %s \n", line.get(0), line.get(1),
                        line.get(2));
            }
            System.out.println("}");
        } else if (this.format.equals("plane")){
            System.out.printf("PLANE OUTPUT NOT IMPLEMENTED");
        }

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
