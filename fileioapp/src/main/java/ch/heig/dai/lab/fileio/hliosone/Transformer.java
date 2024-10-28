package ch.heig.dai.lab.fileio.hliosone;

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
        if(source != null && !source.isEmpty()){
            // replace all occurences of "Chuck Norris" with newName
            return source.replaceAll("Chuck Norris", newName);
        }
        return "";
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.
        // capitalize the first letter of each word in the string
        if(source != null && !source.isEmpty()){
            StringBuilder sbuilder = new StringBuilder();
            String[] words = source.split(" ");

            for (String word : words){
                if (!word.isEmpty()){
                    sbuilder.append(Character.toUpperCase(word.charAt(0)))
                            .append(word.substring(1));
                }
                if (!word.equals(words[words.length - 1])){
                    sbuilder.append(" ");
                }
            }

            return sbuilder.toString();
        }

        return "";
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
            System.out.println("The number of words per line must be greater than 0.");
            return "";
        }

        StringBuilder sb = new StringBuilder();
        String[] words = source.trim().split("\\s+");
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
