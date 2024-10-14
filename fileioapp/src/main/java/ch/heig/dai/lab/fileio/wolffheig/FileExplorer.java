package ch.heig.dai.lab.fileio.wolffheig;

import java.io.File;
import java.util.HashSet;

public class FileExplorer {
    private final File folder;
    private HashSet<File> knownFiles;

    /**
     * Constructor
     * Memorize the folder to explore and initialize the set of known files.
     * @param folder
     */
    public FileExplorer(String folder) {
        this.folder = new File(folder);
        this.knownFiles = new HashSet<File>();
    }

    /**
     * Get a single new file from the folder.
     * The file must not have been returned before.
     * Use the java.io.file API to get the list of files in the folder.
     * Use the HashSet knownFiles to keep track of the files that have already been returned.
     * @return a new file, or null if there is no new file
     */
    public File getNewFile() {

        // Ensure the folder exists and is a directory
        if (!folder.exists() || !folder.isDirectory()) {
            return null;
        }

        // Get the list of files in the folder
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                // Check if the file is not a directory and has not been returned before
                if (file.isFile() && !knownFiles.contains(file)) {
                    // Add the file to knownFiles and return it
                    knownFiles.add(file);
                    return file;
                }
            }
        }

        // If no new file is found, return null
        return null;
    }
}