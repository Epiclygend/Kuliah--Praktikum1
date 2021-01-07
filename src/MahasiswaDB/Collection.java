package MahasiswaDB;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Collection<T extends Collectable> {
    final protected ArrayList<T> collections = new ArrayList<T>();

    public void add(T data) {
        collections.add(data);
    }

    public void deleteById(String id) {
        collections.removeIf(item -> item.getId() == id);
    }

    public Stream<T> filter(Predicate<? super T> filter) {
        return collections.stream().filter(filter);
    }

    public void printAll() {
        collections.forEach(item -> item.print());
    }

    public int length() {
        return collections.size();
    }
}
