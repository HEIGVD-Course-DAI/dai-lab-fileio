package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import ch.heig.dai.lab.fileio.AdrienWard.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Jean-Claude Van Damme";

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

        FileExplorer explorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                File inputFile = explorer.getNewFile();
                if (inputFile == null) {
                    // Aucun fichier nouveau, on attend avant de vérifier à nouveau
                    Thread.sleep(1000);
                    continue;
                }

                // Déterminer l'encodage du fichier
                Charset encoding = encodingSelector.getEncoding(inputFile);

                // Lire le contenu du fichier
                String content = fileReaderWriter.readFile(inputFile, encoding);

                // Appliquer les transformations
                String transformedContent = transformer.replaceChuck(content);
                transformedContent = transformer.capitalizeWords(transformedContent);
                transformedContent = transformer.wrapAndNumberLines(transformedContent);

                // Préparer le nom du fichier de sortie avec suffixe ".processed"
                File outputFile = new File(inputFile.getAbsolutePath() + ".processed");

                // Écrire le contenu transformé dans le fichier de sortie avec UTF-8
                boolean success = fileReaderWriter.writeFile(outputFile, transformedContent, StandardCharsets.UTF_8);

                if (success) {
                    System.out.println("Processed file written to: " + outputFile.getName());
                } else {
                    System.out.println("Failed to write file: " + outputFile.getName());
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
