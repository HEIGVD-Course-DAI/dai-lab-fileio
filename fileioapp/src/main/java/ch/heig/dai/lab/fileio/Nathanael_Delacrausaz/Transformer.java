package ch.heig.dai.lab.fileio.Nathanael_Delacrausaz;

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
        String[] words = source.split("\\s+");  
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                           .append(word.substring(1).toLowerCase()).append(" ");
            }
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
        String[] words = source.split("\\s+");
        StringBuilder result = new StringBuilder();
        int wordCount = 0;
        int lineNumber = 1;
    
        result.append(lineNumber).append(". ");

        for (int i = 0; i < words.length; i++) {
            result.append(words[i]);
            wordCount++;
            
            // If it's not the last word in the line, add a space
            if (wordCount < numWordsPerLine && i < words.length - 1) {
                result.append(" ");
            }

            if (wordCount == numWordsPerLine && i < words.length - 1) {
                wordCount = 0;
                result.append("\n").append(++lineNumber).append(". ");
            }
        }
        result.append("\n");
    
        return result.toString();
    }
    
}
