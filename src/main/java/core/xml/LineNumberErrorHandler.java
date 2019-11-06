package core.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

class LineNumberErrorHandler implements ErrorHandler {

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        String e = "Line: " + exception.getLineNumber() + " : " + exception.getMessage();
        throw new SAXException(e);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        String e = "Line: " + exception.getLineNumber() + " : " + exception.getMessage();
        throw new SAXException(e);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        String e = "Line: " + exception.getLineNumber() + " : " + exception.getMessage();
        throw new SAXException(e);
    }
}
