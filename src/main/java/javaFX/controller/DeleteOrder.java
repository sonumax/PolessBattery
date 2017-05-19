package javaFX.controller;

import hibernate.dao.Impl.OrderDAO;
import hibernate.tables.OrdersEntity;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteOrder {
    public Button btnOk;
    public Button btnNo;

    private OrdersEntity ordersEntity;

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void actionOk(ActionEvent actionEvent) {
        deleteOrder();
        actionClose(actionEvent);
    }

    public void setOrdersEntity(OrdersEntity ordersEntity) {
        this.ordersEntity = ordersEntity;
    }

    private void deleteOrder() {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.delete(ordersEntity);
    }
}
