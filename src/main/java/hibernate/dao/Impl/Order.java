package hibernate.dao.Impl;

import hibernate.dao.Interface.IOrder;
import hibernate.dbService.DBService;
import hibernate.tables.BatteryEntity;
import hibernate.tables.CustomersEntity;
import hibernate.tables.OrdersEntity;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Date;

public class Order implements IOrder<OrdersEntity> {

    private final DBService<OrdersEntity> dbService = new DBService<>();

    @Override
    public void add(final OrdersEntity order) {
        dbService.insertToDB(order);
    }

    @Override
    public void update(final OrdersEntity order) {
        dbService.updateInDB(order);
    }

    @Override
    public void delete(final OrdersEntity order) {
        dbService.deleteInDB(order);
    }

    @Override
    public OrdersEntity getOrderById(final int id) {
        return dbService.getByID(OrdersEntity.class, id);
    }

    @Override
    public Collection<OrdersEntity> getAll() {
        Collection<OrdersEntity> ordersList = dbService.getCollectionResult(builder -> {
            CriteriaQuery<OrdersEntity> criteria = builder.createQuery(OrdersEntity.class);
            Root<OrdersEntity> ordersRoot = criteria.from(OrdersEntity.class);
            criteria.select(ordersRoot);
            return criteria;
        });
        return ordersList;
    }

    @Override
    public Collection<OrdersEntity> getOrdersByCustomer(final CustomersEntity customer) {
        Collection<OrdersEntity> ordersList = dbService.getCollectionResult(builder -> {
            CriteriaQuery<OrdersEntity> criteria = builder.createQuery(OrdersEntity.class);
            Root<OrdersEntity> ordersRoot = criteria.from(OrdersEntity.class);
            criteria.select(ordersRoot);
            criteria.where(builder.equal(ordersRoot.get("customerId"), customer.getCustomerId()));
            return criteria;
        });
        return ordersList;
    }

    @Override
    public Collection<OrdersEntity> getOrdersByBattery(final BatteryEntity battery) {
        Collection<OrdersEntity> ordersList = dbService.getCollectionResult(builder -> {
            CriteriaQuery<OrdersEntity> criteria = builder.createQuery(OrdersEntity.class);
            Root<OrdersEntity> ordersRoot = criteria.from(OrdersEntity.class);
            criteria.select(ordersRoot);
            criteria.where(builder.equal(ordersRoot.get("batteryId"), battery.getBatteryId()));
            return criteria;
        });
        return ordersList;
    }

    @Override
    public Collection<OrdersEntity> getOrdersByDate(final Date date) {
        Collection<OrdersEntity> ordersList = dbService.getCollectionResult(builder -> {
            CriteriaQuery<OrdersEntity> criteria = builder.createQuery(OrdersEntity.class);
            Root<OrdersEntity> ordersRoot = criteria.from(OrdersEntity.class);
            criteria.select(ordersRoot);
            criteria.where(builder.equal(ordersRoot.get("dateExecution"), date));
           return criteria;
        });
        return ordersList;
    }
}
