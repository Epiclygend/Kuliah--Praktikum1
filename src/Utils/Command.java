package Utils;

import java.util.function.BiConsumer;

public class Command {
    final public String title;
    final public BiConsumer<Runnable, Runnable> action;

    public Command(String title, BiConsumer<Runnable, Runnable> action) {
        this.title = title;
        this.action = action;
    }
}
