package hibernate.dao.Interface;

import java.util.Collection;

public interface IComponent<T> extends BaseCommand<T> {
    T getComponentById(final int id);
    T getComponentByName(final String name);
    Collection<T> getComponentsByPrice(final double price);
}
