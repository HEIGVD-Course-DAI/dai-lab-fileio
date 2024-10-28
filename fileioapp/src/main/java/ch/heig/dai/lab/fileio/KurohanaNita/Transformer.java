package ch.heig.dai.lab.fileio.KurohanaNita;

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
        if (source == null) return null;
        return source.replaceAll("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.
        if (source == null) return null;

        String[] words = source.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1));
            }
            if (!word.equals(words[words.length - 1])){
                result.append(" ");
            }
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
        // TODO: Implement the method body here.
        // Use the StringBuilder class to build the result string.
        if (source == null || source.isEmpty() || numWordsPerLine < 1) {
            System.out.println("Invalid input: source is null or empty, or numWordsPerLine is less than 1.");
            return "";
        }

        String[] words = source.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();

        int wordCount = 0;
        int lineCount = 1;

        sb.append(lineCount).append(". ");

        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            wordCount++;

            if (wordCount == numWordsPerLine && i < words.length - 1) {
                sb.append("\n").append(++lineCount).append(". ");
                wordCount = 0;
            } else if (i < words.length - 1) {
                sb.append(" ");
            }
        }

        if (wordCount > 0) {
            sb.append("\n");
        }

        return sb.toString();
    }
}   