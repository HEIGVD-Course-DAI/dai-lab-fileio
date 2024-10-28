package ch.heig.dai.lab.fileio.aminekhelfi;

import java.io.*;
import java.nio.charset.Charset;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     *
     * @param file     the file to read.
     * @param encoding
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        // TODO: Implement the method body here.
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to close the streams and readers at the end.

        //ouvrir fichier en mode lecture dans le try comme vu en cours
        try (var reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))) {
            String file_content = "";
            String line = "";

            while ((line = reader.readLine()) != null) { //parcours le fichier ligne par ligne
                file_content += line + "\n"; //mets une '\n' à la fin car c'est il y a pas le \n quand on fait un readline
            }

            return file_content; //retourne le contenu du fichier

        } catch (IOException e) {
            System.out.println("Erreur: "+e.getMessage());

        }
        return null;//retourne null si erreur
    }

    /**
     * Write the content to a file with a given encoding.
     *
     * @param file     the file to write to
     * @param content  the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
        // TODO: Implement the method body here.
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to flush the data and close the streams and readers at the end.


        //ouvrir le fichier en mode écriture dans le try comme vu en cours
        try (var writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding))) {

            writer.write(content+"\r\n"); //\r\n pour faire un retour à la ligne compatible avec windows même si en réalité y en a plus besoin (par précaution)
            writer.flush();

            return true; //retourne le contenu du fichier

        } catch (IOException e) {
            System.out.println("Erreur: " + e);

        }
        return false;//retourne false en cas d'erreur.

    }
}

