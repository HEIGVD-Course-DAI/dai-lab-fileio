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

        // Get the file name
        String fileName = file.getName();

        // Check the file extension and return the corresponding Charset
        if (fileName.endsWith(".utf8")) {
            return StandardCharsets.UTF_8;
        } else if (fileName.endsWith(".txt")) {
            return StandardCharsets.US_ASCII;
        } else if (fileName.endsWith(".utf16be")) {
            return StandardCharsets.UTF_16BE;
        } else if (fileName.endsWith(".utf16le")) {
            return StandardCharsets.UTF_16LE;
        }

        // The extension is not recognized
        return null;
    }
}