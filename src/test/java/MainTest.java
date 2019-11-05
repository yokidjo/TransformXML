import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private String[] optionValid;
    private String[] optionNotValid;
    private String[] notExistPath;


    @BeforeEach
    void setUp() {

        optionValid = new String[]{
                new File(
                        "src/test/resources/Main/out").getAbsolutePath(),
                new File(
                        "src/test/resources/Main/setUp/books.xml").getAbsolutePath(),
                new File(
                        "src/test/resources/Main/setUp/books.xsd").getAbsolutePath(),
                new File(
                        "src/test/resources/Main/setUp/books.xsl").getAbsolutePath()
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