package MahasiswaDB;

import java.util.Scanner;

public class Utils {
    private static Scanner userInput = new Scanner(System.in);
    
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
