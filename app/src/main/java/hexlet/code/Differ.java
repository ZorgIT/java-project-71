package hexlet.code;

import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    public static void generate(String json1, String json2) throws Exception {
        Map<String, Object> map1 = getData(json1);
        Map<String, Object> map2 = getData(json2);
        List<List<String>> result = checkData(map1, map2);
        for (List<String> line : result) {
            System.out.printf("%s %s: %s \n", line.get(0), line.get(1),
                    line.get(2));
        }
    }

    public static Map getData(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map
                = objectMapper.readValue(content, new TypeReference<Map<String, Object>>() {
        });
        return map;
    }

    public static List lineAdd(String val1, String val2,
                               Object val3) {
        List<Object> line = new ArrayList<>();
        line.add(val1);
        line.add(val2);
        line.add(val3);
        return line;
    }

    public static List<List<String>> checkData(Map<String, Object> oldMap,
                                               Map<String,
                                                       Object> newMap) {
        List<List<String>> lineStatus = new ArrayList<>();
        // 1. идем по старому файлу через ентрисет, задаем переменные
        for (Map.Entry<String, Object> entryOld : oldMap.entrySet()) {
            Object newValue = newMap.get(entryOld.getKey());
            Object oldValue = entryOld.getValue();
            String curKey = entryOld.getKey();
            //2. проверяем, есть ли такой ключ в файле 2
            if (newValue != null) {
                //в2 Такой ключ есть, доп проверка - ключ имеет одинаковые значения
                if (newValue.equals(oldValue)) {
                    lineStatus.add(lineAdd(" ", curKey, oldValue));
                } else {
                    //  в3 Такой ключ есть, доп проверка - ключи имеют разные значения = установить
                    lineStatus.add(lineAdd("-", curKey, oldValue));
                    lineStatus.add(lineAdd("+", curKey, newValue));
                }
            } else {
                //в1 Такого ключа нет во второй коллекции - ключ удален
                lineStatus.add(lineAdd("-", curKey,
                        oldValue));
            }
        }
        for (Map.Entry<String, Object> entryNew : newMap.entrySet()) {
            String curKey = entryNew.getKey();
            Object oldValue = oldMap.get(curKey);
            //2. проверяем, есть ли такой ключ в файле 2
            if (oldValue == null) {
                //ключ отсуствовал в изначальном
                lineStatus.add(lineAdd("+", curKey, entryNew.getValue()));
            }
        }
        lineStatus.sort(Comparator.comparing(list -> list.get(1)));
        return lineStatus;
    }
}



