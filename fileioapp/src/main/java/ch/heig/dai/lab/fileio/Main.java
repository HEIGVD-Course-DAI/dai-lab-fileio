package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.*;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.thomasVn.*;;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Thomas Nguyen";

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


        // Initialize objects with implemented classes
        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        // Infinite loop to process files in the folder
        while (true) {
            try {
                // TODO: loop over all files


                // Get a new file from the folder, that hasn't been processed yet, using FileExplorer. Exit when all the files have been executed
                File input = fileExplorer.getNewFile();
                if (input == null) {

                    System.out.println("All files has been processed.");
                    break;
                }

                // Exclude proccessed files
                int index = input.getName().indexOf(".processed");
                if (index != -1 && index == input.getName().length() - ".processed".length()) {

                    continue;
                }

                // Determine the encoding. If unknown encoding, skip the file
                Charset encoding = encodingSelector.getEncoding(input);
                if (encoding == null) {

                    System.out.println("Unknown encoding for file: " + input.getName());
                    continue;
                }

                // Read the content. If a reading error occurs, skip the file
                String content = fileReaderWriter.readFile(input, encoding);
                if (content == null) {

                    System.err.println("Error reading file: " + input.getName());
                    continue;
                }

                // Apply transformations from Transformer Classes
                String transformed = transformer.replaceChuck(content);
                transformed = transformer.capitalizeWords(transformed);
                transformed = transformer.wrapAndNumberLines(transformed);

                // Create the output file with .proccessed 
                File outputFile = new File(input.getPath() + ".processed");
                boolean success = fileReaderWriter.writeFile(outputFile, transformed, StandardCharsets.UTF_8);

                // Check if the file was written successfully
                if (success) {
                    System.out.println("Processed file: " + outputFile.getName());
                } else {
                    System.err.println("Error writing file: " + outputFile.getName());
                }
                
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
