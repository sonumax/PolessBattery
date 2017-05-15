package hibernate.dao.Interface;

import hibernate.tables.BatteryEntity;
import hibernate.tables.CustomersEntity;

import java.util.Collection;
import java.util.Date;

public interface IOrder<T> extends BaseCommand<T> {
    T getOrderById(final int id);
    Collection<T> getAllOrder();
    Collection<T> getOrdersByCustomer(final CustomersEntity customer);
    Collection<T> getOrdersByBattery(final BatteryEntity battery);
    Collection<T> getOrdersByDate(final Date date);
}
