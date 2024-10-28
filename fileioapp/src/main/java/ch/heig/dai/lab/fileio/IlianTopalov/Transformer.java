package ch.heig.dai.lab.fileio.IlianTopalov;

public class Transformer {

    private final String newName;
    private final int numWordsPerLine;

    /**
     * Constructor
     * Initialize the Transformer with the name to replace "Chuck Norris" with
     * and the number of words per line to use when wrapping the text.
     * @param newName the name to replace "Chuck Norris" with
     * @param numWordsPerLine the number of words per line to use when wrapping the text
     */
    public Transformer(String newName, int numWordsPerLine) {
        this.newName = newName;
        this.numWordsPerLine = numWordsPerLine;
    }

    /**
     * Replace all occurrences of "Chuck Norris" with the name given in the constructor.
     * @param source the string to transform
     * @return the transformed string
     */
    public String replaceChuck(String source) {
        // TODO: Implement the method body here.
        return source.replaceAll("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.
        String[] words = source.split(" ");
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {
            capitalized.append(word.substring(0, 1).toUpperCase())
                    .append(word.substring(1))
                    .append(" ");
        }
        // Remove last appended space
        return capitalized.toString().trim();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        // TODO: Implement the method body here.
        // Use the StringBuilder class to build the result string.
        if (numWordsPerLine < 1) {
            return source;
        }

        String[] words = source.split(" ");

        int wordCounter = 0;
        int lineCounter = 1;
        StringBuilder wrap = new StringBuilder();

        for (String word : words) {
            if (wordCounter >= numWordsPerLine) {
                wrap.append("\n"); // Start a new line after reaching numWordsPerLine words
                wordCounter = 0;
                ++lineCounter;
            }

            if (wordCounter == 0) {
                wrap.append(lineCounter).append(". ");  // Insert line number when new line
            }

            if (wordCounter > 0) { // Separate words by spaces while numWordsPerLine not reached
                wrap.append(" ");
            }

            wrap.append(word); // Append word
            ++wordCounter;
        }

        return wrap.toString();
    }
}   