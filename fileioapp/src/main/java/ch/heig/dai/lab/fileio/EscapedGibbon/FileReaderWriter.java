package ch.heig.dai.lab.fileio.EscapedGibbon;

import java.io.*;
import java.nio.charset.Charset;
public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * @param file the file to read. 
     * @param encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, encoding);
            BufferedReader reader = new BufferedReader(isr)){
           while ((line = reader.readLine()) != null) {
           sb.append(line).append(System.lineSeparator());
        }
        return sb.toString();
      }
      catch (IOException e) {
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
    public boolean writeFile(File file, String content, Charset encoding) throws IOException{
      try (FileOutputStream fos = new FileOutputStream(file);
          OutputStreamWriter osr = new OutputStreamWriter(fos, encoding);
          BufferedWriter writer = new BufferedWriter(osr)){
            writer.write(content); 
            writer.flush();
            return true;
      }catch (IOException e) {
        e.printStackTrace();
        return false;
      } 
    }
  } 
