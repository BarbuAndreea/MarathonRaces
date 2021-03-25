package repository;
import model.Identifiable;

import java.util.Collection;

public interface Repository<Id, T extends Identifiable<Id>>{
    T add(T elem);
    void delete(T elem);
    void update(Id id, T elem);
    T findById (Id id);
    Iterable<T> findAll();
    Collection<T> getAll();
}
