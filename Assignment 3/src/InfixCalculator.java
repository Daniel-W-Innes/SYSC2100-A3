import java.util.Map;

class InfixCalculator {
    private String input;
    private StackListBased<Integer> stack;

    InfixCalculator(String input) {
        this.input = input;
        stack = new StackListBased<>();
    }

    private static int runOperator(String op, int num2, int num1) {
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
        Map<String, Integer> precedence = Map.of("+", 1, "-", 1, "*", 2, "/", 2);
        StringBuilder postFix = new StringBuilder();
        StackListBased<String> operatorStack = new StackListBased<>();
        String[] strs = infix.replaceAll("\\s+", "").split("(?=[-+*/()])|(?<=[-+*/()])");
        for (String str : strs) {
            if (str.matches("\\d+")) {
                postFix.append(str);
                postFix.append(" ");
            } else if (str.equals("(")) {
                operatorStack.push(str);
            } else if (str.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    postFix.append(operatorStack.pop());
                    postFix.append(" ");
                }
                operatorStack.pop();
            } else if (str.matches("[-+*/]")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(") && (precedence.get(str) <= precedence.get(operatorStack.peek()))) {
                    postFix.append(operatorStack.pop());
                    postFix.append(" ");
                }
                operatorStack.push(str);
            }
        }
        while (!operatorStack.isEmpty()) {
            postFix.append(operatorStack.pop());
            postFix.append(" ");
        }
        postFix.deleteCharAt(postFix.lastIndexOf(" "));
        return postFix.toString();
    }

    private int getPostfix(String postFix) {
        for (String str : postFix.split(" ")) {
            if (str.matches("\\d+")) {
                stack.push(Integer.parseInt(str));
            } else {
                stack.push(runOperator(str, stack.pop(), stack.pop()));
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
