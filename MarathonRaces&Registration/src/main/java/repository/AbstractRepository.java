package repository;
import model.*;
import java.util.*;
import java.util.Collection;

public abstract class AbstractRepository <Id, T extends Identifiable<Id>> implements Repository<Id, T> {
    protected Map<Id, T> elem;

    public AbstractRepository() {
        elem = new HashMap<>();
    }

    public T add(T el) {
        if (elem.containsKey(el.getId()))
            throw new RuntimeException("Element already exists!!!");
        else
            elem.put(el.getId(), el);
        return el;
    }

    public void delete(T el) {
        if (elem.containsKey(el.getId()))
            elem.remove(el.getId());
        else
            throw new RuntimeException("Element doesn't exist!!!");
    }

    public void update(Id id, T el) {
        if (elem.containsKey(id))
            elem.put(id, el);
        else
            throw new RuntimeException("Element doesn't exist");
    }

    public T findById(Id id) {
        if (elem.containsKey(id))
            return elem.get(id);
        else
            throw new RuntimeException("Element doesn't exist");
    }

    public Iterable<T> findAll() {
        return elem.values();
    }

    @Override
    public Collection<T> getAll() {
        return elem.values();
    }
}