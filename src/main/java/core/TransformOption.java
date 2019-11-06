package core;


import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TransformOption {
    private static final int COUNT_ARGS = 4;
    private static final int INDEX_ORIGINAL_XML = 0;
    private static final int INDEX_ORIGINAL_XSD = 1;
    private static final int INDEX_ORIGINAL_XSL = 2;
    private static final int INDEX_ORIGINAL_OUT = 3;

    private Path XmlPath;
    private Path XsdPath;
    private Path XslPath;
    private Path OutPath;


    private TransformOption(Path xmlPath, Path xsdPath, Path xslPath, Path outPath) {
        XmlPath = xmlPath;
        XsdPath = xsdPath;
        XslPath = xslPath;
        OutPath = outPath;
    }

    public Path getXmlPath() {
        return XmlPath;
    }

    public Path getXsdPath() {
        return XsdPath;
    }

    public Path getXslPath() {
        return XslPath;
    }

    public Path getOutPath() {
        return OutPath;
    }

    /**
     * Method create object TransformOption
     *
     * @param args     array string options(paths)
     * @param location folder location files, when path not absolute
     * @return TransformOption when file not exist or bad path
     * @throws PathException when options is not valid
     */
    public static TransformOption parseArgs(String[] args, Path location) throws PathException {
        if (args.length == COUNT_ARGS) {
            Path[] paths = new Path[args.length];
            for (String arg : args) {
                Path tmpPath = Paths.get(arg).isAbsolute() ? Paths.get(arg) :
                        Paths.get(location.toAbsolutePath().toString(), arg);
                if (Files.exists(tmpPath, LinkOption.NOFOLLOW_LINKS) && !Files.isDirectory(tmpPath, LinkOption.NOFOLLOW_LINKS)) {
                    checkTypeFile(tmpPath, paths);
                } else {
                    paths[INDEX_ORIGINAL_OUT] = tmpPath.toAbsolutePath();
                }
            }
            checkPaths(paths);
            return new TransformOption(
                    paths[INDEX_ORIGINAL_XML],
                    paths[INDEX_ORIGINAL_XSD],
                    paths[INDEX_ORIGINAL_XSL],
                    paths[INDEX_ORIGINAL_OUT]
            );
        } else {
            throw new PathException("Number of arguments should be 4");
        }
    }

    /**
     * Method check type file and add to array
     *
     * @param path  checked path
     * @param paths array paths where add path
     * @return an order array
     * @throws PathException when type not found
     */
    private static Path[] checkTypeFile(Path path, Path[] paths) throws PathException {
        if (hasExtension(path, ".xml")) {
            paths[INDEX_ORIGINAL_XML] = path;
        } else if (hasExtension(path, ".xsd")) {
            paths[INDEX_ORIGINAL_XSD] = path;
        } else if (hasExtension(path, ".xsl")) {
            paths[INDEX_ORIGINAL_XSL] = path;
        } else {
            throw new PathException("Not right file type");
        }
        return paths;
    }

    /**
     * Method check file type
     *
     * @param path      checked path
     * @param extension is type of file check
     * @return true if type file equals extension
     */
    private static boolean hasExtension(Path path, String extension) {
        return path.toAbsolutePath().toString().endsWith(extension);
    }

    /**
     * Method check path not null
     *
     * @param paths array paths
     * @throws PathException when path is null
     */
    private static void checkPaths(Path[] paths) throws PathException {
        for (Path path : paths) {
            if (path == null) {
                throw new PathException("One  or more  option(s) do not get");
            }
        }
    }
}
