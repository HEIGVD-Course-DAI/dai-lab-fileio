package ch.heig.dai.lab.fileio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import ch.heig.dai.lab.fileio.AnthonyChristen.EncodingSelector;
import ch.heig.dai.lab.fileio.AnthonyChristen.FileExplorer;
import ch.heig.dai.lab.fileio.AnthonyChristen.FileReaderWriter;
import ch.heig.dai.lab.fileio.AnthonyChristen.Transformer;

public class Main {
    private static final String newName = "Christen";

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
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        while (true) {
            try {
                File currentFile = fileExplorer.getNewFile();
                if (currentFile == null) {
                   System.out.println("No more files to process.");
                   break;
                }

                // Ignore the processed files
                if (currentFile.getName().endsWith(".processed")) {
                    continue;
                }

                Charset encoding = encodingSelector.getEncoding(currentFile);
                if (encoding == null) {
                    throw new RuntimeException("Unknown encoding for file" + currentFile.getName());
                }

                String content = fileReaderWriter.readFile(currentFile, encoding);
                if (content == null) {
                    throw new RuntimeException("Could not read file " + currentFile.getName());
                }

                // Transform file content
                content = transformer.replaceChuck(content);
                content = transformer.capitalizeWords(content);
                content = transformer.wrapAndNumberLines(content);

                String processedFileName = currentFile.getName() + ".processed";
                boolean success = fileReaderWriter.writeFile(new File(folder,
                                processedFileName), content, StandardCharsets.UTF_8);

                if (!success) {
                    throw new RuntimeException("Failed to write file " + processedFileName);
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
