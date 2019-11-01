package core.xml;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlEngine {

    /**
     * Method transform data file by style sheet
     *
     * @param pathXML       is path of data file
     * @param pathXSL       is patch of stylesheet file
     * @param pathDirectory is patch of output directory
     * @return true is success, else if files not exist or get exception
     */
    public static boolean transformFile(String pathXML, String pathXSL, String pathDirectory) {
        File dataFile = new File(pathXML);
        File stylesheet = new File(pathXSL);
        if (!dataFile.isFile() || !stylesheet.isFile()) {
            return false;
        }
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(stylesheet);
        try {
            Transformer transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(dataFile);
            File directory = new File(pathDirectory);
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    return false;
                }
            }
            transformer.transform(xml, new StreamResult(new File(directory.getPath() + "/out.xml")));
            return true;
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method validate data file  by XML schema
     *
     * @param pathXML is path of data file
     * @param pathXSD is path of XML schema
     * @return true is success, else if files not exist or get exception
     */
    public static boolean validateFIle(String pathXML, String pathXSD) {
        File xmlFile = new File(pathXML);
        File xsdFile = new File(pathXSD);
        if (!xmlFile.isFile() || !xsdFile.isFile()) {
            System.out.println("xml isFile");
            return false;
        }
        Source xmlSource = new StreamSource(xmlFile);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);
            System.out.println("File : " + xmlFile.getName() + " is valid");
            return true;
        } catch (SAXException e) {
            System.out.println("File : " + xmlFile.getName() + " is NOT valid reason:" + e);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
