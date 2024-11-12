package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.StandardCharsets;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.hliosone.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Stan Stelcher";

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
        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                // TODO: loop over all files
                File file = fileExplorer.getNewFile();
                if (file == null) {
                    break;
                }

                // Get the encoding of the file
                var encoding = encodingSelector.getEncoding(file);
                if (encoding == null) {
                    continue;
                }

                // Read the content of the file
                String content = fileReaderWriter.readFile(file, encoding);
                if (content == null) {continue;}

                // Transform the content
                String transformedContent = transformer.capitalizeWords(content);
                transformedContent = transformer.wrapAndNumberLines(transformedContent);

                // Write the transformed content to a new file
                File newFile = new File(file.getParent(), file.getName() + ".processed");
                if (fileReaderWriter.writeFile(newFile, transformedContent, StandardCharsets.UTF_8)) {
                    System.out.println("File " + file.getName() + " processed successfully");
                } else {
                    System.out.println("Error processing file " + file.getName());
                }
                
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
