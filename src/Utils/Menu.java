package Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiConsumer;

public class Menu {
    public String chooseMenuTitle = "Pilih Menu: ";

    final private ArrayList<Command> MENU = new ArrayList<Command>();

    public Menu() {
    }

    public Menu(Command... listOfMenu) {
        Arrays.asList(listOfMenu).stream().forEach(menu -> MENU.add(menu));
    }

    public void register(Command command) {
        MENU.add(command);
    }

    public void register(String title, BiConsumer<Runnable, Runnable> action) {
        MENU.add(new Command(title, action));
    }

    public void showMenu() {
        for (int i = 0; i < MENU.size(); i++) {
            System.out.println(i + ". " + MENU.get(i).title);
        }
    }

    public Command getMenuSelection() {
        try {
            final int selection = Input.integer(chooseMenuTitle);

            return MENU.get(selection);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Command! Please input available menu!");
            return getMenuSelection();
        }
    }

    public Command get(int selection) {
        while (true) {
            try {
                return MENU.get(selection);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid Command! Please input available menu!");
            }
        }
    }
}
