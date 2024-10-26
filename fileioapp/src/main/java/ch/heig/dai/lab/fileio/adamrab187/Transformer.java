package ch.heig.dai.lab.fileio.adamrab187;

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
        String[] words = source.split("\\s+");
        StringBuilder result = new StringBuilder();

        // Capitalize the first letter of each word and append the rest in lowercase
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        // Ensure rest of the word is in lowercase
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        // Trim any extra space at the end
        return result.toString().trim();
    }


    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        String[] words = source.split("\\s+");
        StringBuilder result = new StringBuilder();
        int lineNumber = 1;
        int wordCount = 0;

        // Start numbering from 1 and append the first line number
        result.append(lineNumber).append(". ");

        for (String word : words) {
            result.append(word);
            wordCount++;
            if (wordCount == this.numWordsPerLine) {
                lineNumber++;       // Increment line number
                result.append(System.lineSeparator()).append(lineNumber).append(". ");
                wordCount = 0;      // Reset word count after reaching the limit
            } else {
                result.append(" "); // Add space between words
            }
        }

        // Trim extra space at the end and append a new line at the end of the result
        return result.toString().trim() + System.lineSeparator();
    }
}
