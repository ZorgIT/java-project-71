package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Map;
import java.util.List;
import java.util.Comparator;

import static hexlet.code.Parser.parseData;

public final class Differ {
    private Differ() {
    }

    public static String generate(final String filePath1,
                                  final String filePath2,
                                  final String format) {
        String[] contentType = filePath1.split("\\.");
        List<List<String>> difference;
        try {
            String file1 = readFile(filePath1);
            String file2 = readFile(filePath2);
            Map<String, String> map1 = parseData(file1, contentType[1]);
            Map<String, String> map2 = parseData(file2, contentType[1]);
            difference = checkData(map1, map2);
            Formatter.showDiff(difference, format);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Formatter.convertToFormat(difference, format);
    }

    public static String generate(final String filePath1,
                                  final String filePath2) {
        return generate(filePath1, filePath2, "plain");
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

}



