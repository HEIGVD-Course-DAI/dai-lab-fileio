package ch.heig.dai.lab.fileio.TheoPilet;

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
        String oldName = "Chuck Norris";
        return source.replaceAll(oldName,newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        String[] words = source.split(" ");

        StringBuilder transformedString = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                String firstLetter = word.substring(0, 1).toUpperCase();
                transformedString.append(firstLetter).append(word.substring(1)).append(" ");
            }
        }
        return transformedString.toString().trim();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        String[] words = source.split(" ");
        StringBuilder createLines = new StringBuilder();
        int lineNumber = 1;

        for (int i = 0; i < words.length; i++) {
            if (i % numWordsPerLine == 0) {
                createLines.append(lineNumber++).append(". ");
            }

            createLines.append(words[i]);

            if (i % numWordsPerLine != numWordsPerLine - 1 && i != words.length - 1) {
                createLines.append(" ");
            } else {
                createLines.append("\n");
            }
        }
        return createLines.toString();
    }
}   