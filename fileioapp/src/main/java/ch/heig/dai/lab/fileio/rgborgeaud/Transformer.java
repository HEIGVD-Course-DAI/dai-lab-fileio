package ch.heig.dai.lab.fileio.rgborgeaud;

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

        String[] strArray = source.split("\\s+");

        for (int i = 0; i < strArray.length; ++i) {

            strArray[i] = strArray[i].substring(0, 1).toUpperCase() + strArray[i].substring(1);
        }

        return String.join(" ", strArray);
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        StringBuilder result = new StringBuilder();

        String[] strArray = source.split("\\s+");

        if (strArray.length > 0) {
            result.append("1.");
        }
        int nbLine = 2;

        for(int i = 0; i < strArray.length; ++i) {

            result.append(" ").append(strArray[i]);

            if ((i + 1) % numWordsPerLine == 0 && i + 1 != strArray.length) {
                result.append("\n");
                result.append(nbLine++).append(".");
            }
        }
        return result.append("\n").toString();
    }
}   