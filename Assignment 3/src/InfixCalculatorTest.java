import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InfixCalculatorTest {
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
    public void evaluateInfix1() {
        InfixCalculator w2 = new InfixCalculator("(10 + 3 * 4 / 6)");
        w2.evaluateInfix();
        Assert.assertEquals("infix: (10 + 3 * 4 / 6)\r\n" +
                "postfix: 10 3 4 * 6 / +\r\n" +
                "result: 12\r\n", outContent.toString());
    }

    @Test
    public void evaluateInfix2() {
        InfixCalculator w2 = new InfixCalculator("12*3 - 4 + (18 / 6)");
        w2.evaluateInfix();
        Assert.assertEquals("infix: 12*3 - 4 + (18 / 6)\r\n" +
                "postfix: 12 3 * 4 - 18 6 / +\r\n" +
                "result: 35\r\n", outContent.toString());
    }

    @Test
    public void evaluateInfix3() {
        InfixCalculator w2 = new InfixCalculator("35 - 42* 17 /2 + 10");
        w2.evaluateInfix();
        Assert.assertEquals("infix: 35 - 42* 17 /2 + 10\r\n" +
                "postfix: 35 42 17 * 2 / - 10 +\r\n" +
                "result: -312\r\n", outContent.toString());
    }

    @Test
    public void evaluateInfix4() {
        InfixCalculator w2 = new InfixCalculator("3 * (4 + 5)");
        w2.evaluateInfix();
        Assert.assertEquals("infix: 3 * (4 + 5)\r\n" +
                "postfix: 3 4 5 + *\r\n" +
                "result: 27\r\n", outContent.toString());
    }

    @Test
    public void evaluateInfix5() {
        InfixCalculator w2 = new InfixCalculator("3 * ( 17 - (5+2))/(2+3)");
        w2.evaluateInfix();
        Assert.assertEquals("infix: 3 * ( 17 - (5+2))/(2+3)\r\n" +
                "postfix: 3 17 5 2 + - * 2 3 + /\r\n" +
                "result: 6\r\n", outContent.toString());
    }
}