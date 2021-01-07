package Utils;

import java.util.Scanner;

public class Input {
    public static Scanner instance = new Scanner(System.in);

    public static String string(String message) {
        System.out.print(message);
        return instance.nextLine();
    }

    public static Integer integer(String message) {
        System.out.print(message);

        try {
            final Integer input = Integer.parseInt(instance.nextLine());
            return input;
        } catch (Exception e) {
            System.err.println("Something error! Please try again!");
            instance.next();
            return Input.integer(message);
        }
    }

    public static boolean confirm(String message) {
        switch (string(message + " (Y/n) ").toUpperCase()) {
            case "Y":
                return true;
            case "N":
                return false;

            default:
                return Input.confirm(message);
        }
    }
}
