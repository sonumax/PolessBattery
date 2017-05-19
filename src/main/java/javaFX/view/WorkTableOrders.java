package javaFX.view;

import hibernate.dao.Impl.OrderDAO;
import hibernate.tables.BatteryEntity;
import hibernate.tables.CustomersEntity;
import hibernate.tables.OrdersEntity;
import hibernate.tables.PolarityEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;

public class WorkTableOrders {
    private static final ObservableList<RecordOder> ORDERS = FXCollections.observableArrayList();

    private static OrderDAO orderDAO = new OrderDAO();

    public static ObservableList<RecordOder> getOrders() {
        fillTablesItem();
        return ORDERS;
    }

    private static void fillTablesItem() {
        for(OrdersEntity ordersEntity : orderDAO.getAll()) {
            ORDERS.add(getWriteRecord(ordersEntity));
        }
    }

    private static RecordOder getWriteRecord(OrdersEntity ordersEntity) {
        CustomersEntity customersEntity = ordersEntity.getCustomer();
        BatteryEntity batteryEntity = ordersEntity.getBattery();
        PolarityEntity polarityEntity = batteryEntity.getPolarity();

        int orderId = ordersEntity.getOrderId();
        int batteryId = batteryEntity.getBatteryId();
        String name = customersEntity.getOrganizationName();
        String mark = batteryEntity.getMark();
        int capacity = batteryEntity.getCapacity();
        int amperage = batteryEntity.getAmperage();
        String namePolarity = polarityEntity.getNamePolarity();
        int quantityProduct = ordersEntity.getQuantityProduct();
        Date dateExecution = ordersEntity.getDateExecution();

        return new RecordOder(orderId, batteryId, name, mark, capacity, amperage, namePolarity, quantityProduct, dateExecution);
    }
}
