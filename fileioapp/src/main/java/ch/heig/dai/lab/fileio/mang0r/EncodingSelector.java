package ch.heig.dai.lab.fileio.mang0r;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Map;

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
        Map<String, String> availableCharsetsKeys = Map.of(
            ".utf8",    "UTF-8",
            ".txt",     "US-ASCII",
            ".utf16be", "UTF-16BE",
            ".utf16le", "UTF-16LE");

        try {
            return Charset.availableCharsets().get(availableCharsetsKeys.get(   // find the key to the charset
                file.getName().substring(file.getName().lastIndexOf('.'))        // from the end of the file name, after the '.'
            ));
        } 
        catch (Exception e) {
            return null;
        }
    }
}