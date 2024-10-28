package ch.heig.dai.lab.fileio.aminekhelfi;

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
        //utilisation de String.replace fonction de la classe string

        String result=source.replace("Chuck Norris",newName);
        return result;
    }

    /**
     * Capitalize the first letter of each word in the string.
     * @param source the string to transform
     * @return the transformed string
     */
    public String capitalizeWords(String source) {
        // TODO: Implement the method body here.
        String[] words=source.split(" ");
        StringBuilder sb = new StringBuilder(); //mis la chaine de caractère dans une BuildString car il est mutable si string ça serais impossbile

        for (String word : words) { //pour chaque mot du tableau words
            if(word.length()>0) //vérifier si le mots contient au moins une lettre
            {
                sb.append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1)) //ajout reste du mot
                        .append(" "); //ajout espace à la fin
            }
        }
        return sb.toString().trim(); //supprimer les espaces en trop qui sont à la fin.

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
        String[] words=source.split(" ");//récupère la liste de mot dans le string
        StringBuilder sb = new StringBuilder();
        int line=1;//numero de la ligne que je fais afficher dans le fichier

        for(int i=0;i<words.length;i++)
        {
            if(i%numWordsPerLine==0)//si on a atteinds le nombre de mots max par ligne
            {
                if(i!=0) //vérifier que c'est pas le premier mot (si y a espace avant le premier mots de la ligne pas c'est pas logique)
                {
                    sb.append("\n");

                }
                sb.append(line).append(". ");
                line++;
            }
            sb.append(words[i]);
            if((i+1)%numWordsPerLine!=0 && !words[i].equals(words[words.length-1])) //vérifer que c'est pas le dernier mot de la ligne, ni le dernier mots de la liste
            {
                sb.append(" ");
            }

        }
        sb.append("\n");
        return sb.toString();
    }
}   