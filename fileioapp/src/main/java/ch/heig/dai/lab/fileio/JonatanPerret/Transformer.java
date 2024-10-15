package ch.heig.dai.lab.fileio.JonatanPerret;

public class Transformer {

    private final String newName;
    private final int numWordsPerLine;

    /**
     * Constructor Initialize the Transformer with the name to replace "Chuck
     * Norris" with and the number of words per line to use when wrapping the
     * text.
     *
     * @param newName the name to replace "Chuck Norris" with
     * @param numWordsPerLine the number of words per line to use when wrapping
     * the text
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
        return source.replaceAll("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     *
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {

        StringBuilder result = new StringBuilder(source.length());
        boolean capitalizeNext = true;

        for (int i = 0; i < source.length(); i++) {
            char currentChar = source.charAt(i);
            if (Character.isWhitespace(currentChar)) {
                capitalizeNext = true;
                result.append(currentChar);
            } else if (capitalizeNext) {
                result.append(Character.toUpperCase(currentChar));
                capitalizeNext = false;
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     *
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        // TODO: Implement the method body here.
        // Use the StringBuilder class to build the result string.

        StringBuilder result = new StringBuilder(source.length());
        String[] words = source.split(" ");

        int line = 1;
        int wordsInLine = 0;

        for (int i = 0; i < words.length; i++) {
            if (wordsInLine == 0) {
                result.append(line).append(". ");
            }
            result.append(words[i]);
            // Add a space only if it's not the last word on the line or the last word in the text
            if (wordsInLine < numWordsPerLine - 1 && i < words.length - 1) {
                result.append(" ");
            }
            wordsInLine++;
            if (wordsInLine == numWordsPerLine || i == words.length - 1) {
                result.append("\n");
                line++;
                wordsInLine = 0;
            }
        }

        return result.toString();
    }
}
