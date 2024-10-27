package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.leirth.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Lei Rothenbühler";

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
        
        // Create objects
        FileExplorer explorer = new FileExplorer(folder);
        EncodingSelector selector = new EncodingSelector();
        FileReaderWriter readerWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                // Get a new file
                File file = explorer.getNewFile();
                if (file == null) {
                    System.out.println("No more files to process.");
                    break;
                }

                if (file.getName().endsWith(".processed")) {
                    System.out.println("File " + file.getName() + " already processed.");
                    continue;
                }

                // Read the file
                Charset encoding = selector.getEncoding(file);
                String content = readerWriter.readFile(file, encoding);

                // Transform the content
                String processedContent = transformer.replaceChuck(content);
                processedContent = transformer.capitalizeWords(processedContent);
                processedContent = transformer.wrapAndNumberLines(processedContent);

                // Write the result
                String resultFile = file.getName() + ".processed";
                readerWriter.writeFile(new File(folder, resultFile), processedContent, StandardCharsets.UTF_8);

                System.out.println("File " + file.getName() + " processed.");
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
