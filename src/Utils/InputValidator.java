package Utils;

import java.util.function.Function;

public abstract class InputValidator<T> {
    protected abstract Function<String, T> getInputMethod();

    protected abstract boolean validate(T value);

    public T get(String message) {
        while (true) {
            final T input = getInputMethod().apply(message);

            if (validate(input)) return input;
            System.out.println(getErrorMsg());
        }
    }

    protected String getErrorMsg() {
        return "Invalid input! Please try again...";
    }
}
