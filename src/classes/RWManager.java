package classes;

import classes.exception.ChooseFileDictonaryException;
import classes.exception.ChooseWordRegexException;

import java.io.*;
import java.security.KeyException;
import java.util.*;

public abstract class RWManager {
    private String fileName;
    private HashMap<String, String> dictionary;

    public RWManager(String fileName) throws IOException, ChooseFileDictonaryException {
        dictionary = new HashMap<>();
        fileName += ".txt";
        this.fileName = fileName;

        File file = new File(fileName);
        if(!file.createNewFile()) {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String regex = reader.readLine();
            if (!regex.strip().equals(getRegex())){
                throw new ChooseFileDictonaryException(
                        String.format("Вы выбрали файл, который не соответсвует выбранному словарю %s", getRegex()),
                        fileName
                );
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split(" ");
                dictionary.put(keyValue[0], keyValue[1]);
            }

        } else {
            System.out.printf("Такого файла не существует\n" +
                            "Создали новый путь к файлу { %s }\n",
                    fileName
            );
            String[] arr = {getRegex() + "\n"};
            writeToFile(arr);
        }
    }

    public void addNewWord(String wordEng, String wordRus) throws ChooseWordRegexException {
        if(isCorrectWord(wordEng)) {
            dictionary.put(wordEng, wordRus);
            writeToFile(getKeyValuesOfDict());
        } else {
            printInfoFormat();
            throw new ChooseWordRegexException(
                    "Слово не удовлетворяет условию",
                    wordEng
            );
        }
    }

    public boolean deleteWord(String wordEng) throws Exception {
        if(dictionary.containsKey(wordEng)) {
            dictionary.remove(wordEng);
            writeToFile(getKeyValuesOfDict());
            return true;
        }
        throw new KeyException("Ключ " + wordEng + " отсутствует");
    }

    public String searchWord(String wordEng) throws Exception {
        if(dictionary.containsKey(wordEng))
            return dictionary.get(wordEng);
        throw new KeyException("Ключ " + wordEng + " отсутствует");
    }

    private String[] getKeyValuesOfDict() {
        String[] lines = new String[dictionary.size()+1];
        List<String> keys = new ArrayList<>(dictionary.keySet());
        lines[0] = getRegex() + "\n";
        for(int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = dictionary.get(key);
            lines[i + 1] = key + " " + value + "\n";
        }
        return lines;
    }

    public void writeToFile(String[] lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public String readAllDict() {
        StringBuilder text = new StringBuilder();
        for(Map.Entry<String, String> entry : dictionary.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            text.append("\n").append(key).append(" : ").append(value).append("\n");
        }
        return text.toString();
    }

    public abstract boolean isCorrectWord(String s);

    public abstract String getRegex();
    public abstract void printInfoFormat();

//    public void ReadAllDict() {
//        if (dictionary.isEmpty()) {
//            System.out.println("Словарь пустой");
//            return;
//        }
//        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//
//            System.out.println(key + " : " + value);
//        }
//    }
//
//    public void SearchWord(String word) {
//        if (dictionary.containsKey(word)) {
//            System.out.println(word + " : " + dictionary.get(word));
//        }
//        else if (dictionary.containsValue(word)) {
//            String key = FindKey(word);
//            System.out.println(word + " : " + key);
//        } else {
//            System.out.println("Слово " + word + " не найдено.");
//        }
//    }

//    private String FindKey(String word) {
//        String key = null;
//        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
//            if (entry.getValue().equals(word)) {
//                key = entry.getKey();
//                break;
//            }
//        }
//        return key;
//    }
//
//    public void AddNewWord(String key, String value) {
//        if (key.matches(getRegex())) {
//            String line = key + " - " + value;
//            dictionary.put(key, value);
//            rwManager.Writer(line);
//        }
//        else printInfoFormat();
//    }
//
//    public void DeleteWord(String word) {
//        String key;
//        if (dictionary.containsKey(word)) {
//            key = word;
//        } else if (dictionary.containsValue(word)) {
//            key = FindKey(word);
//        } else {
//            System.out.println("Слово " + word + " не найдено.");
//            return;
//        }
//        String line = key + " - " + dictionary.get(key);
//        rwManager.DeleteLine(line);
//        dictionary.remove(key);
//    }


}
