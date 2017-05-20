package javaFX.controller;

import hibernate.dao.Impl.BatteryDAO;
import hibernate.dao.Impl.OrderDAO;
import hibernate.tables.BatteryEntity;
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
import util.Utils;

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

    private Stage mainStage;
    private Stage addOrderStage;
    private Parent fxmlEdit;
    private AddOrder addOrderController;
    private FXMLLoader fxmlLoader = new FXMLLoader();

    @FXML
    private void initialize() {
        setTableItem();
        initLoader();
    }

    private void initLoader() {
        try {
            fxmlLoader.setLocation(getClass().getResource("/fxml/addOrder.fxml"));
            fxmlEdit = fxmlLoader.load();
            addOrderController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTableItem() {
        columnOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        columnBatteryId.setCellValueFactory(new PropertyValueFactory<>("batteryId"));
        columnCustomers.setCellValueFactory(new PropertyValueFactory<>("organizationName"));
        columnMark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        columnCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        columnAmperage.setCellValueFactory(new PropertyValueFactory<>("amperage"));
        columnPolarity.setCellValueFactory(new PropertyValueFactory<>("namePolarity"));
        columnCount.setCellValueFactory(new PropertyValueFactory<>("quantityProduct"));
        columnDaneExecute.setCellValueFactory(new PropertyValueFactory<>("dateExecution"));

        WorkTableOrders.fillTablesItem();
        tblOrders.setItems(WorkTableOrders.getOrders());
    }

    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (!(source instanceof Button)) {
            return;
        }
        RecordOder selectedRecordOder = tblOrders.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;
        actionButton(actionEvent, selectedRecordOder, clickedButton);
    }

    private void actionButton(ActionEvent actionEvent, RecordOder selectedRecordOder, Button clickedButton) {
        switch (clickedButton.getId()) {
            case "btnAdd":
                addOrderController.setRecordOder(null);
                showDialog();
                break;
            case "btnChange":
                if (!personIsSelected(selectedRecordOder)) {
                    return;
                }
                addOrderController.setRecordOder(selectedRecordOder);
                showDialog();
                break;
            case "btnDelete":
                if (!personIsSelected(selectedRecordOder)) {
                    return;
                }
                deleteOrder(actionEvent, selectedRecordOder.getOrderId());
                break;
            case "btnPrice":
                if (!personIsSelected(selectedRecordOder)) {
                    return;
                }
                showPrice(actionEvent, selectedRecordOder);
                break;
        }
    }

    private void deleteOrder(ActionEvent actionEvent, int orderId) {
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
            stage.initOwner(mainStage);

            OrderDAO orderDAO = new OrderDAO();
            OrdersEntity ordersEntity = orderDAO.getOrderById(orderId);
            deleteOrder.setOrdersEntity(ordersEntity);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showPrice(ActionEvent actionEvent, RecordOder selectOrder) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader();
            String fxmlFile = "/fxml/cost.fxml";
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            Cost costController = loader.getController();
            stage.setTitle("Стоимость заказа");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainStage);

            double priceOrder = getPriceOrder(selectOrder);
            costController.setPriceOrder(priceOrder);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double getPriceOrder(RecordOder selectOrder) {
        BatteryDAO batteryDAO = new BatteryDAO();
        BatteryEntity battery = batteryDAO.getBatteryById(selectOrder.getBatteryId());
        double priceBattery = battery.countPrice();
        return priceBattery * selectOrder.getQuantityProduct();
    }

    private void showDialog() {
        if (addOrderStage ==null) {
            addOrderStage = new Stage();
            addOrderStage.setTitle("Редактирование записи");
            addOrderStage.setResizable(false);
            addOrderStage.setScene(new Scene(fxmlEdit));
            addOrderStage.initModality(Modality.WINDOW_MODAL);
            addOrderStage.initOwner(mainStage);
            addOrderController.setOrderStage(addOrderStage);
        }
        addOrderStage.showAndWait(); // для ожидания закрытия окна

    }

    private boolean personIsSelected(RecordOder oder) {
        if(oder == null){
            Utils.showErrorDialog("Ошибка", "Выберите заказ");
            return false;
        }
        return true;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
