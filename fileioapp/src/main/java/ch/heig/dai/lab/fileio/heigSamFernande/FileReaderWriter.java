package ch.heig.dai.lab.fileio.heigSamFernande;

import java.io.*;
import java.nio.charset.Charset;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * @param file the file to read. 
     * @param encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) throws IOException 
    {
        // If no file or encoding, return null
        if(file == null || encoding == null)
            return null;

        StringBuilder result = new StringBuilder();

        try (BufferedReader fileToRead = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))) 
        {
            String line;

            // Read and add to result the lines of the file
            while ((line = fileToRead.readLine()) != null) 
            {
                result.append(line).append("\n");
            }
            
        } 
        catch (IOException e) 
        {
            System.out.println("Exception : " + e);
            return null;
        }

        return result.toString();
    }


    /**
     * Write the content to a file with a given encoding. 
     * @param file the file to write to
     * @param content the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) 
    {
        // If one parameter null, return false
        if(file == null || content == null || encoding == null)
            return false;

        try (BufferedWriter fileToWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding)))
        {
            fileToWrite.write(content);
            fileToWrite.flush();

            return true;
        } 
        catch (IOException e) 
        {
            System.out.println("Exception : " + e);
            return false; // False if error
        }
    }

}
