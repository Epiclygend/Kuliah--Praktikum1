package MahasiswaDB;

import java.util.ArrayList;
import java.util.HashMap;

public class Collection<T extends Collectable> {
    final protected ArrayList<T> collections = new ArrayList<T>();
    final HashMap<String, String> findBy = new HashMap<>();

    public void add(T data) {
        collections.add(data);
        System.out.println("Added!");
    }

    public void deleteById(String id) {
        collections.removeIf(item -> item.getId() == id);
    }

    public void createFindByCategoryFunc(T search) {
        // final Function<T> generatedFunc =
        collections.indexOf(search);
    }

    public void printAll() {
        collections.forEach(item -> item.print());
    }
}
