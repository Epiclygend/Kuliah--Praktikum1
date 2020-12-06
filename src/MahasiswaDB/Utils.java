package MahasiswaDB;

import java.util.Scanner;

public class Utils {
    public static Scanner userInput = new Scanner(System.in);

    public static String inputString(String message) {
        System.out.print(message);

        return userInput.next();
    }

    public static Integer inputInteger(String message) {
        System.out.print(message);

        try {
            final Integer input = userInput.nextInt();
            return input;
        } catch (Exception e) {
            System.err.println("Something error! Please try again!");
            userInput.next();
            return Utils.inputInteger(message);
        }
    }

    public static boolean inputConfirm(String message) {
        switch (Utils.inputString(message + " (Y/n)").toUpperCase()) {
            case "Y":
                return true;
            case "N":
                return false;

            default:
                return inputConfirm(message);
        }
    }
}
