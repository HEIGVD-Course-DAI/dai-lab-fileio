package ch.heig.dai.lab.fileio.EscapedGibbon;

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
        String result = source.replaceAll(oldName,newName);
        return result;
    }

     /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        
      StringBuilder result = new StringBuilder();
      String[] words = source.split(" "); // Split the string into words.
      
      for (String word : words) {
          if (word.length() > 0) {
              // Capitalize the first letter and append the rest.
              result.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1)).append(" ");
          }
      }
      return result.toString().trim();
    }
    /**
     * Wrap the text so that there are at most numWordsPerLine words per line.
     * Number the lines starting at 1.
     * @param source the string to transform
     * @return the transformed string
     */
    public String wrapAndNumberLines(String source) {
      StringBuilder str = new StringBuilder();
      String[] words = source.split(" ");
      int lineIndex = 1;
      str.append(lineIndex).append(". ");
      for(int i = 0; i < words.length;i++){
        if((i+1)%numWordsPerLine != 0){
          str.append(words[i]).append(" ");
        }else{
          str.append(words[i]).append(System.lineSeparator());
          str.append(++lineIndex).append(". ");
        }
      }
      String result = str.toString().trim();
      if(words.length%numWordsPerLine != 0){
        result+=System.lineSeparator();
      } 
        
        return result;
    }
}   