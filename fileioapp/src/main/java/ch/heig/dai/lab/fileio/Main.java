package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import ch.heig.dai.lab.fileio.wolffheig.*;

public class Main {

    private static final String newName = "Jérémy Wolff";

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
            System.out.println("Usage: java -jar <jar-file> <folder> <words_per_line>");
            System.exit(1);
        }
        String folder = args[0];
        int wordsPerLine = Integer.parseInt(args[1]);
        System.out.println("Application started, reading folder " + folder + "...");

        // Initialize necessary objects
        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        // Infinite loop to process files in the folder
        while (true) {
            try {
                // Get a new file from the folder, that hasn't been processed yet, using FileExplorer
                File inputFile = fileExplorer.getNewFile();
                if (inputFile == null) {
                    System.out.println("All files processed. Exiting.");
                    break; // Exit loop when all files are processed
                }

                // Exclude files that are already processed using indexOf
                int index = inputFile.getName().indexOf(".processed");
                if (index != -1 && index == inputFile.getName().length() - ".processed".length()) {
                    continue; // Skip this file
                }

                // Determine the file's encoding using EncodingSelector
                Charset encoding = encodingSelector.getEncoding(inputFile);
                if (encoding == null) {
                    System.out.println("Unknown encoding for file: " + inputFile.getName());
                    continue; // Skip file if encoding is unknown
                }

                // Read the content of the file using FileReaderWriter
                String content = fileReaderWriter.readFile(inputFile, encoding);
                if (content == null) {
                    System.err.println("Error reading file: " + inputFile.getName());
                    continue; // Skip file if reading fails
                }

                // Apply transformations using Transformer: replace Chuck Norris, capitalize words, and wrap/number lines
                String transformedContent = transformer.replaceChuck(content);
                transformedContent = transformer.capitalizeWords(transformedContent);
                transformedContent = transformer.wrapAndNumberLines(transformedContent);

                // Create the output file name with a ".processed" suffix
                File outputFile = new File(inputFile.getPath() + ".processed");
                boolean success = fileReaderWriter.writeFile(outputFile, transformedContent, StandardCharsets.UTF_8);

                // Check if the file was successfully written
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
