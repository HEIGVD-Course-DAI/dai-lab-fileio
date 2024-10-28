package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;

import ch.heig.dai.lab.fileio.axelrochat.*;

// DROPME
public class Main {
    private static final String newName = "Axel Rochat";

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
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                File inputFile = fileExplorer.getNewFile();
                if (inputFile == null){
                    System.out.println("No other file available.");
                    break;
                }

                Charset fileEncoding = encodingSelector.getEncoding(inputFile);
                if (fileEncoding == null){
                    System.out.println("Unknown encoding, skipping this file : " + inputFile);
                    continue;
                }
                String fileContent = fileReaderWriter.readFile(inputFile, fileEncoding);

                fileContent = transformer.replaceChuck(fileContent);
                fileContent = transformer.capitalizeWords(fileContent);
                fileContent = transformer.wrapAndNumberLines(fileContent);

                File outputFile = new File(inputFile.getAbsolutePath() + ".processed");
                fileReaderWriter.writeFile(outputFile, fileContent, Charset.forName("UTF-8"));

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
