package core.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    public static final String OUT_FILE = "out.xml";
    private static Logger logger = LogManager.getRootLogger();

    /**
     * Method transform data file by style sheet
     *
     * @param pathXML       is path of data file
     * @param pathXSL       is patch of stylesheet file
     * @param pathDirectory is patch of output directory
     * @return true is success, else if files not exist or get exception
     */
    public static boolean transformFile(String pathXML, String pathXSL, String pathDirectory) {
        File xmlFile = new File(pathXML);
        File stylesheet = new File(pathXSL);
        if (!xmlFile.isFile() || !stylesheet.isFile()) {
            logger.error("Not found file by path");
            return false;
        }
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(stylesheet);
        try {
            Transformer transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(xmlFile);
            File directory = new File(pathDirectory);
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    return false;
                }
            }
            transformer.transform(xml, new StreamResult(new File(directory.getPath() + "/" + OUT_FILE)));
            logger.info("Transform file successfully. " + xmlFile.getName());
            return true;
        } catch (TransformerException e) {
            logger.info("Transform file unsuccessfully. " + xmlFile.getName());
            logger.error(e.getMessage());
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
            logger.info("Not found file by path");
            return false;
        }
        Source xmlSource = new StreamSource(xmlFile);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);
            validator.setErrorHandler(new LineNumberErrorHandler());
            logger.info("Check file successfully. " + xmlFile.getName());
            return true;
        } catch (SAXException | IOException e) {
            logger.info("Check file unsuccessfully. " + xmlFile.getName());
            logger.error(e.getMessage());
            return false;
        }
    }
}