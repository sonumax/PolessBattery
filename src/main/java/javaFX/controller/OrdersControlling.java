package javaFX.controller;

import hibernate.dao.Impl.OrderDAO;
import hibernate.tables.OrdersEntity;
import javaFX.view.RecordOder;
import javaFX.view.WorkTableOrders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.DialogManager;

import java.io.IOException;
import java.sql.Date;

public class OrdersControlling {
    public Button btnAdd;
    public Button btnChange;
    public Button btnDelete;
    public Button btnPrice;
    public Button btnFind;
    public TableView<RecordOder> tblOrders;
    public TableColumn<RecordOder, Integer> columnOrderId;
    public TableColumn<RecordOder, Integer> columnBatteryId;
    public TableColumn<RecordOder, String> columnCustomers;
    public TableColumn<RecordOder, String> columnMark;
    public TableColumn<RecordOder, Integer> columnCapacity;
    public TableColumn<RecordOder, Integer> columnAmperage;
    public TableColumn<RecordOder, String> columnPolarity;
    public TableColumn<RecordOder, Integer> columnCount;
    public TableColumn<RecordOder, Date> columnDaneExecute;

    private Stage ordersStage;

    @FXML
    private void initialize() {
        setTableItem();
    }

    public void setTableItem() {
        columnOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        columnBatteryId.setCellValueFactory(new PropertyValueFactory<>("batteryId"));
        columnCustomers.setCellValueFactory(new PropertyValueFactory<>("organizationName"));
        columnMark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        columnCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        columnAmperage.setCellValueFactory(new PropertyValueFactory<>("amperage"));
        columnPolarity.setCellValueFactory(new PropertyValueFactory<>("namePolarity"));
        columnCount.setCellValueFactory(new PropertyValueFactory<>("quantityProduct"));
        columnDaneExecute.setCellValueFactory(new PropertyValueFactory<>("dateExecution"));

        tblOrders.setItems(WorkTableOrders.getOrders());
    }

    public void deleteOrder(ActionEvent actionEvent, RecordOder recordOder) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader();
            String fxmlFile = "/fxml/deleteOrder.fxml";
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            DeleteOrder deleteOrder = loader.getController();
            stage.setTitle("Удаление");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ordersStage);

            OrderDAO orderDAO = new OrderDAO();
            OrdersEntity ordersEntity = orderDAO.getOrderById(recordOder.getOrderId());
            deleteOrder.setOrdersEntity(ordersEntity);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addOrder(ActionEvent actionEvent, RecordOder recordOder) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader();
            String fxmlFile = "/fxml/addOrder.fxml";
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));

            AddOrder orderControlling = loader.getController();
            orderControlling.setOrderStage(stage);
            orderControlling.setRecordOder(recordOder);


            stage.setTitle("Заказа");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ordersStage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeOrder(ActionEvent actionEvent, RecordOder recordOder) {
        FXMLLoader loader = new FXMLLoader();
        Stage stage = new Stage();
        try {
            String fxmlFile = "/fxml/changeOrder.fxml";
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            ChangeOrder changeOrder = loader.getController();
            changeOrder.setRecordOder(recordOder);
            stage.setTitle("Изменение заказа");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ordersStage);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDialog(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }

        RecordOder selectedRecordOder = tblOrders.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnAdd":
                addOrder(actionEvent, null);
                break;

            case "btnChange":
                if (!personIsSelected(selectedRecordOder)) {
                    return;
                }
                addOrder(actionEvent, selectedRecordOder);
                break;
            case "btnDelete":
                if (!personIsSelected(selectedRecordOder)) {
                    return;
                }
                deleteOrder(actionEvent, selectedRecordOder);
                break;

            case "btnPrice":
                break;

            case "btnFind":
                break;
        }
    }

    private boolean personIsSelected(RecordOder oder) {
        if(oder == null){
            DialogManager.showInfoDialog("Ошибка", "Выберите заказ");
            return false;
        }
        return true;
    }

    public void setOrdersStage(Stage ordersStage) {
        this.ordersStage = ordersStage;
    }
}
