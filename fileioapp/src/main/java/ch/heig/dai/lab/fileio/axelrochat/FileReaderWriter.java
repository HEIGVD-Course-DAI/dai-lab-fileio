package ch.heig.dai.lab.fileio.axelrochat;

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
    
        try (InputStream fileInputStream = new FileInputStream(file);
             Reader reader = new InputStreamReader(fileInputStream, encoding);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
    
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    
        return content.toString();
    }

    /**
     * Write the content to a file with a given encoding. 
     * @param file the file to write to
     * @param content the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
        try (OutputStream fileOutputStream = new FileOutputStream(file);
             Writer writer = new OutputStreamWriter(fileOutputStream, encoding);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
    
            bufferedWriter.write(content);
    
            bufferedWriter.flush();
    
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
