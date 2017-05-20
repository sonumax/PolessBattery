package hibernate.dao.Interface;

public interface IPolarity<T> extends BaseCommand<T>{
    T getById(final int id);
    T getByName(final String name);
}
