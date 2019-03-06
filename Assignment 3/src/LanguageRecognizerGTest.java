import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LanguageRecognizerGTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void recursivePrintG1() {
        LanguageRecognizerG w1 = new LanguageRecognizerG("###");
        w1.recursivePrintG();
        Assert.assertEquals("Recursion: Word “###” is NOT a word of the G language\r\n", outContent.toString());
    }
    @Test
    public void recursivePrintG2() {
        LanguageRecognizerG w1 = new LanguageRecognizerG("#A");
        w1.recursivePrintG();
        Assert.assertEquals("Recursion: Word “#A” IS a word of the G language\r\n", outContent.toString());
    }
}