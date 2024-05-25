import classes.InputHandler;

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