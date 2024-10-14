package ch.heig.dai.lab.fileio.chomusukeworks;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

public class FileExplorer {
	private final File folder;
	private final HashSet<File> knownFiles;

	/**
	 * Constructor
	 * Memorize the folder to explore and initialize the set of known files.
	 *
	 * @param folder the absolute path to a folder
	 */
	public FileExplorer(String folder) {
		this.folder = new File(folder);
		this.knownFiles = new HashSet<>();
	}

	/**
	 * Get a single new file from the folder.
	 * The file must not have been returned before.
	 * Use the java.io.file API to get the list of files in the folder.
	 * Use the HashSet knownFiles to keep track of the files that have already been returned.
	 *
	 * @return a new file, or null if there is no new file
	 */
	public File getNewFile() {
		File[] files = this.folder.listFiles();
		if (files == null)
			return null;

		Optional<File> newFile = Arrays.stream(files)
				.filter(file -> file.isFile() && !this.knownFiles.contains(file))
				.findAny();

		if (newFile.isPresent()) {
			this.knownFiles.add(newFile.get());

			return newFile.get();
		} else {
			return null;
		}
	}
}