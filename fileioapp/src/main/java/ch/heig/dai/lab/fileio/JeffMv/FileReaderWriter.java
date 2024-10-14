package ch.heig.dai.lab.fileio.JeffMv;

import java.io.*;
import java.nio.charset.Charset;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * @param file the file to read. 
     * @param encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to close the streams and readers at the end.

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader( new FileInputStream(file.getAbsolutePath()), encoding ) )) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
            return content.toString();
        } catch (IOException e) {
            System.out.println("An error occurred with file: " + file.getAbsolutePath());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Write the content to a file with a given encoding. 
     * @param file the file to write to
     * @param content the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to flush the data and close the streams and readers at the end.

        try {
            try (BufferedWriter fos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath()), encoding))) {
                fos.write(content);
                return true;
            }
        } catch ( IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
