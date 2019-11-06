import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private String[] optionValid;
    private String[] optionNotValid;
    private String[] notExistPath;

    @BeforeEach
    void setUp() {
        Path  out = Paths.get("src/test/resources/Main/out");

        optionValid = new String[]{
                out.toAbsolutePath().toString(),
                Paths.get(
                        "src/test/resources/Main/setUp/books.xml").toAbsolutePath().toString(),
                Paths.get(
                        "src/test/resources/Main/setUp/books.xsd").toAbsolutePath().toString(),
                Paths.get(
                        "src/test/resources/Main/setUp/books.xsl").toAbsolutePath().toString()
        };
        notExistPath = new String[]{
                "A:\\TMP",
                "A:\\TMP\\books.xml",
                "A:\\TMP\\books.xsd",
                "A:\\TMP\\books.xsd"
        };
        optionNotValid = new String[]{"string"};

    }

    @Test
    void allParametersAreValid() {
        PrintStream printStream = System.out;
        System.setOut(new PrintStream(output));
        Main.main(optionValid);
        assertTrue(output.toString().replaceAll("\n", "").contains("done."),
                "Successfully");
    }

    @Test
    void countOptions() {
        PrintStream printStream = System.out;
        System.setOut(new PrintStream(output));
        Main.main(optionNotValid);
        assertEquals(output.toString().replaceAll("\n", ""),
                "Please, Run the application with 4 options:the path to the xml, xsd, xsl file and the path to the output folder\r",
                "Successfully");
    }

    @Test
    void testTakeException() {
        PrintStream printStream = System.out;
        System.setOut(new PrintStream(output));
        Main.main(notExistPath);
        assertTrue(output.toString().replaceAll("\n", "").contains("Error!"),
                "Successfully");
    }

    @Test
    void testConstructor() {
        Main obj = new Main();
        assertNotNull(obj);
    }
}