package hibernate.dao.Interface;

import hibernate.tables.BatteryEntity;
import hibernate.tables.CustomersEntity;
import hibernate.tables.OrdersEntity;

import java.util.Collection;
import java.util.Date;

public interface IOrder {
    void addOrder(final OrdersEntity order);
    void updateOrder(final OrdersEntity order);
    void deleteOrder(final OrdersEntity order);
    OrdersEntity getOrderById(final int id);
    Collection<OrdersEntity> getAllOrder();
    Collection<OrdersEntity> getOrdersByCustomer(final CustomersEntity customer);
    Collection<OrdersEntity> getOrdersByBattery(final BatteryEntity battery);
    Collection<OrdersEntity> getOrdersByDate(final Date date);
}
