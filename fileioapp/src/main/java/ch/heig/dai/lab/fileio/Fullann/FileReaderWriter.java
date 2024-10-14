package ch.heig.dai.lab.fileio.Fullann;

import java.io.*;
import java.nio.charset.Charset;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * @param file the file to read. 
     * @param encoding the charset to use for reading the file
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        StringBuilder content = new StringBuilder();

        // Try-with-resources to ensure streams are closed automatically
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), encoding);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }

            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Write the content to a file with a given encoding.
     * @param file the file to write to
     * @param content the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
        // Try-with-resources to ensure streams are closed automatically
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), encoding);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            bufferedWriter.write(content);
            bufferedWriter.flush();  // Ensure all data is written to the file

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
