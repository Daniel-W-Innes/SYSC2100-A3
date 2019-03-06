import java.util.Map;

class InfixCalculator {
    private String input;
    private StackListBased<Integer> stack;

    InfixCalculator(String input) {
        this.input = input;
        stack = new StackListBased<>();
    }

    private static int runOperator(String op, int num1, int num2) {
        switch (op) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0;
        }
    }

    private String infixToPostfix(String infix) {
        Map<String, Integer> precedence = Map.of("+", 1,"-",1,"*",2,"/",2);
        StringBuilder Postfix = new StringBuilder();
        StackListBased<String> operatorStack = new StackListBased<>();
        String[] strs = infix.replaceAll("\\s+" , "").split("(?=[-+*/()])|(?<=[-+*/()])");
        for (String str : strs) {
            if (str.matches("\\d+")) {
                Postfix.append(str);
                Postfix.append(" ");
            } else if (str.equals("(")) {
                operatorStack.push(str);
            } else if (str.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    Postfix.append(operatorStack.pop());
                    Postfix.append(" ");
                }
                operatorStack.pop();
            } else if (str.matches("[-+*/]")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(") && (precedence.get(str) <= precedence.get(operatorStack.peek()))) {
                    Postfix.append(operatorStack.pop());
                    Postfix.append(" ");
                }
                operatorStack.push(str);
            }
        }
        while (!operatorStack.isEmpty()) {
            Postfix.append(operatorStack.pop());
            Postfix.append(" ");
        }
        return Postfix.toString();
    }

    private int getPostfix(String postFix) {
        for (String str : postFix.split(" ")) {
            if (str.matches("\\d+")) {
                stack.push(Integer.parseInt(str));
            } else {
                int num = stack.pop();
                stack.push(runOperator(str, stack.pop(), num));
            }
        }
        return stack.pop();
    }

    void evaluateInfix() {
        System.out.println("infix: " + input);
        input = infixToPostfix(input);
        System.out.println("postfix: " + input);
        System.out.println("result: " + getPostfix(input));
    }
}
