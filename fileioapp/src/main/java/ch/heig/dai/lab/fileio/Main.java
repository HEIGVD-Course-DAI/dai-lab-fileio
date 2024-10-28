package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;

import ch.heig.dai.lab.fileio.ismaelgiovannetti.EncodingSelector;
import ch.heig.dai.lab.fileio.ismaelgiovannetti.FileExplorer;
import ch.heig.dai.lab.fileio.ismaelgiovannetti.FileReaderWriter;
import ch.heig.dai.lab.fileio.ismaelgiovannetti.Transformer;

public class Main {
    private static final String newName = "Ismael Giovannetti";

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

        // Create the necessary objects
        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                // Get a new file from the FileExplorer
                File file = fileExplorer.getNewFile();
                if (file == null) {
                    break;
                }

                // Determine the encoding of the file
                Charset encoding = encodingSelector.getEncoding(file);
                if (encoding == null) {
                    System.out.println("Could not determine the encoding of the file " + file.getName());
                    continue;
                }

                // Read the file
                String content = fileReaderWriter.readFile(file, encoding);
                if (content == null) {
                    System.out.println("Could not read the file " + file.getName());
                    continue;
                }

                // Transform the content
                String transformedContent = transformer.wrapAndNumberLines(transformer.capitalizeWords(transformer.replaceChuck(content)));

                // Write the result
                String resultFileName = file.getName() + ".processed";
                File resultFile = new File(file.getParent(), resultFileName);
                if (!fileReaderWriter.writeFile(resultFile, transformedContent, Charset.defaultCharset())) {
                    System.out.println("Could not write the result to the file " + resultFileName);
                }

                System.out.println("File " + file.getName() + " processed successfully.");
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
