package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.TheoPilet.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Theo Pilet";

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

        // implement the main method here
        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                File file = fileExplorer.getNewFile();

                if (file == null) {
                    System.out.println("No more files to process.");
                    break;
                }

                Charset encoding = encodingSelector.getEncoding(file);
                String content = fileReaderWriter.readFile(file, encoding);

                if (content != null) {
                    content = transformer.replaceChuck(content);
                    content = transformer.capitalizeWords(content);
                    content = transformer.wrapAndNumberLines(content);

                    Path outputPath = Paths.get(file.getAbsolutePath() + ".processed");
                    fileReaderWriter.writeFile(outputPath.toFile(), content, Charset.forName("UTF-8"));
                    System.out.println(outputPath + " has been processed and saved: " );
                } else {
                    System.out.println("Failed to read the file: " + file);
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
