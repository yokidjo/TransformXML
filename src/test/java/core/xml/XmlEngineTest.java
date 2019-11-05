package core.xml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XmlEngineTest {
    private String pathXML;
    private String pathXSD;
    private String pathXSL;
    private String pathOut;

    @BeforeEach
    void setUp() {
        pathXML = ClassLoader.getSystemClassLoader().getResource("res/books.xml").getPath();
        pathXSD = ClassLoader.getSystemClassLoader().getResource("res/books.xsd").getPath();
        pathXSL = ClassLoader.getSystemClassLoader().getResource("res/books.xsl").getPath();
        pathOut = "C:\\Users\\patseios\\Desktop\\sbox\\Project";
    }

    @Test
    void transformFile() {
        boolean expected = XmlEngine.transformFile(pathXML, pathXSL, pathOut);
        assertTrue(expected);
    }

    @Test
    void validateFIle() {
        boolean expected = XmlEngine.validateFIle(pathXML, pathXSD);
        assertTrue(expected);
    }
}