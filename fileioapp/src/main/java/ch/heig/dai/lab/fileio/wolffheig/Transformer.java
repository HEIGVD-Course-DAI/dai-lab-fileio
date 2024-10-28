package ch.heig.dai.lab.fileio.wolffheig;

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
        return source.replace("Chuck Norris", this.newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // Split the string by one or more whitespace characters
        String[] words = source.split("\\s+");

        // Capitalize only the first letter of each word
        for (int i = 0; i < words.length; i++) {
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
        // Split the input text into words
        String[] words = source.split("\\s+"); // Split by one or more spaces
        StringBuilder result = new StringBuilder();

        int wordCount = 0;
        int lineNumber = 1;

        // Iterate over the words and group them into lines of numWordsPerLine
        for (String word : words) {
            if (wordCount == 0) {
                // Start a new line with the line number
                result.append(lineNumber).append(". ");
                lineNumber++;
            }

            result.append(word);
            wordCount++;

            if (wordCount >= this.numWordsPerLine) {
                // Wrap to a new line after numWordsPerLine words
                result.append("\n");
                wordCount = 0;
            } else if (!word.equals(words[words.length - 1])) {
                result.append(" ");
            } else {
                result.append("\n");
            }
        }

        return result.toString();
    }

}