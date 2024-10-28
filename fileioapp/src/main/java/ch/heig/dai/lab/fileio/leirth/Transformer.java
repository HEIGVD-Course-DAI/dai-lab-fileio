package ch.heig.dai.lab.fileio.leirth;

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
        return (source.contains("Chuck Norris")) ? source.replace("Chuck Norris", newName) : "Ce texte ne contient pas le nom de Chuck N.";
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        if (!source.isEmpty()) {
            String[] words = source.split(" ");
            for (int i = 0; i < words.length; ++i) {
                words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
            }

            return String.join(" ", words);
        }

        return source;
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        String[] words = source.split(" ");
        StringBuilder result = new StringBuilder();

        int wordCount = 0;
        int lineNumber = 1;
        for (String word : words) {
            if (wordCount == 0) {
                result.append(lineNumber).append(". ");
                lineNumber++;
            }

            result.append(word);
            wordCount++;

            if (wordCount >= numWordsPerLine) {
                result.append("\n");
                wordCount = 0;
            } else if (!word.equals(words[words.length - 1])) {
                result.append(" ");
            }
        }

        return result.append("\n").toString();
    }
}   