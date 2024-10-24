package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import ch.heig.dai.lab.fileio.adamrab187.*;

public class Main {
    private static final String newName = "Adam";

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

        // Create necessary objects
        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        // Infinite loop to process files
        while (true) {
            try {
                // Get a new file from the file explorer
                File file = fileExplorer.getNewFile();
                if (file == null) {
                    // No new file to process, continue the loop
                    continue;
                }

                // Determine the file's encoding
                Charset encoding = encodingSelector.getEncoding(file);
                if (encoding == null) {
                    System.out.println("Encoding not recognized for file: " + file.getName());
                    continue;
                }

                // Read the content of the file
                String content = fileReaderWriter.readFile(file, encoding);
                if (content == null) {
                    System.out.println("Failed to read file: " + file.getName());
                    continue;
                }

                // Transform the content
                String transformedContent = transformer.replaceChuck(content);
                transformedContent = transformer.capitalizeWords(transformedContent);
                transformedContent = transformer.wrapAndNumberLines(transformedContent);

                // Define the result file's name with a ".processed" suffix
                File resultFile = new File(file.getAbsolutePath() + ".processed");

                // Write the transformed content to a new file encoded in UTF-8
                if (!fileReaderWriter.writeFile(resultFile, transformedContent, StandardCharsets.UTF_8)) {
                    System.out.println("Failed to write file: " + resultFile.getName());
                } else {
                    System.out.println("Processed file: " + file.getName() + " -> " + resultFile.getName());
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
