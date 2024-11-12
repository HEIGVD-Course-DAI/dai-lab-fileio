package ch.heig.dai.lab.fileio;

import java.io.File;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.Gabi3469.*;

import java.nio.charset.Charset;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Gabriel Barros Fernandes";

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

        while (true) {
            try {
                // TODO: loop over all files
                File dir = new File(folder);
                File[] files = dir.listFiles();

                for (File file : files) {
                    if (file.isFile()) {
                        try {
                            EncodingSelector encodingSelector = new EncodingSelector();
                            FileReaderWriter fileReaderWriter = new FileReaderWriter();
                            Transformer transformer = new Transformer(newName, wordsPerLine);
                            Charset encoding = encodingSelector.getEncoding(file);
                            if (encoding == null) {
                                System.out.println("Unknown encoding for file: " + file.getName());
                                continue; // Skip file if encoding is unknown
                            }

                            String content = fileReaderWriter.readFile(file, encoding);
                            if (content == null) {
                                System.err.println("Error reading file: " + file.getName());
                                continue; // Skip file if reading fails
                            }

                            String transformedContent = transformer.replaceChuck(content);
                            transformedContent = transformer.capitalizeWords(transformedContent);
                            transformedContent = transformer.wrapAndNumberLines(transformedContent);

                            File outputFile = new File(file.getPath() + ".processed");
                            if (!fileReaderWriter.writeFile(outputFile, transformedContent, encoding)) {
                                System.err.println("Error writing file: " + outputFile.getName());
                            } else {
                                System.out.println("Processed file: " + outputFile.getName());
                            }
                        } catch (Exception e) {
                            System.err.println("Exception: " + e);
                        }
                    }else {
                        System.out.println("Skipping non-file: " + file.getName());
                    }
                }
                System.out.println("All files processed.");
                break;
                

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
