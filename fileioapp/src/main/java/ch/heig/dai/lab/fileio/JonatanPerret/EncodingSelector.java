package ch.heig.dai.lab.fileio.JonatanPerret;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;

public class EncodingSelector {

    // dictionary of supported encodings
    private static final HashMap<String, String> supportedEncoding = new HashMap<String, String>() {
        {
            put(".utf8", "UTF-8");
            put(".txt", "US-ASCII");
            put(".utf16be", "UTF-16BE");
            put(".utf16le", "UTF-16LE");
        }
    };

    /**
     * Get the encoding of a file based on its extension. The following
     * extensions are recognized: - .utf8: UTF-8 - .txt: US-ASCII - .utf16be:
     * UTF-16BE - .utf16le: UTF-16LE
     *
     * @param file the file to get the encoding from
     * @return the encoding of the file, or null if the extension is not
     * recognized
     */
    public Charset getEncoding(File file) {
        // get the extension of the file
        try {
            String extension = file.getName().substring(file.getName().lastIndexOf('.'));

            if (supportedEncoding.containsKey(extension)) {
                return Charset.forName(supportedEncoding.get(extension));
            }
        } catch (Exception e) {
            return null;
        }
        return null;

    }
}
