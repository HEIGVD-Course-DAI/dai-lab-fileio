package ch.heig.dai.lab.fileio.Evan_Charles;

public class Transformer {

    private final String newName;
    private final int numWordsPerLine;

    /**
     * Constructor
     * Initialize the Transformer with the name to replace "Chuck Norris" with
     * and the number of words per line to use when wrapping the text.
     * 
     * @param newName         the name to replace "Chuck Norris" with
     * @param numWordsPerLine the number of words per line to use when wrapping the
     *                        text
     */
    public Transformer(String newName, int numWordsPerLine) {
        this.newName = newName;
        this.numWordsPerLine = numWordsPerLine;
    }

    /**
     * Replace all occurrences of "Chuck Norris" with the name given in the
     * constructor.
     * 
     * @param source the string to transform
     * @return the transformed string
     */
    public String replaceChuck(String source) {
        return source.replace("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * 
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        StringBuilder capitalized = new StringBuilder();
        String[] words = source.split("\\s+");

        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase()).append(" ");
            }
        }

        return capitalized.toString().trim();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * 
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        StringBuilder wrapped = new StringBuilder();
        String[] words = source.split("\\s+");

        for (int i = 0; i < words.length; i += numWordsPerLine) {
            wrapped.append(i / numWordsPerLine + 1).append(". ");
            for (int j = i; j < Math.min(i + numWordsPerLine, words.length); j++) {
                wrapped.append(words[j]).append(" ");
            }
            wrapped.setLength(wrapped.length() - 1);
            wrapped.append("\n");
        }

        return wrapped.toString();
    }
}
