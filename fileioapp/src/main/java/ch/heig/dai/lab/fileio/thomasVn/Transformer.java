package ch.heig.dai.lab.fileio.thomasVn;

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

        //return the transformed string with the changed name if necessary
        return (source.contains("Chuck Norris")) ? source.replace("Chuck Norris", newName) : "Ce texte ne contient pas le nom de Chuck N.";
    }
    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.

        if (source.isEmpty()) return source;

        //Iterate tje string for changing the first letter of each word to uppercase
        String[] words = source.split(" ");
        for (int i = 0; i < words.length; ++i) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }
        return String.join(" ", words);
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        
        String[] words = source.split(" ");
        StringBuilder wrapped = new StringBuilder();

        int countWord = 0;
        int lineNumber = 1;

        // Iterate through the words array divided by spaces
        for (String word : words) {

            if (countWord == 0) {
                
                // insert the line number at the beginning of each line
                wrapped.append(lineNumber).append(". ");
                lineNumber++;
            }

            wrapped.append(word);
            countWord++;

            if (countWord >= this.numWordsPerLine) {
                wrapped.append("\n");
                countWord = 0;

            } else {
                wrapped.append(word.equals(words[words.length - 1]) ? '\n' : " ");
            }
        }

        return wrapped.toString();
    }   
}  