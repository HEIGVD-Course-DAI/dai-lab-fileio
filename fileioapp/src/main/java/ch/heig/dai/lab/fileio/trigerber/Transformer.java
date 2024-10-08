package ch.heig.dai.lab.fileio.trigerber;

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
        return source.replace("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        String[] result = source.split(" ");
        for (int i = 0; i < result.length; i++) {
            if (result[i].length() > 0) 
            result[i] = result[i].substring(0, 1).toUpperCase() + result[i].substring(1);
        }
        return String.join(" ", result);
    }    

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        String[] result = source.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (i % numWordsPerLine == 0) {
                if (i > 0) sb.append("\n");
                sb.append((i / numWordsPerLine) + 1).append( ". ");
            }
            else sb.append(" ");
            sb.append(result[i]);
        }
        return sb.append("\n").toString();
    }
}