package classes;

import classes.dictionary.DigitDict;
import classes.dictionary.LatinDict;

import java.util.Scanner;

public class InputHandler {
    private static boolean blUpdate = true;
    private static boolean isRunning = true;
    private static LangDictionary dictionary;
    private static Scanner scanner = new Scanner(System.in);

    private static void printInfoTask() {
        System.out.println("~~~~  TASK  ~~~~");
        System.out.println("(-1) Поменять словарь");
        System.out.println("(1) Просмотр словаря");
        System.out.println("(2) Поиск слова");
        System.out.println("(3) Добавление слова");
        System.out.println("(4) Удаление слова");
        System.out.println("(27) Выход");
        System.out.println("~~~~~~~~~~~~~~~~");
    }
    private static void printInfoDict() {
        System.out.println("~~~~  DICTIONARY  ~~~~");
        System.out.println("(0) латиница - кириллица");
        System.out.println("(1) цифра - кириллица");
    }

    private static void selectDict() {
        printInfoDict();
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
        while (isRunning) {
            try {
                selectDict();
                String path = getPathToFile();

                while (blUpdate) {
                    task();
                }
            } catch (Exception e) {

            }
        }
    }

    private static String getPathToFile() {
        System.out.print("Введите путь к файлу: ");
        String input = scanner.next();
        if (!input.matches("^[a-zA-Z0-9]+$")) {
            System.out.println("Только латинские буквы и цифры! ");
            return getPathToFile();
        }
        return input;
    }

    private static void task() {
        printInfoTask();
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
