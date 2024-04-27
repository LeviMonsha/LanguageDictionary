package classes;

import java.util.*;

public class LangDictionary {
    private final RWManager rwManager;
    private String fileName = "src\\resourses\\dict.txt";
    private Map<String, String> dictionary;

    public LangDictionary() {
        rwManager = new RWManager(fileName);
        List<String> list = rwManager.Reader();
        dictionary = ConvertToDict(list);
    }

    private Map<String, String> ConvertToDict(List<String> list) {
        Map<String, String> dict = new HashMap<>();
        for(String line : list) {
            String[] lnSplit = line.split(" - ");
            String key =  lnSplit[0], value = lnSplit[1];
            dict.put(key, value);
        }
        return dict;
    }

    public void ReadAllDict() {
        if (dictionary.isEmpty()) {
            System.out.println("Словарь пустой");
            return;
        }
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            System.out.println(key + " : " + value);
        }
    }

    public void SearchWord(String word) {
        if (dictionary.containsKey(word)) {
            System.out.println(word + " : " + dictionary.get(word));
        }
        else if (dictionary.containsValue(word)) {
            String key = FindKey(word);
            System.out.println(word + " : " + key);
        } else {
            System.out.println("Слово " + word + " не найдено.");
        }
    }

    private String FindKey(String word) {
        String key = null;
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            if (entry.getValue().equals(word)) {
                key = entry.getKey();
                break;
            }
        }
        return key;
    }

    public void AddNewWord(String key, String value) {
        String line = key + " - " + value;
        rwManager.Writer(line);
        dictionary.put(key, value);
    }

    public void DeleteWord(String word) {
        String key;
        if (dictionary.containsKey(word)) {
            key = word;
        }
        else if (dictionary.containsValue(word)) {
            key = FindKey(word);
        } else {
            System.out.println("Слово " + word + " не найдено.");
            return;
        }
        String line = key + " - " + dictionary.get(key);
        rwManager.DeleteLine(line);
        dictionary.remove(key);
    }
}
