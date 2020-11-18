package MathGame;

import java.util.Random;
import java.util.Scanner;

public class Utils {
    private static Scanner userInput = new Scanner(System.in);
    
    public static Random randGenerator = new Random();

    public static boolean generateRandomBoolean() {
        return Utils.randGenerator.nextInt(2) < 1;
    }

    public static String parenthesize(String text) {
        return "(" + text + ")";
    }

    public static String squareBracket(String text) {
        return "[" + text + "]";
    }

    public static String inputString(String message) {
        while (true) {
            System.out.print(message);

            try {
                return userInput.nextLine();
            } catch (Exception e) {
                System.err.println("Something error! Please try again!");
                userInput.next();
                continue;
            }
        }
    }

    public static Integer inputInteger(String message) {
        while (true) {
            System.out.print(message);

            try {
                return userInput.nextInt();
            } catch (Exception e) {
                System.err.println("Something error! Please try again!");
                userInput.next();
                continue;
            }
        }
    }
}
