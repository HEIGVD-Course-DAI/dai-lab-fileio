package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.Buffer;
import java.nio.charset.Charset;

import ch.heig.dai.lab.fileio.trigerber.*;

public class Main {
    private static final String newName = "Tristan";

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
        int wordsPerLine = 0;
        
        try {
            wordsPerLine = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for words per line.");
            System.exit(1);
        }

        System.out.println("Application started, reading folder " + folder + "...");

        // Create objects
        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);
        File file;
        
        // Process files
        while ((file = fileExplorer.getNewFile()) != null) {
            try {
                // Get encoding
                Charset encoding = encodingSelector.getEncoding(file);
                
                if (encoding == null) {
                    System.out.println("Unknown encoding for file " + file.getName());
                    continue;
                }
                
                // Read file
                String content = fileReaderWriter.readFile(file, encoding);
                
                if (content == null) {
                    System.out.println("Error reading file " + file.getName());
                    continue;
                }

                // Create new file
                File f = new File(file.getParent(), file.getName() + ".processed");
                f.createNewFile();
                Charset c = Charset.forName("UTF-8");
                String newContent = transformer.replaceChuck(content);

                
                // Process content and write file
                boolean success = fileReaderWriter.writeFile(
                    f, newContent, c
                );
                
                if (!success) {
                    System.out.println("Error writing file " + file.getName());
                }
            } catch (Exception e) {
                System.out.println("Exception processing file " + file.getName() + ": " + e.getMessage());
            }
        }

        System.out.println("All files processed.");
    }
}
