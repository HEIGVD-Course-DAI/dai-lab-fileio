package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.Nathanael_Delacrausaz.*;

public class Main {
    // *** TODO: Change this to your own name ***

    private static final String newName = "Nathanael Delacrausaz";


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

        FileExplorer fileExplorer = new FileExplorer(folder);  
        EncodingSelector encodingSelector = new EncodingSelector();  
        FileReaderWriter fileReaderWriter = new FileReaderWriter();  
        Transformer transformer = new Transformer("NewName", wordsPerLine);

        while (true) {
            try {

                File inputFile = fileExplorer.getNewFile();
                if (inputFile == null) {
                    System.out.println("No more files to process");
                    break;
                }

                Charset fileEncoding = encodingSelector.getEncoding(inputFile);
                if (fileEncoding == null) {
                    System.out.println("Unrecognized encoding for file: " + inputFile.getName());
                    continue;  
                }

                String fileContent = fileReaderWriter.readFile(inputFile, fileEncoding);
                if (fileContent == null) {
                    System.out.println("Error reading file: " + inputFile.getName());
                    continue;
                }

                String transformedContent = transformer.replaceChuck(fileContent);  
                transformedContent = transformer.capitalizeWords(transformedContent);  
                transformedContent = transformer.wrapAndNumberLines(transformedContent);  

                File outputFile = new File(inputFile.getAbsolutePath() + ".processed");
                boolean success = fileReaderWriter.writeFile(outputFile, transformedContent, Charset.forName("UTF-8"));
                if (success) {
                    System.out.println("Successfully processed: " + inputFile.getName());
                } else {
                    System.out.println("Failed to write output for: " + inputFile.getName());
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}


