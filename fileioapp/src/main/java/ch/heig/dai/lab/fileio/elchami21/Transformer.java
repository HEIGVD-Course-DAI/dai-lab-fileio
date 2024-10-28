package ch.heig.dai.lab.fileio.elchami21;

public class Transformer {

    private final String newName;
    private final int numWordsPerLine;

    public Transformer(String newName, int numWordsPerLine) {
        this.newName = newName;
        this.numWordsPerLine = numWordsPerLine;
    }

    public String replaceChuck(String source) {
        if (source == null || newName == null) {
            return "";
        }
        return source.replace("Chuck Norris", newName);
    }

    public String capitalizeWords(String source) {
        if (source == null || source.isEmpty()) {
            return "";
        }

        String[] words = source.split("(?<=\\s|^)(?=\\S)");  // Split keeping delimiters
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            if (!word.trim().isEmpty()) {
                if (word.startsWith("'") || word.startsWith(".")) {
                    result.append(word);
                } else {
                    result.append(word.substring(0, 1).toUpperCase())
                          .append(word.substring(1));
                }
            } else {
                result.append(word);
            }
        }
        
        return result.toString();
    }

    public String wrapAndNumberLines(String source) {
        if (source == null || source.isEmpty() || numWordsPerLine <= 0) {
            return "";
        }

        String[] words = source.split("\\s+");
        StringBuilder result = new StringBuilder();
        int lineNumber = 1;

        for (int i = 0; i < words.length; i += numWordsPerLine) {
            result.append(lineNumber++).append(". ");
            
            int endIndex = Math.min(i + numWordsPerLine, words.length);
            String line = String.join(" ", 
                java.util.Arrays.copyOfRange(words, i, endIndex));
            result.append(line).append("\n");
        }

        return result.toString();
    }
}