package classes.dictionary;

import classes.LangDictionary;

public class LatinDict extends LangDictionary {
    private final String fileName = "src\\resourses\\DictLatin.txt";
    private final String REGEX = "[a-zA-Z]{4}";

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
                "4 символа и эти символы - буквы латинского алфавита");
    }
}
