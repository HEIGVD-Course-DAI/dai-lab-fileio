package ch.heig.dai.lab.fileio.ismaelgiovannetti;

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
        source = source.replaceAll("Chuck Norris", newName);
        return source;
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        source = source.toLowerCase();
        String[] words = source.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word.substring(0, 1).toUpperCase());
            sb.append(word.substring(1));
            sb.append(" ");
        }
        return sb.toString().trim();
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
        int line = 1;
        int wordCount = 0;
    
        sb.append(line).append(". ");
    
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            wordCount++;
    
            if (wordCount == this.numWordsPerLine) {
                sb.append("\n");
                line++;
                wordCount = 0; 
                if (i + 1 < words.length) {
                    sb.append(line).append(". ");
                }
            } else if (i + 1 < words.length) {
                sb.append(" ");
            }
        }
    
        sb.append("\n");
    
        return sb.toString();
    }
}
