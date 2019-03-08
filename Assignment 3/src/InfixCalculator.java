import java.util.Map;

class InfixCalculator {
    private String input;
    private StackListBased<Integer> stack;

    /**
     *Construct InfixCalculator and instance input
     * @param input The postFix convert to infix and solve
     */
    InfixCalculator(String input) {
        this.input = input;
        stack = new StackListBased<>();
    }

    private static int runOperator(String operator, int num2, int num1) {
        // if operator is a provided operator (-+*/) then run it as a math operator on num1 and num2
        switch (operator) {
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
        // Map to convert Operation to precedence number
        Map<String, Integer> precedence = Map.of("+", 1, "-", 1, "*", 2, "/", 2);
        StringBuilder postFix = new StringBuilder();
        StackListBased<String> operatorStack = new StackListBased<>();
        //Remove white space and parse String to list strings (tokenize)
        String[] tokenize = infix.replaceAll("\\s+", "").split("(?=[-+*/()])|(?<=[-+*/()])");
        for (String token : tokenize) {
            //If token is a number append it to prefix along with a space for parsing
            if (token.matches("\\d+")) {
                postFix.append(token);
                postFix.append(" ");
                //If the token is an opening bracket push to the stack.
            } else if (token.equals("(")) {
                operatorStack.push(token);
                //If the token is a closing bracket pop from the stack in a append to the prefix along with a space for parsing in till Opening bracket is found
            } else if (token.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    postFix.append(operatorStack.pop());
                    postFix.append(" ");
                }
                operatorStack.pop();
                //If token is operator Sort by precedence and append appropriate operators to postFix according to lecture 10 slides
            } else if (token.matches("[-+*/]")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(") && (precedence.get(token) <= precedence.get(operatorStack.peek()))) {
                    postFix.append(operatorStack.pop());
                    postFix.append(" ");
                }
                operatorStack.push(token);
            }
        }
        //Clear operator stack into postfix
        while (!operatorStack.isEmpty()) {
            postFix.append(operatorStack.pop());
            postFix.append(" ");
        }
        postFix.deleteCharAt(postFix.lastIndexOf(" "));
        return postFix.toString();
    }

    private int getPostfix(String postFix) {
        //Purse postFix expression by Preplaced spaces
        for (String token : postFix.split(" ")) {
            //If token is a number push to the stack else run the token on the first two number from the stack
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            } else {
                stack.push(runOperator(token, stack.pop(), stack.pop()));
            }
        }
        return stack.pop();
    }

    /**
     *Run and print the results of infixToPostfix and getPostfix.
     */
    void evaluateInfix() {
        System.out.println("infix: " + input);
        input = infixToPostfix(input);
        System.out.println("postfix: " + input);
        System.out.println("result: " + getPostfix(input));
    }
}
