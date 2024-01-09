package hexlet.code;


import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;

import static hexlet.code.Differ.lineAdd;
import static hexlet.code.Differ.checkData;
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
        Map<String, String> oldMap = new HashMap<>();
        oldMap.put("key1", "value1");
        oldMap.put("key2", "value2");

        Map<String, String> newMap = new HashMap<>();
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
}
