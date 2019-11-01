import core.xml.XmlEngine;

import java.net.URL;


public class Main {
    private final static URL FILE_XML = Main.class.getResource("data/books.xml");
    private final static URL FILE_XSD = Main.class.getResource("data/books.xsd");
    private final static URL FILE_XSL = Main.class.getResource("data/books.xsl");

    //static final Logger userLogger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        if (XmlEngine.validateFIle(FILE_XML.getPath(), FILE_XSD.getPath())) {
            boolean b = XmlEngine.transformFile(FILE_XML.getPath(), FILE_XSL.getPath());
        }
    }
}
