package ch.heig.dai.lab.fileio.Fullann;

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
        // Replace all instances of "Chuck Norris" with the provided newName
        return source.replace("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // Split the string into words
        String[] words = source.split("\\s+");
        StringBuilder capitalized = new StringBuilder();

        // Iterate over each word and capitalize the first letter
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase()).append(" ");
            }
        }
        // Remove the last extra space and return the result
        return capitalized.toString().trim();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        // Split the source text into individual words
        String[] words = source.split("\\s+");
        StringBuilder wrapped = new StringBuilder();

        int wordCount = 0;
        int lineNumber = 1;

        wrapped.append(lineNumber++).append(". ");  // Start with line 1

        // Iterate over each word and add them to the output
        for (String word : words) {
            wrapped.append(word).append(" ");
            wordCount++;

            // If the number of words per line is reached, wrap to the next line
            if (wordCount == numWordsPerLine) {
                // Trim the extra space at the end of the line before moving to the next one
                wrapped.setLength(wrapped.length() - 1);
                wrapped.append(System.lineSeparator())
                        .append(lineNumber++).append(". ");  // Start a new numbered line
                wordCount = 0;
            }
        }

        // If there are leftover words, end the last line cleanly
        if (wordCount > 0) {
            wrapped.setLength(wrapped.length() - 1);  // Remove the trailing space
        }

        // Append the final newline character
        wrapped.append(System.lineSeparator());

        return wrapped.toString();  // Return the result
    }
}
