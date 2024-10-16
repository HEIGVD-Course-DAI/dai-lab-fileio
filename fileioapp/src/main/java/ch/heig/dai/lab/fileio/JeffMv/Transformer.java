package ch.heig.dai.lab.fileio.JeffMv;

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
        return source.replaceAll("Chuck Norris", this.newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        String[] words = source.split(" ");
        for (int i=0; i < words.length; ++i) {
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
        // Use the StringBuilder class to build the result string.
        String lineSep = System.lineSeparator();
        // String lineSep = "\r\n"; // System.lineSeparator();

        var words = source.split(" ");
        int wordsInLine = 0;
        int nbLines = 1;

        StringBuilder result = new StringBuilder(nbLines + ". ");
        for (int i=0; i < words.length; ++i) {
            if (wordsInLine >= this.numWordsPerLine) {
                result.append(lineSep);
                wordsInLine = 0;
                nbLines++;
                result.append(nbLines);
                result.append(". ");
            }
            result.append(words[i]);

            if (wordsInLine < this.numWordsPerLine - 1 && i < words.length - 1) {
                result.append(" ");
            }
            ++wordsInLine;
        }
        result.append(lineSep);
        return result.toString();
    }
}   