package hibernate.dao.Interface;

import java.util.Collection;

public interface BaseCommand<T> {
    void add(final T t);
    void update(final T t);
    void delete(final T t);
    Collection<T> getAll();
}
