import core.PathException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
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
                "C:\\Users\\patseios\\Desktop\\sbox\\Project",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xml",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsd",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsl"};
        optionNotValid = new String[]{"string"};
        notExistPath = new String[]{
                "C:\\Users\\patseios\\Desktop\\sbox\\Project",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xml",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsd",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsd"};

    }

    @Test
    void main() {
        PrintStream printStream = System.out;
        System.setOut(new PrintStream(output));
        Main.main(optionValid);
        assertTrue(output.toString().replaceAll("\n", "").contains("Transform file success."),
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
    void testCatchBlock() {
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