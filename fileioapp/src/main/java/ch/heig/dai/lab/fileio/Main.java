package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.*;

import ch.heig.dai.lab.fileio.EscapedGibbon.FileExplorer;
// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.ryadbouzourene.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Ryad Bouzourène";

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
        FileExplorer fillExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter readerWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                // TODO: loop over all files
                File input = fillExplorer.getNewFile();
                if(input==null)break ;
                if (input.getName().endsWith(".processed")) {
                    continue; // Passe au fichier suivant
                }
                System.out.println("Processing file: " + input.getName());
                Charset encoding = encodingSelector.getEncoding(input);
                if(encoding==null){
                    System.err.println("Unknown encoding extension: " + input.getName());
                    continue;
                }
                
                String source = readerWriter.readFile(input,encoding);
                source = transformer.replaceChuck(source);
                source = transformer.capitalizeWords(source);
                source = transformer.wrapAndNumberLines(source);
                
                File output = new File(input.getParentFile(), input.getName() + ".processed");
                
                if (readerWriter.writeFile(output, source, Charset.forName("UTF-8"))) {
                    System.out.println("Processed file: " + output.getName());
                } else {
                    System.err.println("Error writing file: " + output.getName());
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
