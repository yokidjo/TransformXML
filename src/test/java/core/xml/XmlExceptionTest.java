package core.xml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class XmlExceptionTest {
    @Test
    void testConstructor() {
        XmlException test = new XmlException("string");
        assertNotNull(test);
    }

}