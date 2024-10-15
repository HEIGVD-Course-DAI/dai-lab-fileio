package ch.heig.dai.lab.fileio.svelva;

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
        String[] tokens = source.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].substring(0, 1).toUpperCase() + tokens[i].substring(1);
        }

        return String.join(" ", tokens);
    }

    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
        // Use the StringBuilder class to build the result string.
        String[] tokens = source.split(" ");
        String output = "";
        String buildingLine = "";
        int counter = 0;
        int lineCount = 1;
        for (String token : tokens) {
            if (counter == numWordsPerLine) {
                buildingLine += "\n";
                output += String.valueOf(lineCount++) + "." + buildingLine;

                buildingLine = "";
                counter = 0;
            }
            counter++;
            buildingLine += " " + token;
        }

        if (!buildingLine.isEmpty()) {
            output += String.valueOf(lineCount) + "." + buildingLine + "\n";
        }

        return output;
    }
}   