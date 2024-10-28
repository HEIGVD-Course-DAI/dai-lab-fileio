package ch.heig.dai.lab.fileio.mang0r;

import java.util.Arrays;
import java.util.stream.Collectors;

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
        return Arrays.stream(source.split(" "))
            .map((String s) ->{ return Character.toUpperCase(s.charAt(0)) + s.substring(1); })
            .collect(Collectors.joining(" "));
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
        var sb = new StringBuilder();

        for (String l : source.split("\\r?\\n")) {
            int i = 0;
            for (String w : l.split(" ")) {
                if (i % numWordsPerLine == 0) {
                    sb.append(i / numWordsPerLine + 1).append(". ");
                }
                sb.append(w).append((++i % numWordsPerLine == 0) ? "\n" : " ");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
        return sb.toString();
    }
}   