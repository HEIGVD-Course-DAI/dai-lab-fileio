package ch.heig.dai.lab.fileio.svelva;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class EncodingSelector {

    private final Map<String, Charset> extensionMapping = Map.of(
        ".txt", StandardCharsets.US_ASCII,
        ".utf8", StandardCharsets.UTF_8,
        ".utf16be", StandardCharsets.UTF_16BE,
        ".utf16le", StandardCharsets.UTF_16LE
    );

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
        for (String key : extensionMapping.keySet()) {
            if (file.getName().endsWith(key)) {
                return extensionMapping.get(key);
            }
        }
        return null;
    }
}