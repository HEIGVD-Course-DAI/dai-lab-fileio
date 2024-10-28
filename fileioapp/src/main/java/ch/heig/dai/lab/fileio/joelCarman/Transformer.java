package ch.heig.dai.lab.fileio.joelCarman;

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
        if (source == null) {
            return null;
        }

        return source.replaceAll("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        
        if (source == null || source.isEmpty()) {
            return null;
        }

        String[] words = source.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(word.substring(0, 1).toUpperCase());
            result.append(word.substring(1));
            result.append(" ");
        }
        return result.toString().trim();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        // Use the StringBuilder class to build the result string.

        if (source == null || source.isEmpty() || numWordsPerLine <= 0) {
            return null;
        }

        String[] words = source.split(" ");

        StringBuilder result = new StringBuilder();
        int lineCount = 1;
        int wordCount = 0;

        for (String word : words) {
            if (wordCount == 0) {
                result.append(lineCount).append(". ");
                lineCount++;
            }
            result.append(word);
            wordCount++;
            
            if (wordCount >= numWordsPerLine) {
                result.append(System.lineSeparator());
                wordCount = 0;
                continue;
            }
            result.append(" ");
        }

        // Remove last space
        if (result.charAt(result.length() - 1) == ' ') {
            result.deleteCharAt(result.length() - 1);
        }
        
        return result.append(System.lineSeparator()).toString();
    }
}   
