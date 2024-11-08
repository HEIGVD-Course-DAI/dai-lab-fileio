package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;

import ch.heig.dai.lab.fileio.Evan_Charles.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Evan Charles";

    /**
     * Main method to transform files in a folder.
     * Create the necessary objects (FileExplorer, EncodingSelector,
     * FileReaderWriter, Transformer).
     * In an infinite loop, get a new file from the FileExplorer, determine its
     * encoding with the EncodingSelector,
     * read the file with the FileReaderWriter, transform the content with the
     * Transformer, write the result with the
     * FileReaderWriter.
     *
     * Result files are written in the same folder as the input files, and encoded
     * with UTF8.
     *
     * File name of the result file:
     * an input file "myfile.utf16le" will be written as "myfile.utf16le.processed",
     * i.e., with a suffixe ".processed".
     */
    public static void main(String[] args) {
        // Read command line arguments
        if (args.length != 2 || !new File(args[0]).isDirectory()) {
            System.out.println(
                    "You need to provide two command line arguments: an existing folder and the number of words per line.");
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

        while (true) {
            try {
                // Get a new file from the FileExplorer
                File file = fileExplorer.getNewFile();
                if (file == null) {
                    System.out.println("No more files to process.");
                    break;
                }

                // Determine the encoding of the file
                Charset encoding = encodingSelector.getEncoding(file);
                if (encoding == null) {
                    System.out.println("File " + file.getName() + " has an unknown encoding.");
                    continue;
                }

                // Read the file with the FileReaderWriter
                String content = fileReaderWriter.readFile(file, encoding);
                if (content == null) {
                    System.out.println("File " + file.getName() + " could not be read.");
                    continue;
                }

                // Transform the content with the Transformer
                String transformedContent = transformer.replaceChuck(content);
                transformedContent = transformer.capitalizeWords(transformedContent);
                transformedContent = transformer.wrapAndNumberLines(transformedContent);
                if (transformedContent == null) {
                    System.out.println("File " + file.getName() + " could not be transformed.");
                    continue;
                }

                // Write the result with the FileReaderWriter
                String resultFileName = file.getName() + ".processed";
                fileReaderWriter.writeFile(new File(folder, resultFileName), transformedContent,
                        Charset.forName("UTF-8"));
                System.out.println("File processed: " + file.getName());
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
