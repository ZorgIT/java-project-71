package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

final class Differ {
    private Differ() {
    }

    public static List<List<String>> generate(final String file1,
                                              final String file2,
                                              final String contentType) {
        Map<String, Object> map1 = parseData(file1, contentType);
        Map<String, Object> map2 = parseData(file2, contentType);
        return checkData(map1, map2);
    }

    public static Map<String, Object> parseData(final String content,
                                                final String contentType) {
        //System.out.println("Received " + contentType + " content: " +
        // content);

        try {
            ObjectMapper objectMapper;
            if (contentType.equalsIgnoreCase("json")) {
                objectMapper = new ObjectMapper();
            } else if (contentType.equalsIgnoreCase("yml")) {
                objectMapper = new YAMLMapper();
            } else {
                System.out.println("Unsupported content type: " + contentType);
                return null;
            }

            Map<String, Object> map = objectMapper.readValue(content,
                    new TypeReference<>() {
                    });
//            System.out.println(contentType.toUpperCase() + " parsed successfully.");
            return map;
        } catch (IOException e) {
            System.out.println("Error parsing " + contentType + ": " + e.getMessage());
            e.printStackTrace();
            return null;
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



