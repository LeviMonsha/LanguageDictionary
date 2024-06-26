package classes;

import classes.dictionary.DigitDict;
import classes.dictionary.LatinDict;
import classes.exception.ChooseFileDictonaryException;

import java.io.IOException;
import java.util.Scanner;

public class InputHandler {
    private static boolean blUpdate = true;
    private static boolean isRunning = true;
    private static RWManager dictManager;
    private static final Scanner scanner = new Scanner(System.in);

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

    private static void selectDict(String path) throws ChooseFileDictonaryException, IOException {
        printInfoDict();
        int value = inputValue();
        if (value == 0) dictManager = new LatinDict(path);
        else if (value == 1) dictManager = new DigitDict(path);
        else {
            System.out.println("Неверный ввод!");
            selectDict(path);
        }
        blUpdate = true;
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
                String path = getPathToFile();
                selectDict(path);

                while (blUpdate) {
                    task();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                update();
            }
        }
        System.out.println("Выход...\nДо скорого!");
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
        int keyHandler = inputValue();
        try {
            switch (keyHandler) {
                case 1 -> System.out.println("{\n" + dictManager.readAllDict() + "\n}");
                case 2 -> {
                    System.out.print("Введите ключ: ");
                    dictManager.searchWord(scanner.next());
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
                            dictManager.addNewWord(key, value);
                        }
                        else System.out.println("Неверный формат для добавления слова");
                    }
                }
                case 4 -> {
                    System.out.print("Введите ключ: ");
                    dictManager.deleteWord(scanner.next());
                }
                case -1 -> blUpdate = false;
                case 27 -> { blUpdate = false; isRunning = false; }
                default -> System.out.println("Такой функции не существует");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            task();
        }
    }
}
