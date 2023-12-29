package hexlet.code;


import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;

import static hexlet.code.Differ.getJsonData;
import static hexlet.code.Differ.getYmlData;
import static hexlet.code.Differ.lineAdd;
import static hexlet.code.Differ.checkData;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {

    @Test
    void testGetData() throws Exception {
        String filePath = "src/test/resources/file1.json";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        Map<String, Object> result = getJsonData(content);

        assertEquals("hexlet.io", result.get("host"));
        assertEquals(50, result.get("timeout"));
        assertEquals("123.234.53.22", result.get("proxy"));
        assertEquals(false, result.get("follow"));
    }

    @Test
    void testLineAdd() {
        List<Object> line = new ArrayList<>();
        line.add(" ");
        line.add("timeout");
        line.add("50");
        List result = lineAdd(" ", "timeout", "50");
        assertEquals(result.get(0), line.get(0));
        assertEquals(result.get(1), line.get(1));
        assertEquals(result.get(2), line.get(2));
    }

    @Test
    void testCheckData() {
        Map<String, Object> oldMap = new HashMap<>();
        oldMap.put("key1", "value1");
        oldMap.put("key2", "value2");

        Map<String, Object> newMap = new HashMap<>();
        newMap.put("key1", "value1");
        newMap.put("key2", "newvalue2");
        newMap.put("key3", "value3");

        List<List<String>> result = checkData(oldMap, newMap);

        List<List<String>> expected = new ArrayList<>();
        expected.add(Arrays.asList(" ", "key1", "value1"));
        expected.add(Arrays.asList("-", "key2", "value2"));
        expected.add(Arrays.asList("+", "key2", "newvalue2"));
        expected.add(Arrays.asList("+", "key3", "value3"));

        assertEquals(expected.size(), result.size());

        for (int i = 0; i < expected.size(); i++) {
            List<String> expectedLine = expected.get(i);
            List<String> resultLine = result.get(i);

            assertEquals(expectedLine, resultLine);
        }


//        Map<String, Object> oldMap1 = new HashMap<>();
//        oldMap.put("key1", "value1");
//        oldMap.put("key2", "value2");
//
//        Map<String, Object> newMap1 = new HashMap<>();
//        newMap.put("key1", "value1");
//
//        List<List<String>> result1 = checkData(oldMap1, newMap1);
//
//        List<List<String>> expected1 = Arrays.asList(
//                Arrays.asList("-", "key2", "value2")
//        );
//        assertEquals(expected1, result1);

    }

    @Test
    void testYamlData() throws Exception {
        String filePath = "src/test/resources/file1.yml";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        Map<String, Object> result1 = getYmlData(content);

        assertEquals("hexlet.io", result1.get("host"));
        assertEquals(50, result1.get("timeout"));
        assertEquals("123.234.53.22", result1.get("proxy"));
        assertEquals(false, result1.get("follow"));

        String filePath2 = "src/test/resources/file2.yml";
        String content2 = new String(Files.readAllBytes(Paths.get(filePath2)));
        Map<String, Object> result2 = getYmlData(content2);

        assertEquals(20, result2.get("timeout"));
        assertEquals(true, result2.get("verbose"));
        assertEquals("hexlet.io", result2.get("host"));
    }

}
