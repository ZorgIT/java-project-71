package hexlet.code;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

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

    @Test
    void testGenerateStylish() throws JsonProcessingException {
        String expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        String actual = generate("src/test/resources/file3.json",
                "src/test/resources/file4.json", "Stylish");
        assertEquals(expected, actual);
    }

    @Test
    void testGeneratePlain() throws JsonProcessingException {
        App.setFormat("plain");
        String expected = "Property 'chars2' was updated. From [complex "
                + "value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex "
                + "value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to "
                + "[complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to "
                + "'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";
        String actual = generate("src/test/resources/file3.json",
                "src/test/resources/file4.json", "plain");
        assertEquals(expected, actual);
    }

}
