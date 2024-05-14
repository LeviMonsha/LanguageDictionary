import classes.InputHandler;
import classes.LangDictionary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        showInfo();
        InputHandler.update();
    }

    private static void showInfo() {
        System.out.println("~~~~~~~~~~~~~~~~");
        System.out.println("|  Dictionary  |");
        System.out.println("~~~~~~~~~~~~~~~~");
    }
}