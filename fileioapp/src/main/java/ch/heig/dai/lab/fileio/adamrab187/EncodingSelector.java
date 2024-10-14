package ch.heig.dai.lab.fileio.adamrab187;

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
        // Get the name of the file
        String fileName = file.getName();

        // Find the last occurrence of the dot character in the file name
        int lastDotIndex = fileName.lastIndexOf('.');

        // If a dot is found and it is not the last character in the file name
        if (lastDotIndex != -1) {
            // Extract the file extension
            String extension = fileName.substring(lastDotIndex + 1);

            // Return the corresponding Charset based on the file extension
            return switch (extension) {
                case "utf8" -> StandardCharsets.UTF_8;
                case "txt" -> StandardCharsets.US_ASCII;
                case "utf16be" -> StandardCharsets.UTF_16BE;
                case "utf16le" -> StandardCharsets.UTF_16LE;
                default -> null; // Return null if the file extension is not recognized
            };
        }
        // If no dot is found or it is the last character in the file name return null
        return null;
    }
}
