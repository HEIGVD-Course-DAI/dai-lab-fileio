package ch.heig.dai.lab.fileio.nadi3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * @param file the file to read. 
     * @param encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        try (java.io.Reader reader = new FileReader(file, encoding)) {
            StringBuilder sb = new StringBuilder();
            int character = reader.read();
            while (character != -1) {
                sb.append((char) character);
                character = reader.read();
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            System.out.println("Exception: " + e);
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
        try (java.io.Writer writer = new FileWriter(file, encoding)) {
            writer.write(content);
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
        return false;
    }
}
