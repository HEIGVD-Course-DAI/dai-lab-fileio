package ch.heig.dai.lab.fileio.FehlmannDy;

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
        while (source.contains("Chuck Norris")){
            source = source.replace("Chuck Norris", this.newName);
        }
        return source;
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.
        String[] words = source.split(" ");
        for (int i = 1; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }
        source = String.join(" ", words);
        return source;
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
        source = "";
        int ligne = 1;

        for (int i = 0; i < words.length; i++) {
            if(i%this.numWordsPerLine == 0){
                if(i!=0){
                    source += "\n";
                }
                source += ligne +".";
                ++ligne;
            }
            source += " "+ words[i];
        }
        source +="\n";
        return source;
    }
}   