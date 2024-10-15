package ch.heig.dai.lab.fileio._9r55hs;

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
        String[] splittedSource = source.split(" ");
        String cap, capitalizedContent = "";
        for(String word : splittedSource){
            cap = word.substring(0,1).toUpperCase();
            capitalizedContent += cap + word.substring(1) + " ";
        }
        return capitalizedContent.trim();
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        String[] splittedSource = source.split(" ");
        String wrappedContent = "";
        boolean newLine = true;

        for (int i = 0, line = 1; i < splittedSource.length; i++, line += newLine ? 1 : 0) {
            if(newLine)
                wrappedContent += "\n" + Integer.toString(line) + ". ";

            wrappedContent += splittedSource[i];
            newLine = (i + 1) % numWordsPerLine == 0;

            if(!newLine)
                wrappedContent += " ";
        }

        wrappedContent = wrappedContent.trim() + "\n";

        return wrappedContent;
    }
}   