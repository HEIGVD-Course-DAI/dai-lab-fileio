package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import ch.heig.dai.lab.fileio.JonatanPerret.EncodingSelector;
import ch.heig.dai.lab.fileio.JonatanPerret.FileExplorer;
import ch.heig.dai.lab.fileio.JonatanPerret.FileReaderWriter;
import ch.heig.dai.lab.fileio.JonatanPerret.Transformer;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Jonatan Perret";

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

        // Create the necessary objects
        FileExplorer explorer = new FileExplorer(folder);
        EncodingSelector selector = new EncodingSelector();
        Transformer transformer = new Transformer(newName, wordsPerLine);
        FileReaderWriter readerWriter = new FileReaderWriter();
        int count = 0;
        while (true) {
            try {
                // TODO: loop over all files
                File file = explorer.getNewFile();
                if (file == null) {
                    break;
                }
                // skip the .processed files
                if (file.getName().endsWith(".processed")) {
                    continue;
                }
                // Determine the encoding
                Charset encoding = selector.getEncoding(file);
                // Read the file
                String content = readerWriter.readFile(file, encoding);
                // Transform the content
                String transformedContent = transformer.replaceChuck(content);
                transformedContent = transformer.capitalizeWords(transformedContent);
                transformedContent = transformer.wrapAndNumberLines(transformedContent);
                // Write the result
                File outputFile = new File(file.getParent(), file.getName() + ".processed");
                readerWriter.writeFile(outputFile, transformedContent, StandardCharsets.UTF_8);
                System.err.println("Processed file " + file.getName());
                count++;

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
        System.out.println("Application finished, processed " + count + " files.");
    }
}
