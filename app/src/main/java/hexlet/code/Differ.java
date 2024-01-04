package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static hexlet.code.Parser.parseData;

final class Differ {
    private Differ() {
    }

    public static void generate(final String filePath1,
                                              final String filePath2,
                                              final String format) {
        String[] contentType = filePath1.split("\\.");
        try {
            String file1 = readFile(filePath1);
            String file2 = readFile(filePath2);
            Map<String, String> map1 = parseData(file1, contentType[1]);
            Map<String, String> map2 = parseData(file2, contentType[1]);
            showDiff(checkData(map1, map2),format);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> lineAdd(final String val1, final String val2,
                                       final Object val3) {
        List<String> line = new ArrayList<>();
        line.add(val1);
        line.add(val2);
        line.add(val3.toString());
        return line;
    }

    public static List<List<String>> checkData(final Map<String, String> oldMap,
                                               final Map<String,
                                                       String> newMap) {
        List<List<String>> lineStatus = new ArrayList<>();

        for (Map.Entry<String, String> entryOld : oldMap.entrySet()) {
            Object newValue = newMap.get(entryOld.getKey());
            Object oldValue = entryOld.getValue();
            String curKey = entryOld.getKey();

            if (newValue != null) {
                if (newValue.equals(oldValue)) {
                    lineStatus.add(lineAdd(" ", curKey, oldValue));
                } else {
                    lineStatus.add(lineAdd("-", curKey, oldValue));
                    lineStatus.add(lineAdd("+", curKey, newValue));
                }
            } else {
                lineStatus.add(lineAdd("-", curKey, oldValue));
            }
        }

        for (Map.Entry<String, String> entryNew : newMap.entrySet()) {
            String curKey = entryNew.getKey();
            Object oldValue = oldMap.get(curKey);
            if (oldValue == null) {
                lineStatus.add(lineAdd("+", curKey, entryNew.getValue()));
            }
        }
        lineStatus.sort(Comparator.comparing(list -> list.get(1)));
        return lineStatus;
    }

    private static String readFile(final String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }

    private static void showDiff(List<List<String>> difference, String format) {
        if (format.equals("stylish")) {
            System.out.println("\n{");
            for (List<String> line : difference) {
                System.out.printf("  %s %s: %s \n", line.get(0), line.get(1),
                        line.get(2));
            }
            System.out.println("}");
        } else if (format.equals("plain")) {
            StringBuilder sb = new StringBuilder();
            Iterator<List<String>> iterator = difference.iterator();
            while (iterator.hasNext()) {
                List<String> line1 = iterator.next();
                String lineStatus = line1.get(0);
                if (lineStatus.equals(" ")) {
                    continue;
                }
                if (lineStatus.equals("-")) {
                    sb.append("Property " + "\'" + line1.get(1) + "\' ");
                    List<String> line2 = new ArrayList<>();
                    if (iterator.hasNext()) {
                        line2 = iterator.next();
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
                            sb.append("was removed" + "\n");
                            sb.append("Property " + "\'" + line2.get(1) + "' " +
                                    "was"
                                    + " added with value: ");
                            if (line2.get(2).charAt(0) == '['
                                    || line2.get(2).charAt(0) == '{') {
                                sb.append("[complex value] ");
                            } else {
                                sb.append(line2.get(2) + " ");
                            }
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
                sb.append("\n");
            }
            System.out.printf(sb.toString());
        }
    }
}



