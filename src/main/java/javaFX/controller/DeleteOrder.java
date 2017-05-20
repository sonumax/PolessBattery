package javaFX.controller;

import hibernate.dao.Impl.OrderDAO;
import hibernate.tables.OrdersEntity;
import javaFX.view.WorkTableOrders;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteOrder {
    public Button btnOk;
    public Button btnNo;

    private OrdersEntity ordersEntity;

    public void setOrdersEntity(OrdersEntity ordersEntity) {
        this.ordersEntity = ordersEntity;
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void actionOk(ActionEvent actionEvent) {
        deleteOrder();
        WorkTableOrders.fillTablesItem();
        actionClose(actionEvent);
    }

    private void deleteOrder() {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.delete(ordersEntity);
    }
}
