class LanguageRecognizerG {
    private String input;
    LanguageRecognizerG(String input){
        this.input = input;
    }
    void recursivePrintG(){
        if (recursiveRecogG(input)){
            System.out.println("Recursion: Word " + input + " IS a word of the G language");
        }else {
            System.out.println("Recursion: Word " + input + " is NOT a word of the G language");
        }
    }
    private boolean recursiveRecogG(String input){
        switch (input.length()){
            case 0:
                return true;
            case 1:
                return recogE(input.charAt(0));
            case 2:
                return ((recogV(input.charAt(0)) && recogE(input.charAt(1))) || (recogE(input.charAt(0)) && recogV(input.charAt(1))));
            default:
                return (recogE(input.charAt(0)) && recursiveRecogG(input.substring(1, input.length()-1)) && recogV(input.charAt(input.length()-1)));
        }
    }

    private boolean recogE(char input){
        return (input ==  '&' || input ==  '#');
    }

    private boolean recogV(char input){
        return (input ==  'W' || input ==  'A');
    }
}
