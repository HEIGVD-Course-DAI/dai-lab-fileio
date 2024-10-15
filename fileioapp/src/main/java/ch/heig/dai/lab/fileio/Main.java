package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;

import ch.heig.dai.lab.fileio.EscapedGibbon.*;

public class Main {
    private static final String newName = "Maxim Golubev";
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
        String filenameExt = ".processed";
        int wordsPerLine = Integer.parseInt(args[1]);
        System.out.println("Application started, reading folder " + folder + "...");
        FileExplorer folderInCheck = new FileExplorer(folder);
        Transformer transformer = new Transformer(newName, wordsPerLine);
        while (true) {
          EncodingSelector encoding = new EncodingSelector();
          FileReaderWriter stream = new FileReaderWriter();
          File fileInCheck = folderInCheck.getNewFile();
          //If no new files, then the algorithm stops.
          if(fileInCheck == null){
            System.out.println("Application ended.");
            break;
          } 
         //If encoding is incorrect, then we skip the file
         //(In our case it is the file with ".processed" extension).
          Charset fileEncoding = encoding.getEncoding(fileInCheck);
          if(fileEncoding == null){
           continue;
          }
            try { 
             String sourceContent = stream.readFile(fileInCheck, fileEncoding); 
             sourceContent = transformer.replaceChuck(sourceContent);
             sourceContent = transformer.capitalizeWords(sourceContent);
             sourceContent =  transformer.wrapAndNumberLines(sourceContent);
             File targetFile = new File(fileInCheck.getAbsolutePath()+filenameExt);
             stream.writeFile(targetFile, sourceContent, Charset.forName("UTF8"));
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
