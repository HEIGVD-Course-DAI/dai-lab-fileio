package ch.heig.dai.lab.fileio.LovIMarK;

import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        if(source.contains("Chuck Norris"))
        {
            return source.replace("Chuck Norris",newName);
        }
        return source;
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {

        return Stream.of(source.split(" "))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));

    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        String[] words = source.split(" ");

        // StringBuilder to build the result
        StringBuilder wrappedText = new StringBuilder();

        int wordCount = 0;
        int lineNumber = 1;

        // Append the first line number
        wrappedText.append(lineNumber).append(". ");

        // Loop through the array of words
        for (String word : words) {
            if (wordCount == numWordsPerLine) {
                // If we reach the word limit per line, append a new line and line number
                wrappedText.append("\n");
                lineNumber++;
                wrappedText.append(lineNumber).append(". ");
                wordCount = 0;
            }

            // Append the word to the current line
            wrappedText.append(word);
            wordCount++;

            // Only append a space if this is not the last word in the list
            if (!(wordCount == numWordsPerLine || word.equals(words[words.length - 1]))) {
                wrappedText.append(" ");
            }
        }

        // Ensure the final string ends with a newline
        wrappedText.append("\n");

        // Convert StringBuilder to string and return the result
        return wrappedText.toString();
    }
}   