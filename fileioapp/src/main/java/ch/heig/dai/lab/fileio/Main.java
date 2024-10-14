package ch.heig.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import ch.heig.dai.lab.fileio.Fullann.*;

public class Main {
    private static final String newName = "Nathan Füllemann - Fullann";

    public static void main(String[] args) {
        // Read command line arguments
        if (args.length != 2 || !new File(args[0]).isDirectory()) {
            System.out.println("You need to provide two command line arguments: an existing folder and the number of words per line.");
            System.exit(1);
        }

        String folder = args[0];
        int wordsPerLine = Integer.parseInt(args[1]);

        System.out.println("Application started, reading folder " + folder + "...");

        // Créer les objets nécessaires
        FileExplorer fileExplorer = new FileExplorer(folder);
        EncodingSelector encodingSelector = new EncodingSelector();
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        Transformer transformer = new Transformer(newName, wordsPerLine);

        // Boucle pour traiter les fichiers
        while (true) {
            try {
                File file = fileExplorer.getNewFile();
                if (file == null) {
                    System.out.println("Aucun nouveau fichier à traiter. Fin du programme.");
                    break;
                }

                // Skip fichier qui se finisse par ".processed"
                if (file.getName().endsWith(".processed")) {
                    System.out.println("Fichier déjà traité: " + file.getName() + " - Ignoré.");
                    continue;
                }

                Charset encoding = encodingSelector.getEncoding(file);
                if (encoding == null) {
                    System.out.println("Encodage inconnu pour le fichier: " + file.getName());
                    continue;
                }

                String content = fileReaderWriter.readFile(file, encoding);
                if (content == null) {
                    System.out.println("Échec de la lecture du fichier: " + file.getName());
                    continue;
                }

                String transformedContent = transformer.replaceChuck(content);
                transformedContent = transformer.capitalizeWords(transformedContent);
                transformedContent = transformer.wrapAndNumberLines(transformedContent);

                String outputFileName = file.getName() + ".processed";
                File outputFile = new File(file.getParent(), outputFileName);

                boolean success = fileReaderWriter.writeFile(outputFile, transformedContent, Charset.forName("UTF-8"));
                if (success) {
                    System.out.println("Fichier traité et écrit dans: " + outputFile.getName());
                } else {
                    System.out.println("Échec de l'écriture du fichier: " + outputFile.getName());
                }

            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}
