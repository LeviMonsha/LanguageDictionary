package classes.exception;

public class ChooseWordRegexException extends Exception {
    private String word;
    public String getNumber(){return word;}
    public ChooseWordRegexException(String message, String word) {
        super(message);
        this.word = word;
    }
}
