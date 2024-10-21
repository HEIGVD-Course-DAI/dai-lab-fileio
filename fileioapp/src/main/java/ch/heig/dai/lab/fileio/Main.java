package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.StandardCharsets;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.jehrensb.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Jerome-Riedo";

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
        var myFileExplorer = new ch.heig.dai.lab.fileio.Jerome_Riedo.FileExplorer(folder);
        var myEncoderSelector = new ch.heig.dai.lab.fileio.Jerome_Riedo.EncodingSelector();
        var myFileReaderWriter = new ch.heig.dai.lab.fileio.Jerome_Riedo.FileReaderWriter();
        var myTransformer = new ch.heig.dai.lab.fileio.Jerome_Riedo.Transformer(newName, wordsPerLine);

        while (true) {
            try {
                // TODO: loop over all files
                var inputFile = myFileExplorer.getNewFile();
                if (inputFile == null) {
                    break; // plus de fichier à lire : sortie de la boucle
                }
                var outputFile = new File(inputFile.getAbsolutePath() + ".processed");
                var fileCharset = myEncoderSelector.getEncoding(inputFile);
                if (fileCharset == null) {
                    continue;   // permet de ne pas traiter les fichiers .processed
                }
                var textFile = myFileReaderWriter.readFile(inputFile, fileCharset);
                var modifiedTextFile = myTransformer.wrapAndNumberLines(textFile);
                myFileReaderWriter.writeFile(outputFile, modifiedTextFile, StandardCharsets.UTF_8);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
                break;
            }
        }
        System.out.println("Application finished.");
    }
}
