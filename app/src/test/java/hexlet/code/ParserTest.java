package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static hexlet.code.Parser.parseData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParserTest {

    @Test
    void testParseJsonData() throws Exception {
        String filePath = "src/test/resources/file1.json";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        Map<String, Object> result = parseData(content, "json");

        assertEquals("hexlet.io", result.get("host"));
        assertEquals(50, result.get("timeout"));
        assertEquals("123.234.53.22", result.get("proxy"));
        assertEquals(false, result.get("follow"));
    }

    @Test
    void testParseYmlData() throws Exception {
        String filePath = "src/test/resources/file1.yml";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        Map<String, Object> result = parseData(content, "yml");

        assertEquals("hexlet.io", result.get("host"));
        assertEquals(50, result.get("timeout"));
        assertEquals("123.234.53.22", result.get("proxy"));
        assertEquals(false, result.get("follow"));
    }


    @Test
    public void testParseDataWithUnsupportedContentType() {
        String content = "[1, 2, 3]";
        String contentType = "xml";

        Map<String, Object> result = Parser.parseData(content, contentType);

        assertNull(result);
    }

    @Test
    public void testParseDataWithIOException() {
        String content = "invalid content";
        String contentType = "json";

        Map<String, Object> result = Parser.parseData(content, contentType);

        assertNull(result);
    }
}
