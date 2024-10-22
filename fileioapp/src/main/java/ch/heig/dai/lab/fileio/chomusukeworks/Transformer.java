package ch.heig.dai.lab.fileio.chomusukeworks;

import java.util.List;

public class Transformer {

	private final String newName;
	private final int numWordsPerLine;

	/**
	 * Constructor
	 * Initialize the Transformer with the name to replace "Chuck Norris" with
	 * and the number of words per line to use when wrapping the text.
	 *
	 * @param newName         the name to replace "Chuck Norris" with
	 * @param numWordsPerLine the number of words per line to use when wrapping the text
	 */
	public Transformer(String newName, int numWordsPerLine) {
		this.newName = newName;
		this.numWordsPerLine = numWordsPerLine;
	}

	/**
	 * Replace all occurrences of "Chuck Norris" with the name given in the constructor.
	 *
	 * @param source the string to transform
	 * @return the transformed string
	 */
	public String replaceChuck(String source) {
		return source.replaceAll("Chuck Norris", newName);
	}

	/**
	 * Capitalize the first letter of each word in the string.
	 *
	 * @param source the string to transform
	 * @return the transformed string
	 */
	public String capitalizeWords(String source) {
		String[] words = source.split(" ");

		var result = new StringBuilder();

		for (String word : words) {
			result.append(' ');
			result.append(firstCharToUpperCase(word));
		}

		return result.substring(1);
	}

	/**
	 * Wrap the text so that there are at most numWordsPerLine words per line.
	 * Number the lines starting at 1.
	 *
	 * @param source the string to transform
	 * @return the transformed string
	 */
	public String wrapAndNumberLines(String source) {
		if (numWordsPerLine <= 0) return source;
		List<String> words = List.of(source.split(" "));

		var result = new StringBuilder();

		// Creates the result one line at a time
		int nbLines = words.size() / numWordsPerLine + (words.size() % numWordsPerLine == 0 ? 0 : 1);
		for (int i = 0; i < nbLines; ++i) {
			int first = i * numWordsPerLine;
			int last = Math.min((i + 1) * numWordsPerLine, words.size());

			String s = String.format("%d. %s\n",
					i + 1,
					String.join(" ", words.subList(first, last))
			);
			System.out.println(s);

			result.append(s);
		}

		return result.toString();
	}

	private String firstCharToUpperCase(String word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1);
	}
}   