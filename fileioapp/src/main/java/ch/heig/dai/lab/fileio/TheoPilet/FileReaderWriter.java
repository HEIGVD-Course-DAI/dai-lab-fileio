package ch.heig.dai.lab.fileio.TheoPilet;

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
        StringBuilder writer = new StringBuilder();
        try (FileInputStream fileReader = new FileInputStream(file);) {
            var reader = new BufferedReader(new InputStreamReader(fileReader, encoding));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.append(line).append("\r \n");
            }
            return writer.toString();
        } catch (IOException e){
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
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            var writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, encoding));
            writer.write(content);
            writer.flush();
            return true;
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
        return false;
    }
}
