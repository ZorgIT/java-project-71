package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static hexlet.code.Differ.getData;
import static hexlet.code.Differ.lineAdd;
import static org.junit.jupiter.api.Assertions.*;



public class DifferTest {
    @Test
    void testGetData() throws Exception{
        String filePath = "src/test/resources/file1.json";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        Map<String, Object> result = getData(content);

        assertEquals("hexlet.io", result.get("host"));
        assertEquals(50, result.get("timeout"));
        assertEquals("123.234.53.22", result.get("proxy"));
        assertEquals(false, result.get("follow"));
    }

    @Test
    void testGenerate() {

    }

    @Test
    void testLineAdd() {
        List<Object> line = new ArrayList<>();
        line.add(" ");
        line.add("timeout");
        line.add(50);
        List result = lineAdd(" ", "timeout", 50);
        assertEquals(result.get(0), line.get(0));
        assertEquals(result.get(1), line.get(1));
        assertEquals(result.get(2), line.get(2));
    }

    @Test
    void testCheckData() {

    }
}
