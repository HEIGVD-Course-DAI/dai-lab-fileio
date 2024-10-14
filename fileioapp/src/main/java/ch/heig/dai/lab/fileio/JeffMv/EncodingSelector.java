package ch.heig.dai.lab.fileio.JeffMv;

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

        // The method getEncoding returns the Charset to be used to read an input file.
        // It does this by looking at the extension of the file name.

        String fname = file.getName();

        // v1
        /*
        if (fname.endsWith(".utf8")) {
            return StandardCharsets.UTF_8;
        } else if (fname.endsWith(".txt")) {
            return StandardCharsets.US_ASCII;
        } else if (fname.endsWith(".utf16be")) {
            return StandardCharsets.UTF_16BE;
        } else if (fname.endsWith(".utf16le")) {
            return StandardCharsets.UTF_16LE;
        }*/

        // v2
        // WARNING : don't use split(".") split takes a rege...
        var parts = fname.split("\\.");

        String extension = parts[parts.length - 1];
        switch (extension) {
            case "utf8":
                return StandardCharsets.UTF_8;
            case "txt":
                return StandardCharsets.US_ASCII;
            case "utf16be":
                return StandardCharsets.UTF_16BE;
            case "utf16le":
                return StandardCharsets.UTF_16LE;
        }

        return null;
    }
}