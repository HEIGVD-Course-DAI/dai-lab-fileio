package ch.heig.dai.lab.fileio.wolffheig;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EncodingSelector {

    /**
     * Get the encoding of a file based on its extension.
     * The following extensions are recognized:
     *   - .utf8:    UTF-8
     *   - .txt:     US-ASCII
     *   - .utf16be: UTF-16BE
     *   - .utf16le: UTF-16LE
     * 
     * @param file the file to get the encoding from
     * @return the encoding of the file, or null if the extension is not recognized
     */
    public Charset getEncoding(File file) {

        // Get the file name from the File object
        String fileName = file.getName();

        // Find the position of the last '.' in the file name to extract the extension
        int lastIndexOfDot = fileName.lastIndexOf('.');

        // Ensure there is a '.' and that it's not the first character
        if (lastIndexOfDot != -1 && lastIndexOfDot != fileName.length() - 1) {

            // Extract the part after the last '.' to check if it's a valid extension
            String extension = fileName.substring(lastIndexOfDot);

            // Match the extracted extension with the right charset
            switch (extension) {
                case ".utf8":
                    return StandardCharsets.UTF_8;
                case ".txt":
                    return StandardCharsets.US_ASCII;
                case ".utf16be":
                    return StandardCharsets.UTF_16BE;
                case ".utf16le":
                    return StandardCharsets.UTF_16LE;
            }
        }

        // The extension is not recognized
        return null;
    }
}