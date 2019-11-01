import core.Utilities;
import core.xml.XmlEngine;
import org.apache.logging.log4j.core.util.JsonUtils;

import java.io.File;
import java.net.URL;


public class Main {

    //static final Logger userLogger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length > 0) {
            String[] pathFiles = Utilities.getPathFiles(args);
            try {
                String jarFolder = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getPath();
                //File jar = new File(Thread.currentThread().getContextClassLoader().getResource(".").toURI());
                System.out.println(jarFolder);


                if (XmlEngine.validateFIle(pathFiles[0], pathFiles[1])) {
                    boolean b = XmlEngine.transformFile(pathFiles[0], pathFiles[2], pathFiles[3]);
                    System.out.println(b);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
