package hibernate.dao.Interface;

public interface BaseCommand<T> {
    void add(final T t);
    void update(final T t);
    void delete(final T t);
}
