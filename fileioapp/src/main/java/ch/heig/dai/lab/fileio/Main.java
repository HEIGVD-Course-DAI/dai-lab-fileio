package ch.heig.dai.lab.fileio;

import java.io.File;

import ch.heig.dai.lab.fileio._9r55hs.FileReaderWriter;
import ch.heig.dai.lab.fileio.mang0r.EncodingSelector;
import ch.heig.dai.lab.fileio.mang0r.FileExplorer;
import ch.heig.dai.lab.fileio.mang0r.Transformer;

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
        // TODO: implement the main method here

        EncodingSelector selector = new EncodingSelector();
        FileReaderWriter frw = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);
        
        FileExplorer explorer;
        try {
            explorer = new FileExplorer(folder);
        } catch (Exception e) {
            System.err.println("Folder not found");
            return; // Exit program
        }

        File f;
        while ((f = explorer.getNewFile()) != null) {
            try {
                // TODO: loop over all files
                // skip already processed files
                if ((f.getName().substring(f.getName().lastIndexOf('.'))).equals("processed")) break;
                
                File of = new File(f.getAbsolutePath() + ".processed");
                frw.writeFile(
                    of,
                    transformer.replaceChuck(frw.readFile(f, selector.getEncoding(f))), 
                        selector.getEncoding(f));

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
