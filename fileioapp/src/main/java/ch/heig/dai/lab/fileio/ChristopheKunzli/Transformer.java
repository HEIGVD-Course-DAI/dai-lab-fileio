package ch.heig.dai.lab.fileio.ChristopheKunzli;

public class Transformer {

    private final String newName;
    private final int numWordsPerLine;

    /**
     * Constructor
     * Initialize the Transformer with the name to replace "Chuck Norris" with
     * and the number of words per line to use when wrapping the text.
     * @param newName         the name to replace "Chuck Norris" with
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
        return source.replace("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        String[] words = source.split(" ");
        for (int i = 0; i < words.length; ++i) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }
        return String.join(" ", words);
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        if (source.isEmpty()) return "";

        StringBuilder wrapped = new StringBuilder();
        String[] words = source.split(" ");

        int numberOfLines = words.length / numWordsPerLine + (words.length % numWordsPerLine == 0 ? 0 : 1);
        for (int i = 0; i < numberOfLines; ++i) {
            wrapped.append(i + 1).append(". ");

            int startIndex = i * numWordsPerLine;
            int endIndex = Math.min(startIndex + numWordsPerLine, words.length);
            for (int j = startIndex; j < endIndex; ++j) {
                wrapped.append(words[j]);
                if (j < endIndex - 1) {
                    wrapped.append(" ");
                }
            }
            wrapped.append("\n");
        }

        return wrapped.toString();
    }
}