package ch.heig.dai.lab.fileio.bvernet;

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
        return source.replace("Chuck Norris", newName);
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {

        String[] words = source.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            result.append(word.substring(0,1).toUpperCase())
                  .append(word.substring(1))
                  .append(" ");
        }

        result.deleteCharAt(result.length()-1); // deletes the last space

        return result.toString();
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
        int nbLine = 1;
        int currentNbWords = 0;

        result.append(nbLine++ + ". ");

        for(String word : words){

            if(currentNbWords == numWordsPerLine){
                result.setLength(result.length() - 1); // deletes the last space of each line
                result.append("\n" + nbLine + ". ");
                currentNbWords = 0;
                nbLine++;
            }

            result.append(word + " ");
            currentNbWords++;
        }

        result.deleteCharAt(result.length()-1); // deletes the last space

        return result.append("\n").toString();
    }
}   