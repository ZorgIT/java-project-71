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

    public static List<List<String>> generate(final String json1,
                                              final String json2)
            throws Exception {
        Map<String, Object> map1 = getJsonData(json1);
        Map<String, Object> map2 = getJsonData(json2);
        List<List<String>> result = checkData(map1, map2);
        return result;
    }

    public static Map<String, Object> getJsonData(final String content) {
        System.out.println("Received JSON content: " + content);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue(content,
                    new TypeReference<Map<String, Object>>() {
                    });
            System.out.println("JSON parsed successfully.");
            return map;
        } catch (IOException e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> getYmlData(final String content) {
        System.out.println("Received Yml content: " + content);

        try {
            ObjectMapper objectMapper = new YAMLMapper();
            Map<String, Object> map = objectMapper.readValue(content,
                    new TypeReference<Map<String, Object>>() {
                    });
            System.out.println("YAML parsed successfully.");
            return map;
        } catch (IOException e) {
            System.out.println("Error parsing YAML: " + e.getMessage());
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

            if (newValue.equals(oldValue)) {
                lineStatus.add(lineAdd(" ", curKey, oldValue));
            } else {
                lineStatus.add(lineAdd("-", curKey, oldValue));
                lineStatus.add(lineAdd("+", curKey, newValue));
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



