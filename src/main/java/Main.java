import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    private final static URL FILE_XML = Main.class.getResource("song.xml");
    private final static URL FILE_XSD = Main.class.getResource("song.xsd");

    public static void main(String[] args) {
        try {

            Source xmlFile = new StreamSource(new File(FILE_XSD.getFile()));
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(FILE_XSD);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
