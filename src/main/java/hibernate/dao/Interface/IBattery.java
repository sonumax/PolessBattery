package hibernate.dao.Interface;

import java.util.Collection;

public interface IBattery<T> extends BaseCommand<T>{
    T getBatteryById(final int id);
    Collection<T> getByMark(final String mark);
    Collection<T> getByCapacity(final double capacity);
    Collection<T> getByAmperage(final double amperage);
    Collection<T> getByPolarityId(final int polarityId);
    Collection<T> getAllCustomers();
}
