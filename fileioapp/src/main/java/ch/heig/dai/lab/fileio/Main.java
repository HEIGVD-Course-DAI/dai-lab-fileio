package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


import ch.heig.dai.lab.fileio.LovIMarK.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Mark Lovink";

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
        // TODO: implement the main method here
        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName,wordsPerLine);

        while (true) {
            try {
                // TODO: loop over all files
                File inputFile = fileExplorer.getNewFile();
                if (inputFile == null) {
                    System.out.println("No more files to process.");
                    break;
                }

                // Determine the file's encoding using EncodingSelector
                Charset encoding = encodingSelector.getEncoding(inputFile);
                if (encoding == null) {
                    System.out.println("Unable to detect encoding for file: " + inputFile.getName());
                    continue;
                }

                // Read the file content
                String content = fileReaderWriter.readFile(inputFile, encoding);
                if (content == null) {
                    System.out.println("Failed to read file: " + inputFile.getName());
                    continue;
                }

                String transformedContent = transformer.capitalizeWords(content);
                transformedContent=transformer.replaceChuck(transformedContent);
                transformedContent=transformer.wrapAndNumberLines(transformedContent);

                // Write the transformed content to a new file
                File outputFile = new File(inputFile.getAbsolutePath() + ".processed");
                boolean writeSuccess = fileReaderWriter.writeFile(outputFile, transformedContent, StandardCharsets.UTF_8);
                if (writeSuccess) {
                    System.out.println("Processed file written: " + outputFile.getName());
                } else {
                    System.out.println("Failed to write processed file: " + outputFile.getName());
                }



            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
