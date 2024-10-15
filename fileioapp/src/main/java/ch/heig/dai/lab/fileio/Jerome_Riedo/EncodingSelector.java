package ch.heig.dai.lab.fileio.Jerome_Riedo;

import java.io.File;
import java.nio.charset.Charset;

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
        // TODO: implement the method body here
            int index = file.getName().lastIndexOf('.');
            if (index > 0) {
                String extension = file.getName().substring(index+1);

                switch (extension) {
                    case "utf8" :
                        return Charset.forName("UTF-8");
                    case "txt" :
                        return Charset.forName("ASCII");
                    case "utf16be" :
                        return Charset.forName("UTF-16BE");
                    case "utf16le" :
                        return Charset.forName("UTF-16LE");
                    default:
                        return null;
                }
            }
        return null;
    }
}
