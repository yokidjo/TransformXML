package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class TransformOptionTest {


    @BeforeEach
    void setUp() {
        Path out = Paths.get("src/test/resources/trOption/out");

        if (!Files.exists(out)) {
            try {
                out = Files.createDirectories(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void parseArgs() {
        try {
            String[] args = new String[]{
                    Paths.get("src/test/resources/trOption/testGetPaths/books.xml").toAbsolutePath().toString(),
                    Paths.get("src/test/resources/trOption/testGetPaths/books.xsd").toAbsolutePath().toString(),
                    Paths.get("src/test/resources/trOption/testGetPaths/books.xsl").toAbsolutePath().toString(),
                    Paths.get("src/test/resources/trOption/out").toAbsolutePath().toString()
            };
            Path[] expected = new Path[]{
                    Paths.get("src/test/resources/trOption/testGetPaths/books.xml").toAbsolutePath(),
                    Paths.get("src/test/resources/trOption/testGetPaths/books.xsd").toAbsolutePath(),
                    Paths.get("src/test/resources/trOption/testGetPaths/books.xsl").toAbsolutePath(),
                    Paths.get("src/test/resources/trOption/out").toAbsolutePath()
            };
            TransformOption tmp = TransformOption.parseArgs(args, Paths.get("."));
            Path[] actual = new Path[]{
                    tmp.getXmlPath(),
                    tmp.getXsdPath(),
                    tmp.getXslPath(),
                    tmp.getOutPath()
            };
            assertArrayEquals(expected, actual);
        } catch (PathException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testNotRightFile() {
        String[] args = new String[]{
                Paths.get("src/test/resources/trOption/testNotRightFile/1.txt").toAbsolutePath().toString(),
                Paths.get("src/test/resources/trOption/testNotRightFile/1.txt").toAbsolutePath().toString(),
                Paths.get("src/test/resources/trOption/testNotRightFile/1.txt").toAbsolutePath().toString(),
                Paths.get("src/test/resources/trOption/out").toAbsolutePath().toString()
        };
        assertThrows(PathException.class, () -> TransformOption.parseArgs(args, Paths.get(".")));
    }

    @Test
    void testNullPath() {
        String[] args = new String[]{
                Paths.get("src/test/resources/trOption/testGetPaths/books.xml").toString(),
                Paths.get("src/test/resources/trOption/testGetPaths/books.xml").toString(),
                Paths.get("src/test/resources/trOption/testGetPaths/books.xml").toString(),
                Paths.get("src/test/resources/trOption/out").toString()
        };
        assertThrows(PathException.class, () -> TransformOption.parseArgs(args, Paths.get(".")));
    }

    @Test
    void testCountArgs() {
        String[] args = new String[]{"string"};
        assertThrows(PathException.class, () -> TransformOption.parseArgs(args, Paths.get(".")));
    }

    @Test
    void testPathIsAbsolute() {
        String[] args = new String[]{
                Paths.get("trOption/testGetPaths/books.xml").toString(),
                Paths.get("trOption/testGetPaths/books.xsd").toString(),
                Paths.get("trOption/testGetPaths/books.xsl").toString(),
                Paths.get("trOption/out").toString()
        };

        try {
            assertNotNull(TransformOption.parseArgs(args, Paths.get("src/test/resources/")));
        } catch (PathException e) {
            e.printStackTrace();
        }
    }



}