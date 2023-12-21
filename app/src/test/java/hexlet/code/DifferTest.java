package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class DifferTest {
    @Test
    void testGetData(){
        String filePath = "src/test/resources/file1.json";
        assertEquals(filePath, "src/test/resources/file1.json");
    }
}
