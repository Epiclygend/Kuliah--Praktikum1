package KaryawanDB;

import java.util.ArrayList;
import java.util.Optional;

interface HasId {
    public String getId();
}

public abstract class Collection<T extends HasId> {
    private ArrayList<T> repository = new ArrayList<>();

    public void add(T item) {
        final Optional<T> itemWithSameId = find(item.getId());

        if (itemWithSameId.isEmpty()) repository.add(item);
        else throw new NullPointerException("Item with same code is already exists!");
    }

    public Optional<T> find(String id) {
        return repository.stream()
            .filter((item) -> item.getId().equals(id))
            .findFirst();
    }

    public void delete(String id) {
        repository.removeIf(item -> item.getId().equals(id));
    }

    public ArrayList<T> getCollection() {
        return repository;
    }
}
