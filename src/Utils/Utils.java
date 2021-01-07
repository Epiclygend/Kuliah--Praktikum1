package Utils;

import java.util.Scanner;

public class Utils {
    final static int TEXT_MAXLENGTH = 100;
    public static Scanner userInput = new Scanner(System.in);

    public static void drawSeparator(int count) {
        System.out.println("-".repeat(count));
    }

    public static void drawSeparator() {
        drawSeparator(TEXT_MAXLENGTH);
    }

    public static String padLeft(String text, String replacer, int maxLen) {
        final int textLen = text.length();

        if (textLen >= maxLen) {
            return text;
        }

        final int replacerCount = maxLen - textLen;

        return replacer.repeat(replacerCount) + text;
    }

    public static String padLeft(String text, String replacer) {
        return padLeft(text, replacer, TEXT_MAXLENGTH);
    }

    public static String padLeft(String text) {
        return padLeft(text, " ");
    }

    public static String inputString(String message) {
        System.out.print(message);
        return userInput.nextLine();
    }

    public static Integer inputInteger(String message) {
        System.out.print(message);

        try {
            final Integer input = Integer.parseInt(userInput.nextLine());
            return input;
        } catch (Exception e) {
            System.err.println("Something error! Please try again!");
            userInput.next();
            return Utils.inputInteger(message);
        }
    }

    public static boolean inputConfirm(String message) {
        switch (Utils.inputString(message + " (Y/n) ").toUpperCase()) {
            case "Y":
                return true;
            case "N":
                return false;

            default:
                return inputConfirm(message);
        }
    }
}
