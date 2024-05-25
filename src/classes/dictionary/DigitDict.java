package classes.dictionary;

import classes.RWManager;
import classes.exception.ChooseFileDictonaryException;

import java.io.IOException;

public class DigitDict extends RWManager {
//    private final String fileName = "src\\resourses\\DictDigit.txt";
    private final String REGEX = "[0-9]{5}";

    public DigitDict(String filePath) throws ChooseFileDictonaryException, IOException {
        super(filePath);
    }

    @Override
    public boolean isCorrectWord(String s) {
        return s.matches(REGEX);
    }

    @Override
    public String getRegex() {
        return REGEX;
    }

    @Override
    public void printInfoFormat() {
        System.out.println("Формат слова в данном словаре:\n" +
                "5 символов и эти символы - цифры");
    }
}
