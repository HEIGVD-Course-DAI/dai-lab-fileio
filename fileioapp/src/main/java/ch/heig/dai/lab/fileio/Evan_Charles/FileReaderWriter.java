package ch.heig.dai.lab.fileio.Evan_Charles;

import java.io.*;
import java.nio.charset.Charset;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * 
     * @param file     the file to read.
     * @param encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))) {

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }

            return content.toString();

        } catch (IOException e) {
            return null;
        }

    }

    /**
     * Write the content to a file with a given encoding.
     * 
     * @param file     the file to write to
     * @param content  the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding))) {

            out.write(content);
            out.flush();

            return true;

        } catch (IOException e) {
            return false;
        }
    }
}
