package ch.heig.dai.lab.fileio.aleddaheig;

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
        if (source != null) {
            return source.replace("Chuck Norris", newName);
        }
        return source;
    }

    /**
     * Capitalize the first letter of each word in the string.
     * 
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        StringBuilder result = new StringBuilder();
        String[] words = source.split(" ");

        for (String word : words) {
            // Capitalize the first letter and append the end of the word
            result.append(word.substring(0, 1).toUpperCase());
            result.append(word.substring(1));
            result.append(" ");
        }

        // Convert to String and remove leading and trailing space
        return result.toString().trim();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * 
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        StringBuilder result = new StringBuilder();
        String[] words = source.split(" ");

        for (int i = 0; i < words.length; i++) {
            if (i % numWordsPerLine == 0) {
                // If the number of words in this line is a multiple of numWordsPerLine
                if (i > 0) {
                    // Insert newline character except for line 0
                    result.append("\n");
                }
                // Number the line
                result.append(i / numWordsPerLine + 1).append(". ");
            } else {
                result.append(" ");
            }
            result.append(words[i]);
        }
        result.append("\n");

        return result.toString();
    }
}
