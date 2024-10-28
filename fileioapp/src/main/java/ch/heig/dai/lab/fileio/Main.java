package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;

// *** TODO: Change this to import your own package ***
import ch.heig.dai.lab.fileio.aminekhelfi.*;

public class Main {
    // *** TODO: Change this to your own name ***
    private static final String newName = "Amine Khelfi";

    /**
     * Main method to transform files in a folder.
     * Create the necessary objects (FileExplorer, EncodingSelector, FileReaderWriter, Transformer).
     * In an infinite loop, get a new file from the FileExplorer, determine its encoding with the EncodingSelector,
     * read the file with the FileReaderWriter, transform the content with the Transformer, write the result with the
     * FileReaderWriter.
     *
     * Result files are written in the same folder as the input files, and encoded with UTF8.
     *
     * File name of the result file:
     * an input file "myfile.utf16le" will be written as "myfile.utf16le.processed",
     * i.e., with a suffixe ".processed".
     */
    public static void main(String[] args) {
        // Read command line arguments
        if (args.length != 2 || !new File(args[0]).isDirectory()) {
            System.out.println("You need to provide two command line arguments: an existing folder and the number of words per line.");
            System.exit(1);
        }
        String folder = args[0];
        int wordsPerLine = Integer.parseInt(args[1]);
        System.out.println("Application started, reading folder " + folder + "...");
        // TODO: implement the main method here

        //Initialiser les objets
        FileExplorer fileExplorer = new FileExplorer(folder); //découvrir l'ensemble des fichiers
        EncodingSelector encodingSelector = new EncodingSelector(); //pour l'encodage du fichier
        FileReaderWriter fileReaderWriter = new FileReaderWriter(); //lecture et écriture du fichier
        Transformer transformer= new Transformer(newName,wordsPerLine); //appliquer les fonctions de transformation

        while (true) {
            try {
                // TODO: loop over all files
                File input=fileExplorer.getNewFile();//récupérer le prochain fichier

                if(input==null)
                {
                    System.out.println("Tout les fichiers ont été traités");
                    break;
                }

                //ne pas traiter les fichier avec l'extention .processed
                int index=input.getName().indexOf(".processed");
                if(index!=-1 && index == input.getName().length() - ".processed".length()) //vérifie que le fichier process est bien à la fin du fichier
                {
                    continue;
                }

                //Détermine l'encodage
                Charset encoding =encodingSelector.getEncoding(input);
                if(encoding==null)
                {
                    System.out.println("Encodage non trouvé: "+input.getName());
                    continue;
                }

                //lire le contenu
                String content=fileReaderWriter.readFile(input,encoding);
                if(content==null)
                {
                    System.out.println("Erreur lecture fichier: "+input.getName());
                    continue;
                }


                String contentTransformed= transformer.replaceChuck(content); //faire le remplace de Chuck Norris
                contentTransformed=transformer.capitalizeWords(contentTransformed); //mettre toute les 1ère lettres des mots en majuscule
                contentTransformed=transformer.wrapAndNumberLines(contentTransformed); //mettre la phrase en ligne

                //création fichier sorti
                File output=new File(input.getPath()+".processed"); //utiliser le chemin du fichier entrée pour la création du fichier de sorti en ajoutant le .process

                if(fileReaderWriter.writeFile(output,contentTransformed,encoding)) //écriture dans fichier si la fonction retourne false il y a une erreur.
                {
                    System.out.println("Fichier traité: "+output.getName());
                }
                else{
                    System.out.println("Erreur écriture fichier: "+output.getName());
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
