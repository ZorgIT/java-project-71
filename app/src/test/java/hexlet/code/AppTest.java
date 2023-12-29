package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    public void testRun() {
        // Arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        App app = new App();
        app.filePath1 = "src/test/resources/file1.json";
        app.filePath2 = "src/test/resources/file2.json";

        // Act
        app.run();
        String output = outContent.toString().trim();

        // Assert
        assertNotNull(output);
        // Add your assertion statements here based on the expected output
    }

    @Test
    public void testMain() {
    }
}