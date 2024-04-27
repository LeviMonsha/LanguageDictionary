import classes.LangDictionary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println("|  Dictionary  |");
        System.out.println("~~~~~~~~~~~~~~~~");

        PrintInfo();
        update();
    }

    private static void PrintInfo() {
        System.out.println("~~~~  TASK  ~~~~");
        System.out.println("(1) Просмотр словаря");
        System.out.println("(2) Поиск слова");
        System.out.println("(3) Добавление слова");
        System.out.println("(4) Удаление слова");
        System.out.println("~~~~~~~~~~~~~~~~");
    }

    boolean blUpdate = true;
    public static void update() {
        Scanner scanner = new Scanner(System.in);
        LangDictionary dictionary = new LangDictionary();
        String word;
        int keyHandler = 0;
        while (keyHandler != 27) {
            if (scanner.hasNextInt()) {
                keyHandler = scanner.nextInt();
            }

            switch (keyHandler) {
                case 1:
                    dictionary.ReadAllDict();
                    break;
                case 2:
                    word = scanner.next();
                    dictionary.SearchWord(word);
                    break;
                case 3:
                    System.out.println("Формат добавления слова:");
                    System.out.println("<word> : <translateWord>");
                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                        String line = scanner.nextLine();
                        String[] words = line.split(" : ");
                        String key = words[0], value = words[1];
                        dictionary.AddNewWord(key, value);
                    }
                    break;
                case 4:
                    word = scanner.next();
                    dictionary.DeleteWord(word);
                    break;
            }
        }
    }
}