package ch.heig.dai.lab.fileio.axelrochat;

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
        return source.replace("Chuck Norris", newName);
    }
    

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        if (source == null || source.isEmpty()) {
            return source;
        }
        
        String[] words = source.split("\\s+");
        StringBuilder capitalized = new StringBuilder();
    
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                           .append(word.substring(1).toLowerCase())
                           .append(" ");
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
        if (source == null || source.isEmpty()) {
            return source;
        }
    
        String[] words = source.split(" ");
        StringBuilder result = new StringBuilder();
        int wordCount = 0;
        int lineNumber = 1;
    
        result.append(lineNumber).append(". ");
    
        for (String word : words) {
            if (wordCount == numWordsPerLine) {
                wordCount = 0;
                lineNumber++;
                result.append(System.lineSeparator())
                      .append(lineNumber).append(". ");
            }
            
            result.append(word).append(" ");
            wordCount++;
        }
    
        return result.toString().trim() + "\n";
    }
    
}   