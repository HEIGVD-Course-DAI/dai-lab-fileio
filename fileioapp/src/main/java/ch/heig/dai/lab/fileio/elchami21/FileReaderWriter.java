package ch.heig.dai.lab.fileio.elchami21;

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
        if (file == null || encoding == null) {
            return null;
        }

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, encoding);
             BufferedReader reader = new BufferedReader(isr)) {
            
            StringBuilder content = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
            
            return content.toString();
            
        } catch (IOException e) {
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
        if (file == null || content == null || encoding == null) {
            return false;
        }

        try (FileOutputStream fos = new FileOutputStream(file);
             OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
             BufferedWriter writer = new BufferedWriter(osw)) {
            
            writer.write(content);
            writer.flush();
            return true;
            
        } catch (IOException e) {
            return false;
        }
    }
}