package classes.dictionary;

import classes.LangDictionary;

public class DigitDict extends LangDictionary {
    private final String fileName = "src\\resourses\\DictDigit.txt";
    private final String REGEX = "[0-9]{5}";

    @Override
    public String getFile() {
        return fileName;
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
