package ch.heig.dai.lab.fileio.Evan_Charles;

import java.io.File;
import java.nio.charset.Charset;

public class EncodingSelector {

    /**
     * Get the encoding of a file based on its extension.
     * The following extensions are recognized:
     * - .utf8: UTF-8
     * - .txt: US-ASCII
     * - .utf16be: UTF-16BE
     * - .utf16le: UTF-16LE
     * 
     * @param file the file to get the encoding from
     * @return the encoding of the file, or null if the extension is not recognized
     */
    public Charset getEncoding(File file) {
        if (file == null) {
            return null;
        }
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex == -1) {
            return null; // ou retourner une valeur par défaut, par exemple UTF-8
        }

        String extension = fileName.substring(dotIndex);

        switch (extension) {
            case ".utf8":
                return Charset.forName("UTF-8");
            case ".txt":
                return Charset.forName("US-ASCII");
            case ".utf16be":
                return Charset.forName("UTF-16BE");
            case ".utf16le":
                return Charset.forName("UTF-16LE");
            default:
                return null;
        }
    }
}
