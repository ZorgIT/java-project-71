package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;

import static hexlet.code.Differ.lineAdd;
import static hexlet.code.Differ.checkData;
import static hexlet.code.Differ.generate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {



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
    }

    @Test
    void testGenerate() throws IOException {

        String filePath1 = "src/test/resources/file1.json";
        String content1 = new String(Files.readAllBytes(Paths.get(filePath1)));
        String filePath2 = "src/test/resources/file2.json";
        String content2 = new String(Files.readAllBytes(Paths.get(filePath2)));


        List<List<String>> result = generate(content1, content2, "json");

        List<List<String>> expected = new ArrayList<>();
        expected.add(Arrays.asList("-", "follow", "false"));
        expected.add(Arrays.asList(" ", "host", "hexlet.io"));
        expected.add(Arrays.asList("-", "proxy", "123.234.53.22"));
        expected.add(Arrays.asList("-", "timeout", "50"));
        expected.add(Arrays.asList("+", "timeout", "20"));
        expected.add(Arrays.asList("+", "verbose", "true"));

        assertEquals(expected.size(), result.size());

        for (int i = 0; i < expected.size(); i++) {
            List<String> expectedLine = expected.get(i);
            List<String> resultLine = result.get(i);

            assertEquals(expectedLine, resultLine);
        }
    }
}
