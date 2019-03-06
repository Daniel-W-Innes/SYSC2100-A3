import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Assignment3 {
    public static void main(String [] args) {
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            System.out.print("Enter the G-Language word to check:");
            input = keyboardReader.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        LanguageRecognizerG w1 = new LanguageRecognizerG(input);
        w1.recursivePrintG();
        try {
            System.out.print("Enter the infix expression to evaluate:");
            input = keyboardReader.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        InfixCalculator w2 = new InfixCalculator(input);
        w2.evaluateInfix();
    }
}
