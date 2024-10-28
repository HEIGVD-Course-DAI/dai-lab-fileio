package ch.heig.dai.lab.fileio.Romain720;

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
        return source.replaceAll("Chuck Norris",newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.
        String[] words=source.split(" ");
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {

            capitalized.append(word.substring(0, 1).toUpperCase());
            capitalized.append(word.substring(1));
            capitalized.append(" ");

        }
        return capitalized.toString().trim();

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

        String[] words = source.split(" ");
        StringBuilder result = new StringBuilder();

        int wordCount = 0;
        int lineCount = 1;

        result.append(lineCount).append(". ");

        for (String word : words) {

            if (wordCount == numWordsPerLine) {

                wordCount = 0;
                lineCount++;
                result.append("\n").append(lineCount).append(". ");

            }
            else if (wordCount > 0) {

                result.append(" ");

            }

            result.append(word);
            wordCount++;

        }

        if (result.charAt(result.length() - 1) == ' ')
            result.deleteCharAt(result.length() - 1);


        return result.toString().trim() + "\n";


    }
}   