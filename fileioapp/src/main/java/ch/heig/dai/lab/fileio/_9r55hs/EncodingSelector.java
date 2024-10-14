package ch.heig.dai.lab.fileio._9r55hs;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Dictionary;
import java.util.Hashtable;

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
        Dictionary<String, Charset> extensions = new Hashtable<>();
        extensions.put("utf8",      StandardCharsets.UTF_8);
        extensions.put("txt",       StandardCharsets.US_ASCII);
        extensions.put("utf16be",   StandardCharsets.UTF_16BE);
        extensions.put("utf16le",   StandardCharsets.UTF_16LE);

        String[] splitFilename = file.toString().split("\\.");
        String fileExt = splitFilename[splitFilename.length - 1];

        return extensions.get(fileExt);
    }
}