package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class UtilitiesTest {
    private String[] args;

    @BeforeEach
    void setUp() {
        args = new String[]{
                "C:\\Users\\patseios\\Desktop\\sbox\\Project",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xml",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsd",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsl"};
    }

    @Test
    void getPaths() {
        try {
            String[] expected = {
                    "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xml",
                    "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsd",
                    "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsl",
                    "C:\\Users\\patseios\\Desktop\\sbox\\Project"};
            String[] actual = Utilities.getPaths(expected, "");
            assertArrayEquals(expected, actual);
        } catch (PathException e) {
            e.printStackTrace();
        }
    }
}