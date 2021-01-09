package Utils;

import java.util.Scanner;
import java.util.function.Function;

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

    public static class RangeInput extends InputValidator<Integer> {
        int min;
        int max;

        public RangeInput(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        protected boolean validate(Integer value) {
            return value >= min && value <= max;
        }

        @Override
        protected Function<String, Integer> getInputMethod() {
            return Input::integer;
        }
    }
}
