package ch.heig.dai.lab.fileio.nadi3;

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
        String[] words = source.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            sb.append(word);
            sb.append(' ');
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        String[] words = source.split(" ");
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int line = 1;
        for (String word : words) {
            if(index % numWordsPerLine == 0) {
                sb.append('\n');
                sb.append(line);
                sb.append(". ");
                ++line;
            } else {
                sb.append(' ');
            }
            sb.append(word);
            ++index;
        }
        sb.append('\n');
        return sb.substring(1, sb.length());
    }
}   