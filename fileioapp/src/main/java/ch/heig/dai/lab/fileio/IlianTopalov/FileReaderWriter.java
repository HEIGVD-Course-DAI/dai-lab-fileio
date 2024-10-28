package ch.heig.dai.lab.fileio.IlianTopalov;

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
        try {
            var reader = new BufferedReader(
                        new InputStreamReader(
                            new FileInputStream(
                                    file.getPath()
                            ), encoding
                        )
            );

            // Create StringBuilder in order not to create new strings for each iteration
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }

            // Remove the line break at the end of the file
            output.deleteCharAt(output.length() - 1);
            reader.close();

            return output.toString();

        } catch (IOException e) {
            System.err.println(e.getMessage());
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

        try {
            var writer = new BufferedWriter(
                            new OutputStreamWriter(
                                new FileOutputStream(
                                    file.getPath()
                                ), encoding
                            )
            );

            writer.write(content);
            writer.close();  // Flush and close stream writer

        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
}
