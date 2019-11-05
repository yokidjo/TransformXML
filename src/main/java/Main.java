import core.PathException;
import core.TransformOption;
import core.xml.XmlEngine;
import core.xml.XmlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Paths;

import static core.xml.XmlEngine.OUT_FILE;


public class Main {
    private static final String LOCATION = new File(Main.class.getProtectionDomain().getCodeSource().
            getLocation().getPath()).getParent();
    private static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {
        logger.info("Start task.");
        if (args.length == 4) {
            try {
                TransformOption trOption = TransformOption.parseArgs(args, Paths.get(LOCATION));
                System.out.println("XML : " + trOption.getXmlPath());
                System.out.println("XSD : " + trOption.getXmlPath());
                System.out.println("XLS : " + trOption.getXmlPath());
                System.out.println("Out : " + trOption.getXmlPath());
                System.out.println("Logs : " + Paths.get(LOCATION, "logs"));
                if (XmlEngine.validateFIle(trOption.getXmlPath().toAbsolutePath().toString()
                        , trOption.getXsdPath().toAbsolutePath().toString())) {
                    if (XmlEngine.transformFile(
                            trOption.getXmlPath().toAbsolutePath().toString(),
                            trOption.getXslPath().toAbsolutePath().toString(),
                            trOption.getOutPath().toAbsolutePath().toString()
                    )) {
                        if (XmlEngine.validateFIle(Paths.get(trOption.getOutPath().toAbsolutePath().toString(), OUT_FILE).toAbsolutePath().toString(),
                                trOption.getXsdPath().toAbsolutePath().toString())) {
                            System.out.println("Task is done.");
                        }
                    }
                }
            } catch (PathException | XmlException e) {
                System.out.println("Error! Check logs");
                logger.error(e.getMessage());
            }
        } else {
            System.out.println("Please, Run the application with 4 options:\n" +
                    "the path to the xml, xsd, xsl file and the path to the output folder");
        }
        logger.info("End task.");
    }
}
