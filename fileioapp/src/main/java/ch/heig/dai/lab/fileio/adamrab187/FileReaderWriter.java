package ch.heig.dai.lab.fileio.adamrab187;

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
        // Stores the content of the file
        StringBuilder content = new StringBuilder();
        try (
                // Reads raw bytes from the file
                FileInputStream fis = new FileInputStream(file);
                // Converts bytes to characters with the specified encoding
                InputStreamReader isr = new InputStreamReader(fis, encoding);
                // Buffers the input to improve performance
                BufferedReader reader = new BufferedReader(isr)
        ) {
            String line;
            // Reads each line from the file and appends it to the StringBuilder
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();    // Prints the stack trace for debugging purposes
            return null;            // Returns null if an error occurs
        }
        return content.toString();  // Returns the content of the file as a string
    }

    /**
     * Write the content to a file with a given encoding.
     * @param file the file to write to
     * @param content the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
        try (
                // Writes raw bytes to the file
                FileOutputStream fos = new FileOutputStream(file);
                // Converts characters to bytes using the specified encoding
                OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
                // Buffers the output to improve performance
                BufferedWriter writer = new BufferedWriter(osw)
        ) {
            writer.write(content);          // Writes the content to the file
            writer.flush();                 // Ensures all data is flushed to the file
        } catch (IOException e) {
            e.printStackTrace();            // Prints the stack trace for debugging purposes
            return false;                   // Returns false if an error occurs during writing
        }
        return true;                        // Returns true if the file was successfully written
    }
}
