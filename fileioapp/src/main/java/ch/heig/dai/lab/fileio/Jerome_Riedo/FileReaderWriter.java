package ch.heig.dai.lab.fileio.Jerome_Riedo;

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
        // TODO: Implement the method body here. 
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to close the streams and readers at the end.
        var result = new StringBuilder();
        try (var is = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));){
            String line;
            while ((line = is.readLine()) != null){
                result.append(line).append("\n");
            }
            return result.toString();
        }
        catch (FileNotFoundException e) {
            System.out.println("ReaderFile not found: " + file.getAbsolutePath());
            return null;
        }
        catch (IOException e) {
            System.out.println("ReaderFile IOException: " + e.getMessage());
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
        try (var os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));) {
            os.write(content + "\n");
            os.flush();
            return true;
        }
        catch (FileNotFoundException e){
            System.out.println("WriterFile not found: " + file.getAbsolutePath());
            return false;
        }
        catch (IOException e) {
            System.out.println("WriterFile IOException: " + e.getMessage());
            return false;
        }
    }
}
