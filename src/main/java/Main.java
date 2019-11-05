import core.PathException;
import core.Utilities;
import core.xml.XmlEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


public class Main {
    private static final String LOCATION = new File(Main.class.getProtectionDomain().getCodeSource().
            getLocation().getPath()).getParent();
    private static Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {
        if (args.length == 4) {
            try {
                String[] pathFiles = Utilities.getPaths(args, LOCATION);
                System.out.println("XML : " + pathFiles[0]);
                System.out.println("XSD : " + pathFiles[1]);
                System.out.println("XLS : " + pathFiles[2]);
                System.out.println("Out : " + pathFiles[3]);
                System.out.println("Logs : " + LOCATION + "\\logs");
                if (XmlEngine.validateFIle(pathFiles[0], pathFiles[1])) {
                    if (XmlEngine.transformFile(pathFiles[0], pathFiles[2], pathFiles[3])) {
                        System.out.println("Transform file success.");
                        //TODO validate out file by scheme
                    }
                }
            } catch (PathException e) {
                System.out.println("Error! Check logs");
                logger.error(e.getMessage());
            }
        } else {
            System.out.println("Please, Run the application with 4 options:\n" +
                    "the path to the xml, xsd, xsl file and the path to the output folder");
        }
    }
}
