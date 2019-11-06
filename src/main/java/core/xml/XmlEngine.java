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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmlEngine {
    public static final String OUT_FILE = "out.xml";
    private static Logger logger = LogManager.getRootLogger();

    /**
     * Method validate data file  by XML schema
     *
     * @param pathXML is path of data file
     * @param pathXSD is path of XML schema
     * @throws XmlException an error occurred while parsing XML ,an error occurred while while work with file
     */
    public static void validateFile(Path pathXML, Path pathXSD) throws XmlException {
        File xmlFile = new File(pathXML.toAbsolutePath().toString());
        File xsdFile = new File(pathXSD.toAbsolutePath().toString());
        existFiles(xmlFile, xsdFile);
        try {
            Source xmlSource = new StreamSource(xmlFile);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);
            validator.setErrorHandler(new LineNumberErrorHandler());
            logger.info("Check file successfully. " + xmlFile.getName());
        } catch (SAXException e) {
            throw new XmlException("Check file unsuccessfully. An error occurred while parsing XML. " + xmlFile.getName() + " " + e.getMessage());
        } catch (IOException e) {
            throw new XmlException("Check file unsuccessfully. An error occurred while while work with file . " + xmlFile.getName() + " " + e.getMessage());
        }
    }

    /**
     * Method transform data file by style sheet
     *
     * @param pathXML       is path of data file
     * @param pathXSL       is patch of stylesheet file
     * @param pathDirectory is patch of output directory
     * @throws XmlException not found file, transform unsuccessfully
     */
    public static void transformFile(Path pathXML, Path pathXSL, Path pathDirectory) throws XmlException {
        File xmlFile = new File(pathXML.toAbsolutePath().toString());
        File stylesheet = new File(pathXSL.toAbsolutePath().toString());
        existFiles(xmlFile, stylesheet);
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(stylesheet));
            Source xmlSource = new StreamSource(xmlFile);
            createDir(pathDirectory);
            StreamResult streamResult = new StreamResult(
                    new File(
                            Paths.get(pathDirectory.toAbsolutePath().toString(), OUT_FILE).toString()
                    )
            );
            transformer.transform(xmlSource, streamResult);
            logger.info("Transform file successfully. " + xmlFile.getName());

        } catch (TransformerException e) {
            throw new XmlException("Transform file unsuccessfully. XSL transform exception. " + xmlFile.getName() +
                    "\n" + e.getMessage());
        } catch (IOException e) {
            throw new XmlException("Transform file unsuccessfully. Can not create directory. " + e.getMessage());
        }

    }

    /**
     * Method create directory by path
     *
     * @param pathDirectory is directory to  create
     * @throws IOException occurrence of a problem during directory creation
     */
    private static void createDir(Path pathDirectory) throws IOException {
        if (!Files.exists(pathDirectory)) {
            Files.createDirectories(pathDirectory);
        }
    }

    /**
     * Method check exist files
     *
     * @param xmlFile    xml file
     * @param stylesheet xsl file
     * @throws XmlException path not file
     */
    private static void existFiles(File xmlFile, File stylesheet) throws XmlException {
        if (!xmlFile.isFile() | !stylesheet.isFile()) {
            throw new XmlException("Not found file by path");
        }
    }
}