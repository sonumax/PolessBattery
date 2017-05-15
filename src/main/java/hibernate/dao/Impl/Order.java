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
    public Collection<OrdersEntity> getAllOrder() {
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
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//
//        CriteriaQuery<OrdersEntity> criteria = builder.createQuery(OrdersEntity.class);
//        Root<OrdersEntity> ordersRoot = criteria.from(OrdersEntity.class);
//        criteria.select(ordersRoot);
//        criteria.where(builder.equal(ordersRoot.get("CustomerID"), customer.getCustomerId()));
//
//        List<OrdersEntity> orders = session.createQuery(criteria).getResultList();
//        session.close();
//        return orders;

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
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//
//        CriteriaQuery<OrdersEntity> criteria = builder.createQuery(OrdersEntity.class);
//        Root<OrdersEntity> ordersRoot = criteria.from(OrdersEntity.class);
//        criteria.select(ordersRoot);
//        criteria.where(builder.equal(ordersRoot.get("BatteryID"), battery.getBatteryId()));
//
//        List<OrdersEntity> orders = session.createQuery(criteria).getResultList();
//        session.close();
//        return orders;
        return null;
    }

    @Override
    public Collection<OrdersEntity> getOrdersByDate(final Date date) {
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//
//        CriteriaQuery<OrdersEntity> criteria = builder.createQuery(OrdersEntity.class);
//        Root<OrdersEntity> ordersRoot = criteria.from(OrdersEntity.class);
//        criteria.select(ordersRoot);
//        criteria.where(builder.equal(ordersRoot.get("DateExecution"), date));
//
//        List<OrdersEntity> orders = session.createQuery(criteria).getResultList();
//        session.close();
//        return orders;
        return null;
    }
}
