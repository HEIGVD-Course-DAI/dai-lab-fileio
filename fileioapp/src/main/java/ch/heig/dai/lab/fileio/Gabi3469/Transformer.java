package ch.heig.dai.lab.fileio.Gabi3469;

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
        return source.replaceAll("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.
        String[] words = source.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            String firstLetter = word.substring(0, 1).toUpperCase();
            String restOfWord = word.substring(1);
            sb.append(firstLetter).append(restOfWord).append(' ');
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
        // TODO: Implement the method body here.
        // Use the StringBuilder class to build the result string.
        String[] words = source.split("\\s+");

        StringBuilder sb = new StringBuilder();

        int line = 1;
        int wordCount = 0;

        for (String word : words) {
            if (wordCount == 0) {
                sb.append(line).append(". ");
            }
            sb.append(word);
            wordCount++;
            if (wordCount == numWordsPerLine || word == words[words.length - 1]) {
                sb.append("\n");
                line++;
                wordCount = 0;
            }else{
                sb.append(" ");
            }
        }
        
        return sb.toString();
    }
}   