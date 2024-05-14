package classes;

import classes.dictionary.DigitDict;
import classes.dictionary.LatinDict;

import java.util.Scanner;

public class InputHandler {
    private static boolean blUpdate = true;
    private static LangDictionary dictionary;
    private static Scanner scanner = new Scanner(System.in);

    private static void PrintInfoTask() {
        System.out.println("~~~~  TASK  ~~~~");
        System.out.println("(-1) Поменять словарь");
        System.out.println("(1) Просмотр словаря");
        System.out.println("(2) Поиск слова");
        System.out.println("(3) Добавление слова");
        System.out.println("(4) Удаление слова");
        System.out.println("(27) Выход");
        System.out.println("~~~~~~~~~~~~~~~~");
    }
    private static void PrintInfoDict() {
        System.out.println("~~~~  DICTIONARY  ~~~~");
        System.out.println("(0) латиница - кириллица");
        System.out.println("(1) цифра - кириллица");
    }

    private static void selectDict() {
        PrintInfoDict();
        int value = inputValue();
        if (value == 0) dictionary = new LatinDict();
        else if (value == 1) dictionary = new DigitDict();
        else {
            System.out.println("Неверный ввод");
            selectDict();
        }
    }

    private static int inputValue() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        else {
            scanner.next();
            return inputValue();
        }
    }

    public static void update() {
        selectDict();

        while (blUpdate) {
            Task();
        }
    }

    private static void Task() {
        PrintInfoTask();
        String word;
        int keyHandler = inputValue();

        switch (keyHandler) {
            case 1 -> dictionary.ReadAllDict();
            case 2 -> {
                word = scanner.next();
                dictionary.SearchWord(word);
            }
            case 3 -> {
                System.out.println("Формат добавления слова:");
                System.out.println("<word> : <translateWord>");
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                    String line = scanner.nextLine();
                    if (line.contains(" : ")) {
                        String[] words = line.split(" : ");
                        String key = words[0], value = words[1];
                        dictionary.AddNewWord(key, value);
                    }
                    else System.out.println("Неверный формат для добавления слова");
                }
            }
            case 4 -> {
                word = scanner.next();
                dictionary.DeleteWord(word);
            }
            case -1 -> selectDict();
            case 27 -> blUpdate = false;
            default -> {
                System.out.println("Такой функции не существует");
            }
        }
    }
}
