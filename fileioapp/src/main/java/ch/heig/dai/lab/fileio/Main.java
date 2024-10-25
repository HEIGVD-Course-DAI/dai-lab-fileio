package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.VinchXVI.*;

import javax.xml.crypto.dsig.Transform;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Ehrensberger";

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

    //java -jar target/fileioapp-1.0.jar /mnt/c/Users/Vinch/DAI_JOKES_DELETE/jokes/ 6

    public static void main(String[] args) {
        // Read command line arguments
        if (args.length != 2 || !new File(args[0]).isDirectory()) {
            System.out.println("You need to provide two command line arguments: an existing folder and the number of words per line.");
            System.exit(1);
        }

        String folder = args[0];
        int wordsPerLine = Integer.parseInt(args[1]);
        System.out.println("Application started, reading folder " + folder + "...");
        // TODO: implement the main method here

        FileExplorer explorer = new FileExplorer(folder);
        Charset finalCharset = StandardCharsets.UTF_8;

        EncodingSelector encSelector = new EncodingSelector();
        FileReaderWriter fileHandler = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {

            try {
                // TODO: loop over all files
                File file = explorer.getNewFile();
                if (file == null || !file.exists()) {
                    break;
                }

                Charset encoding = encSelector.getEncoding(file);

                String newContent = fileHandler.readFile(file, encoding);
                newContent = transformer.capitalizeWords(newContent);
                newContent = transformer.replaceChuck(newContent);
                newContent = transformer.wrapAndNumberLines(newContent);

                String newName = file.getParent() + "/" + file.getName() + ".processed";
                fileHandler.writeFile(new File(newName), newContent, finalCharset);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
