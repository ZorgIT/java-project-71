package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
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
        } else if (this.format.equals("plain")) {
            StringBuilder sb = new StringBuilder();
            Iterator<List<String>> iterator = difference.iterator();
            while (iterator.hasNext()) {
                List<String> line1 = iterator.next();
                String lineStatus = line1.get(0);
                if (lineStatus.equals(" ")) {
                    continue;
                }
                if (!lineStatus.equals(" ")) {
                    if (lineStatus.equals("-")) {
                        sb.append("Property " + "\'" + line1.get(1) + "\' ");
                        List<String> line2 = new ArrayList<>();
                        if (iterator.hasNext()) {
                            line2 = iterator.next();
                        } else {
                            sb.append("was removed");
                        }
                        if (line1.get(1).equals(line2.get(1))) {
                            sb.append("was updated. From ");
                            if (line1.get(2).charAt(0) == '['
                                    || line1.get(2).charAt(0) == '{') {
                                sb.append("[complex value] ");
                            } else {
                                sb.append(line1.get(2) + " ");
                            }
                            if (line2.get(2).charAt(0) == '['
                                    || line1.get(2).charAt(0) == '{') {
                                sb.append("to [complex value]");
                            } else {
                                sb.append("to " + line2.get(2) + " ");
                            }
                        } else {
                            sb.append("was removed");
                        }

                    } else {
                        sb.append("Property " + "\'" + line1.get(1) + "\' was"
                                + " added with value: ");
                        if (line1.get(2).charAt(0) == '['
                                || line1.get(2).charAt(0) == '{') {
                            sb.append("[complex value] ");
                        } else {
                            sb.append(line1.get(2) + " ");
                        }

                    }
                }
                sb.append("\n");
            }
            System.out.printf(sb.toString());
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
