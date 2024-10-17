package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.JeffMv.*;

import javax.xml.crypto.dsig.Transform;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Jeffrey";

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
        if (args.length != 2 || !new File(args[0]).isDirectory()) {
            System.out.println("You need to provide two command line arguments: an existing folder and the number of words per line.");
            System.exit(1);
        }
        String folder = args[0];
        int wordsPerLine = Integer.parseInt(args[1]);
        System.out.println("Application started, reading folder " + folder + "...");
        // TODO: implement the main method here

        FileExplorer explorer = new FileExplorer(folder);



        while (true) {

            try {
                // TODO: loop over all files
                File file = explorer.getNewFile();
                if (file == null || !file.exists()) {
                    break;
                }
                EncodingSelector encSelector = new EncodingSelector();
                Charset encoding = encSelector.getEncoding(file);
                FileReaderWriter fileHandler = new FileReaderWriter();
                Transformer transformer = new Transformer(newName, wordsPerLine);

                String content = fileHandler.readFile(file, encoding);
                content = transformer.capitalizeWords(content);
                content = transformer.replaceChuck(content);
                content = transformer.wrapAndNumberLines(content);

                String destPath = destinationFilepath(file);
                fileHandler.writeFile(new File(destPath), content, encoding);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }


    private static String destinationFilepath(File file) {
        return makeDestinationDirectory(file) + "/" + file.getName() + ".processed";
    }

    private static String makeDestinationDirectory(File file) {
        String path = getFilePath(file) + "/processed";
        File dir = new File(path);
        dir.mkdirs();
        return path;
    }

    private static String getFilePath(File file) {
        return file.getParent();
    }
}
