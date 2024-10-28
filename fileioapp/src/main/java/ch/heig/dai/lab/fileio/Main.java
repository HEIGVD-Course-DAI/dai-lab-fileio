package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.IlianTopalov.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "IlianTopalov";

    /**
     * Main method to transform files in a folder.
     * Create the necessary objects (FileExplorer, EncodingSelector, FileReaderWriter, Transformer).
     * In an infinite loop, get a new file from the FileExplorer, determine its encoding with the EncodingSelector,
     * read the file with the FileReaderWriter, transform the content with the Transformer, write the result with the
     * FileReaderWriter.
     * 
     * Result files are written in the same folder as the input files, and encoded with UTF8.
     *
     * File name of the result file:
     * an input file "myfile.utf16le" will be written as "myfile.utf16le.processed",
     * i.e., with a suffixe ".processed".
     */
    public static void main(String[] args) {
        // Read command line arguments
        System.out.println(Arrays.toString(args));
        if (args.length != 2 || !new File(args[0]).isDirectory()) {
            System.out.println("You need to provide two command line arguments: an existing folder and the number of words per line.");
            System.exit(1);
        }
        String folder = args[0];
        int wordsPerLine = Integer.parseInt(args[1]);
        System.out.println("Application started, reading folder " + folder + "...");

        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        String suffix = ".processed";

        // loop over all files
        while (true) {
            try {
                File file = fileExplorer.getNewFile();
                if (file == null) {
                    break;
                }

                if (!file.getName().endsWith(suffix)) {
                    continue;
                }

                // Get file encoding
                Charset encoding = encodingSelector.getEncoding(file);

                // Get file content
                System.out.println("Reading file " + file.getName());  // LOG
                String content = fileReaderWriter.readFile(file, encoding);

                // Transform content
                transformer.replaceChuck(content);
                transformer.capitalizeWords(content);
                transformer.wrapAndNumberLines(content);

                // Output content
                String outputFileName = file.getName() + suffix;
                File outputFile = new File(outputFileName);
                fileReaderWriter.writeFile(outputFile, content, encoding);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
