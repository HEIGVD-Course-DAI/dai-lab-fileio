package ch.heig.dai.lab.fileio.wolffheig;

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

        StringBuilder content = new StringBuilder();

        // Use try-with-resources to automatically close the resources
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))) {
            String line;
            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }

            return content.toString();

        } catch (IOException e) {
            // Handle the exception and return null if an error occurs
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

        // Use try-with-resources to automatically close the resources
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding))) {
            // Write the content to the file
            writer.write(content);
            writer.flush(); // Ensure all buffered data is written to the file directly

            return true;

        } catch (IOException e) {
            // Handle the exception and return false if an error occurs
            e.printStackTrace();

            return false;
        }

    }
}
