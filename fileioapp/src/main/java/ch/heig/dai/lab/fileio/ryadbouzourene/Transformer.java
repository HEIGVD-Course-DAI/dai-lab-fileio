package ch.heig.dai.lab.fileio.ryadbouzourene;

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
        String s ;
        s = source.replaceAll("Chuck Norris", newName);

        return s;
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.
        if (source == null || source.isEmpty()) {
            return source;
        }
        char[] chaineSource = source.toCharArray() ;
        for(int i = 0 ; i< chaineSource.length ; ++i){
            if( Character.isLetter(chaineSource[i]) &&
            (i==0 || Character.isWhitespace(chaineSource[i-1]))){
                chaineSource[i]= Character.toUpperCase(chaineSource[i]);
            }
        }
        return new String(chaineSource);
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
        String [] words = source.split(" ");
        StringBuilder sb = new StringBuilder();

        int lineNumber = 1; 

        for (int i = 0; i < words.length; i++) {
            
            if (i % numWordsPerLine == 0) {
                if (i != 0) sb.append("\n"); 
                sb.append(lineNumber++).append(". ");
            }
            
            sb.append(words[i]);

            if(i%numWordsPerLine!=numWordsPerLine-1 && i < words.length -1){
                sb.append(" ");
            }

            if(i == words.length -1){
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}   