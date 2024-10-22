package ch.heig.dai.lab.fileio.hliosone;

import java.io.*;
import java.nio.charset.Charset;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * @param file the file to read. 
     * @param encoding file encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        // TODO: Implement the method body here. 
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to close the streams and readers at the end.
        if (file == null || encoding == null) {
            return null;
        }

        try(
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, encoding);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ){
            StringBuilder sbuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sbuilder.append(line).append(System.lineSeparator());
            }
            return sbuilder.toString();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e);
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
        // TODO: Implement the method body here. 
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to flush the data and close the streams and readers at the end.

        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, encoding);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)
                ){
            bufferedWriter.write(content);
            bufferedWriter.flush();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing file: " + e);
            return false;
        }
    }
}
