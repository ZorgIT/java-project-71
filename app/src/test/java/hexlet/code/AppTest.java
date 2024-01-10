package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {

    @Test
    public void testRun() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        App app = new App();
        app.setFilePath1("src/test/resources/file1.json");
        app.setFilePath2("src/test/resources/file2.json");

        app.run();
        String output = outContent.toString().trim();

        assertNotNull(output);
    }

    @Test
    public void testMain() {
    }
}
