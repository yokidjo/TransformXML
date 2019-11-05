package core;

import org.junit.jupiter.api.Assertions;
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

    @Test
    void testNullPath() {
        String[] expected = {
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xml",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsd",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsd",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project"};
        Assertions.assertThrows(PathException.class, () -> Utilities.getPaths(expected, ""));
    }

    @Test
    void testNotExistFile() {
        String[] expected = {
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books1.xml",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books1.xsd",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books1.xsl",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project1"};
        Assertions.assertThrows(PathException.class, () -> Utilities.getPaths(expected, ""));
    }

    @Test
    void testFormatFilesBranches() {
        String[] expected = {
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xml",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsd",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.txt",
                "C:\\Users\\patseios\\Desktop\\sbox\\Project"};
        Assertions.assertThrows(PathException.class, () -> Utilities.getPaths(expected,
                ""));
    }

    @Test
    void testFilesWithOutAbsolutePath() {
        try {
            String[] expected = {
                    "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xml",
                    "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsd",
                    "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsl",
                    "C:\\Users\\patseios\\Desktop\\sbox\\Project"};
            String[] test = {
                    "books.xml",
                    "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsd",
                    "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\books.xsl",
                    "C:\\Users\\patseios\\Desktop\\sbox\\Project"};
            String[] actual = Utilities.getPaths(test, "C:\\Users\\patseios\\Desktop\\sbox\\Project\\TransformXML\\src\\main\\resources\\res\\");
            assertArrayEquals(expected, actual);
        } catch (PathException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCountOptions() {
        String[] expected = {"C:\\Users\\patseios\\Desktop\\sbox\\Project1"};
        Assertions.assertThrows(PathException.class, () -> Utilities.getPaths(expected, ""));
    }
}