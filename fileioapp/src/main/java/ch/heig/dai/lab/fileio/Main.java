package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import ch.heig.dai.lab.fileio.nadi3.*;

public class Main {
    private static final String newName = "Nadia Cattin";

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
        EncodingSelector selector = new EncodingSelector();
        FileReaderWriter readerWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);
        while (true) {
            try {
                File file = explorer.getNewFile();
                if (null == file) {
                    break;
                }

                Charset encoding = selector.getEncoding(file);
                String content = readerWriter.readFile(file, encoding);
                String transformed = transformer.wrapAndNumberLines(
                        transformer.capitalizeWords(
                                transformer.replaceChuck(content)
                        )
                );
                String newFileName = folder + File.separator + file.getName() + ".processed";
                if (readerWriter.writeFile(new File(newFileName), transformed, StandardCharsets.UTF_8)) {
                    System.out.println("Success for file: " + newFileName);
                } else {
                    System.out.println("Error for file: " + newFileName);
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
