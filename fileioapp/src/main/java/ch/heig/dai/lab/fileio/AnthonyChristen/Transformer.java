package ch.heig.dai.lab.fileio.AnthonyChristen;

import java.util.ArrayList;
import java.util.List;

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
        return source.replaceAll("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        String[] words = source.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; ++i) {
            String word = words[i];

            result
                .append(word.substring(0, 1).toUpperCase())
                .append(word.substring(1));

            if (i != words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        String[] words = source.split(" ");
        StringBuilder result = new StringBuilder();
        List<String> line = new ArrayList<>();

        int lineNumber = 1;

        for (String word : words) {
            if (line.size() >= numWordsPerLine) {
                result.append(lineNumber).append(". ").append(String.join(" ", line)).append("\n");
                line.clear();
                lineNumber++;
            }

            line.add(word);
        }

        // Add the last line to the result if not empty
        if (!line.isEmpty()) {
            result.append(lineNumber).append(". ").append(String.join(" ", line)).append("\n");
        }

        return result.toString();
    }
}   