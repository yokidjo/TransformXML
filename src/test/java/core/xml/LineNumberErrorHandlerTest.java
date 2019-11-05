package core.xml;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LineNumberErrorHandlerTest {

    @Test
    void warning() {
        assertThrows(SAXException.class, () ->
                new LineNumberErrorHandler().warning(
                        new SAXParseException("message", "publicId", "systemId", 1, 23)
                ));
    }

    @Test
    void error() {
        assertThrows(SAXException.class, () ->
                new LineNumberErrorHandler().error(
                        new SAXParseException("message", "publicId", "systemId", 1, 23)
                ));
    }

    @Test
    void fatalError() {
        assertThrows(SAXException.class, () ->
                new LineNumberErrorHandler().fatalError(
                        new SAXParseException("message", "publicId", "systemId", 1, 23)
                ));
    }
}