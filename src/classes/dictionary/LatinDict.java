package classes.dictionary;

import classes.RWManager;
import classes.exception.ChooseFileDictonaryException;

import java.io.IOException;

public class LatinDict extends RWManager {
//    private final String fileName = "src\\resourses\\DictLatin.txt";
    private final String REGEX = "[a-zA-Z]{4}";

    public LatinDict(String filePath) throws ChooseFileDictonaryException, IOException {
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
                "4 символа и эти символы - буквы латинского алфавита");
    }
}
