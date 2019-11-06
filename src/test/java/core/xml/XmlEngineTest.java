package core.xml;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class XmlEngineTest {

    @Test
    void testExistFilesValidate() {
        Path pathXML = Paths.get("A:\\test");
        Path pathXSD = Paths.get("A:\\test");
        assertThrows(XmlException.class, () -> XmlEngine.validateFile(pathXML, pathXSD));
    }

    @Test
    void testExistFilesTransform() {
        Path pathXML = Paths.get("A:\\test");
        Path pathXSL = Paths.get("A:\\test");
        Path pathOUT = Paths.get("A:\\test");
        assertThrows(XmlException.class, () -> XmlEngine.transformFile(pathXML, pathXSL, pathOUT));
    }

    @Test
    void testNotExistFilesValidate() {
        Path pathXML = Paths.get("src/test/resources/XmlEngine/setUp/books.xml").toAbsolutePath();
        Path pathXSD = Paths.get("src/test/resources/XmlEngine/badFiles/books.xsd");
        try {
            XmlEngine.validateFile(pathXML, pathXSD);
        } catch (XmlException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCreateDir() {
        Path pathXML = Paths.get("src/test/resources/XmlEngine/setUp/books.xml").toAbsolutePath();
        Path pathXSL = Paths.get("src/test/resources/XmlEngine/setUp/books.xsl");
        Path pathOUT = Paths.get("src/test/resources/XmlEngine/out");
        try {
            Files.walk(pathOUT)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
            XmlEngine.transformFile(pathXML, pathXSL, pathOUT);
        } catch (IOException | XmlException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testTransformerException() {
        Path pathXML = Paths.get("src/test/resources/XmlEngine/setUp/books.xml").toAbsolutePath();
        Path pathXSL = Paths.get("src/test/resources/XmlEngine/badFiles/books.xsl");
        Path pathOUT = Paths.get("src/test/resources/XmlEngine/out");
        assertThrows(XmlException.class, () -> XmlEngine.transformFile(pathXML, pathXSL, pathOUT));
    }

    @Test
    void testSAXException() {
        Path pathXML = Paths.get("src/test/resources/XmlEngine/setUp/books.xml");
        Path pathXSD = Paths.get("src/test/resources/XmlEngine/badFiles/books.xsd");

        assertThrows(XmlException.class, () -> XmlEngine.validateFile(pathXML, pathXSD));
    }




    @Test
    void testConstructor() {
        XmlEngine xmlEngine = new XmlEngine();
        assertNotNull(xmlEngine);
    }
}