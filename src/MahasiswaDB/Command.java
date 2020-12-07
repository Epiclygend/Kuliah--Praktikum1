package MahasiswaDB;

import java.util.function.BiConsumer;

public class Command {
    final String title;
    final BiConsumer<Runnable, Runnable> action;

    public Command(String title, BiConsumer<Runnable, Runnable> action) {
        this.title = title;
        this.action = action;
    }
}
