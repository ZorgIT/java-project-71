package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

final class Differ {
    private Differ() {
    }

    public static List<List<String>> generate(final String file1,
                                              final String file2,
                                              final String contentType) {
        Map<String, Object> map1 = Parser.parseData(file1, contentType);
        Map<String, Object> map2 = Parser.parseData(file2, contentType);
        return checkData(map1, map2);
    }

    public static List<String> lineAdd(final String val1, final String val2,
                                       final Object val3) {
        List<String> line = new ArrayList<>();
        line.add(val1);
        line.add(val2);
        line.add(val3.toString());
        return line;
    }

    public static List<List<String>> checkData(final Map<String, Object> oldMap,
                                               final Map<String,
                                                       Object> newMap) {
        List<List<String>> lineStatus = new ArrayList<>();

        for (Map.Entry<String, Object> entryOld : oldMap.entrySet()) {
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

        for (Map.Entry<String, Object> entryNew : newMap.entrySet()) {
            String curKey = entryNew.getKey();
            Object oldValue = oldMap.get(curKey);
            if (oldValue == null) {
                lineStatus.add(lineAdd("+", curKey, entryNew.getValue()));
            }
        }
        lineStatus.sort(Comparator.comparing(list -> list.get(1)));
        return lineStatus;
    }
}



