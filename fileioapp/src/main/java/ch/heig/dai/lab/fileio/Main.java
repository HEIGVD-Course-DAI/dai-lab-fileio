package ch.heig.dai.lab.fileio;

import java.io.File;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.joelCarman.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Joel Carman";


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
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        EncodingSelector encodingSelector = new EncodingSelector();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                File file = fileExplorer.getNewFile();
                if (file == null) {
                    System.out.println("No more files to process, exiting...");
                    break;
                }
                Charset charset = encodingSelector.getEncoding(file);
                String content = fileReaderWriter.readFile(file, charset);
                String transformedContent = transformer.replaceChuck(content);
                transformedContent = transformer.capitalizeWords(transformedContent);
                transformedContent = transformer.wrapAndNumberLines(transformedContent);

                
                fileReaderWriter.writeFile( new File(destinationFilepath(file)) , transformedContent, charset);

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
    private static String destinationFilepath(File file) {
        return makeDestinationDirectory(file) + "/" + file.getName() + ".processed";
    }

    private static String makeDestinationDirectory(File file) {
        String path = getFilePath(file) + "/processed";
        File dir = new File(path);
        dir.mkdirs();
        return path;
    }

    private static String getFilePath(File file) {
        return file.getParent();
    }
}
