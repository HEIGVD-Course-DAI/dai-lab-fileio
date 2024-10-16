package ch.heig.dai.lab.fileio.bvernet;

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
        String content = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getName()), encoding))){
            String line = "";
            while((line = reader.readLine()) != null){
                content += (line + "\n");
            }

        } catch (IOException e){
            System.out.println("Exception: " + e);
            return null;
        }

        return content;   
    }

    /**
     * Write the content to a file with a given encoding. 
     * @param file the file to write to
     * @param content the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
  
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getName()), encoding))){
            writer.write(content);
            writer.flush();
            return true;
        } catch (IOException e){
            System.err.println("Exception: " + e);
            return false;
        }
    }
}
