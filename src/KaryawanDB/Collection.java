package KaryawanDB;

import java.util.ArrayList;

interface HasId {
    public String getId();
}

public abstract class Collection<T extends HasId> {
    private ArrayList<T> repository = new ArrayList<>();

    public void add(T item) {
        repository.add(item);
    }

    public T find(String id) {
        return repository.stream()
            .filter((item) -> item.getId().equals(id))
            .findFirst()
            .get();
    }

    public void delete(String id) {
        repository.removeIf(item -> item.getId().equals(id));
    }

    public ArrayList<T> getCollection() {
        return repository;
    }
}
