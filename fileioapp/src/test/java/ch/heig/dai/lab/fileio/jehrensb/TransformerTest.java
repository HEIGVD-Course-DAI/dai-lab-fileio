// TODO change this to your own package instead of jehrensb ***
package ch.heig.dai.lab.fileio.jehrensb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransformerTest {
    private final String newName = "Jean-Claude Van Damme";
    public final int numWordsPerLine = 3;

    private final Transformer transformer = new Transformer(newName, numWordsPerLine);
        private String testString = "Chuck Norris s’est fait flasher à 280km/h sur l’autoroute. A pied.";

    @Test
    @Disabled
    public void replaceChuckTest() {
        String expected = "Jean-Claude Van Damme s’est fait flasher à 280km/h sur l’autoroute. A pied.";
        assertEquals(expected, transformer.replaceChuck(testString));
    }

    @Test
    @Disabled
    public void noReplaceChuckTest() {
        String testString = "Ce texte ne contient pas le nom de Chuck N.";
        assertEquals(testString, transformer.replaceChuck(testString));
    }

    @Test
    @Disabled
    public void capitalizeWordsTest() {
        String expected = "Chuck Norris S’est Fait Flasher À 280km/h Sur L’autoroute. A Pied.";
        assertEquals(expected, transformer.capitalizeWords(testString));
    }

    @Test
    @Disabled
    public void wrapAndNumberLines() {
        String expected = "1. Chuck Norris s’est\n2. fait flasher à\n3. 280km/h sur l’autoroute.\n4. A pied.\n";
        assertEquals(expected, transformer.wrapAndNumberLines(testString));
    }
}