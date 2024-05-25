package classes.exception;

public class ChooseFileDictonaryException extends Exception {
    private String word;
    public String getNumber(){ return word; }
    public ChooseFileDictonaryException(String message, String word) {
        super(message);
        this.word = word;
    }
}
