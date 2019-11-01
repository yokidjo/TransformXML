package core;

public class Utilities {

    //((-(xml|xsd|xslt))\s{1,}(([A-z]+:){0,1}((\/|\\)[A-z0-9-_+]+(\/|\\))){0,1})([A-z0-9]+\.(xml|xsd|xslt))
    public static final String COMMAND_REGEXP = "((-(xml|xsd|xslt))\\s{1,}(([A-z]+:){0,1}((\\/|\\\\)[A-z0-9-_+]+(\\/|\\\\))){0,1})([A-z0-9]+\\.(xml|xsd|xslt))";

    public static String[] getPathFiles(String[] args) {
        String[] pathFiles = new String[4];
        for (int i = 0; i < args.length; i++) {
            String fileExtension = args[i].substring(args[i].length() - 3).toUpperCase();
            if (fileExtension.equals("XML") || fileExtension.equals("XSD") || fileExtension.equals("XSL")) {
                System.out.println(fileExtension.toUpperCase() + " : " + args[i]);
            } else {
                System.out.println("Folder output : " + args[i]);
            }
            pathFiles[i] = args[i];
        }
        return pathFiles;
    }
}

