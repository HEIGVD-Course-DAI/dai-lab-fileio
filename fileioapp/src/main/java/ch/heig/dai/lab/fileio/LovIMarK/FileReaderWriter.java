package ch.heig.dai.lab.fileio.LovIMarK;

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

        if(file.exists()) {

            // StringBuilder is used to build the file content line by line.
            StringBuilder content = new StringBuilder();
            try (InputStream iS = new FileInputStream(file);
                 Reader reader = new InputStreamReader(iS, encoding);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {

                String line;
                // Read the file line by line until the end of the file
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } catch (IOException e) {
                System.out.println("Exception: " + e);
                return null;
            }
            return content.toString();
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

        try (OutputStream oS = new FileOutputStream(file);
             Writer writer = new OutputStreamWriter(oS, encoding);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            bufferedWriter.write(content);
            bufferedWriter.flush();
            return true;
        } catch (IOException e) {
                System.out.println("Exception: " + e);
            return false;
        }

    }
}
