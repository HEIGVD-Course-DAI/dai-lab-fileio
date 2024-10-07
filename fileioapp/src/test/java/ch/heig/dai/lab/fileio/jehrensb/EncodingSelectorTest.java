// TODO change this to your own package instead of jehrensb ***
package ch.heig.dai.lab.fileio.jehrensb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class EncodingSelectorTest {

    private final EncodingSelector selector = new EncodingSelector();

    @Test
    @Disabled
    public void encodingTest() {
        assertEquals (StandardCharsets.UTF_8, selector.getEncoding(new File("file1.utf8")));
        assertEquals (StandardCharsets.US_ASCII, selector.getEncoding(new File("file1.txt")));
        assertEquals (StandardCharsets.UTF_16BE, selector.getEncoding(new File("file1.utf16be")));
        assertEquals (StandardCharsets.UTF_16LE, selector.getEncoding(new File("file1.utf16le")));
    }

    @Test
    @Disabled
    public void nullTest() {
        assertEquals (null, selector.getEncoding(new File("file1.utf")));
        assertEquals (null, selector.getEncoding(new File("file1")));
    }

    @Test
    @Disabled
    public void dotsTests() {
        assertEquals (StandardCharsets.UTF_8, selector.getEncoding(new File("file1.txt.utf8")));
        assertEquals (StandardCharsets.US_ASCII, selector.getEncoding(new File("file1.utf8.txt")));
    }
}