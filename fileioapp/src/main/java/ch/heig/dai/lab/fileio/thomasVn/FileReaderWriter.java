package ch.heig.dai.lab.fileio.thomasVn;

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

        if (file == null || encoding == null) {
            return null;
        }

        try {
            var reader = new FileReader(file, encoding);
            var bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
            bufferedReader.close();
            return stringBuilder.toString();

        } catch (IOException e) {
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
    public boolean writeFile(File file, String content, Charset encoding) {
        // TODO: Implement the method body here. 
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to flush the data and close the streams and readers at the end.

        if (file == null || content == null || encoding == null) {
            return false;
        }

        try {
            OutputStream outputStream = new FileOutputStream(file);
            Writer writer = new OutputStreamWriter(outputStream, encoding);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}