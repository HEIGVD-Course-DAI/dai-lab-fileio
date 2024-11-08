package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import ch.heig.dai.lab.fileio.elchami21.*;

public class Main {
    private static final String elchami21 = "Jean-Claude Van Damme";

    public static void main(String[] args) {
        // Read command line arguments
        if (args.length != 2 || !new File(args[0]).isDirectory()) {
            System.out.println("You need to provide two command line arguments: an existing folder and the number of words per line.");
            System.exit(1);
        }
        String folder = args[0];
        int wordsPerLine = Integer.parseInt(args[1]);
        System.out.println("Application started, reading folder " + folder + "...");

        // Create necessary objects
        FileExplorer explorer = new FileExplorer(folder);
        EncodingSelector selector = new EncodingSelector();
        FileReaderWriter fileIO = new FileReaderWriter();
        Transformer transformer = new Transformer(elchami21, wordsPerLine);

        while (true) {
            try {
                // Get next file to process
                File inputFile = explorer.getNewFile();
                if (inputFile == null) {
                    // No more files to process, wait a bit before checking again
                    Thread.sleep(1000);
                    continue;
                }

                // Get the encoding of the input file
                Charset encoding = selector.getEncoding(inputFile);
                if (encoding == null) {
                    System.out.println("Unknown encoding for file: " + inputFile.getName());
                    continue;
                }

                // Read the file content
                String content = fileIO.readFile(inputFile, encoding);
                if (content == null) {
                    System.out.println("Failed to read file: " + inputFile.getName());
                    continue;
                }

                // Transform the content
                String transformedContent = transformer.replaceChuck(content);
                transformedContent = transformer.capitalizeWords(transformedContent);
                transformedContent = transformer.wrapAndNumberLines(transformedContent);

                // Create output file with .processed suffix
                File outputFile = new File(inputFile.getPath() + ".processed");

                // Write the transformed content with UTF-8 encoding
                boolean success = fileIO.writeFile(outputFile, transformedContent, StandardCharsets.UTF_8);
                if (success) {
                    System.out.println("Successfully processed: " + inputFile.getName());
                } else {
                    System.out.println("Failed to write file: " + outputFile.getName());
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}