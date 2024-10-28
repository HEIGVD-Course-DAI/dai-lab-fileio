package ch.heig.dai.lab.fileio.Jerome_Riedo;

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
        return "";
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.
        return "";
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
<<<<<<< HEAD
        return "";
=======
        var output = new StringBuilder();
        int i = 0;
        int lineNumber = 1; // numéro de ligne (commence à 1

        String[] words = source.split(" ");
        output.append(lineNumber++).append(". ");

        for (String word : words) {
            if (i == numWordsPerLine) {
                output.setLength(output.length() - 1);
                output.append("\n").append(lineNumber++).append(". ");
                i = 0;
            }
            output.append(word).append(" ");
            i++;
        }
        output.setLength(output.length() - 1); // suppression du dernier " "
        return output.append("\n").toString();
>>>>>>> main
    }
}   
