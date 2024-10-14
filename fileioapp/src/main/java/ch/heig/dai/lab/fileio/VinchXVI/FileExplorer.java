package ch.heig.dai.lab.fileio.VinchXVI;

import java.io.File;
import java.util.HashSet;
import java.util.*;
import java.util.stream.*;

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
        // TODO: implement the method body here
        java.io.File[] listFiles = this.folder.listFiles();

        for( File f : listFiles )
            if(this.knownFiles.contains(f)) continue;
            else{
                this.knownFiles.add(f);
                return f;
            }


        return null;
    }
}