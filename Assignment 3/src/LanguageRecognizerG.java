class LanguageRecognizerG {
    private String input;

    /**
     *Construct LanguageRecognizerG and instance input
     * @param input The string to test against language G
     */
    LanguageRecognizerG(String input) {
        this.input = input;
    }

    /**
     *Run and print the results of recursiveRecogG.
     */
    void recursivePrintG() {
        if (recursiveRecogG(input)) {
            System.out.println("Recursion: Word “" + input + "” IS a word of the G language");
        } else {
            System.out.println("Recursion: Word “" + input + "” is NOT a word of the G language");
        }
    }

    private boolean recursiveRecogG(String input) {
        switch (input.length()) {
            case 0:
                //language case "empty string"
                return true;
            case 1:
                //language case "<E>"
                return recogE(input.charAt(0));
            case 2:
                //language case "<V> <E>" and "<E> <empty string> <V>"
                return ((recogV(input.charAt(0)) && recogE(input.charAt(1))) || (recogE(input.charAt(0)) && recogV(input.charAt(1))));
            default:
                //language case "<E> <G> <V>"
                return (recogE(input.charAt(0)) && recursiveRecogG(input.substring(1, input.length() - 1)) && recogV(input.charAt(input.length() - 1)));
        }
    }

    private boolean recogE(char input) {
        //if input falls under "<E>"
        return (input == '&' || input == '#');
    }

    private boolean recogV(char input) {
        //if input falls under "<V>"
        return (input == 'W' || input == 'A');
    }
}
