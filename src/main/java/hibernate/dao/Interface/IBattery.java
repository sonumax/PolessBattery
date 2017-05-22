package hibernate.dao.Interface;

import java.util.Collection;

public interface IBattery<T> extends BaseCommand<T>{
    T getBatteryById(final int id);
    Collection<T> getByMark(final String mark);
    Collection<T> getByCapacity(final int capacity);
    Collection<T> getByAmperage(final int amperage);
    Collection<T> getByPolarityId(final int polarityId);
}
