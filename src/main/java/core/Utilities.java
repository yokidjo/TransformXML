package core;

import java.io.File;

public final class Utilities {

    private static final int COUNT_ARGS = 4;

    /**
     * Method return array paths
     *
     * @param args     array with paths
     * @param location folder location files, when path not absolute
     * @return array paths
     * @throws PathException when file not exist or bad path
     */
    public static String[] getPaths(String[] args, String location) throws PathException {
        if (args.length == COUNT_ARGS) {
            String[] paths = new String[args.length];
            for (int i = 0; i < args.length; i++) {
                File f = args[i].contains("\\") ? new File(args[i]) : new File(location + "\\" + args[i]);
                if (f.exists()) {
                    if (f.isFile()) {
                        if ((f.getAbsolutePath()).toLowerCase().contains(".xml")) {
                            paths[0] = f.getAbsolutePath();
                        } else if ((f.getAbsolutePath()).toLowerCase().contains(".xsd")) {
                            paths[1] = f.getAbsolutePath();
                        } else if ((f.getAbsolutePath()).toLowerCase().contains(".xsl")) {
                            paths[2] = f.getAbsolutePath();
                        } else {
                            throw new PathException("Not right file type");
                        }
                    } else {
                        paths[3] = f.getAbsolutePath();
                    }
                } else {
                    throw new PathException("One of the options does not apply to a file or folder");
                }
            }
            for (String s : paths) {
                if (s == null) {
                    throw new PathException("One  or more  option(s) do not get");
                }
            }
            return paths;
        } else {
            throw new PathException("Number of arguments should be 4");
        }
    }
}