package javaFX.controller;

import hibernate.dao.Impl.BatteryDAO;
import hibernate.dao.Impl.CustomerDAO;
import hibernate.dao.Impl.OrderDAO;
import hibernate.dao.Impl.PolarityDAO;
import hibernate.tables.BatteryEntity;
import hibernate.tables.CustomersEntity;
import hibernate.tables.OrdersEntity;
import hibernate.tables.PolarityEntity;
import javaFX.view.RecordOder;
import javaFX.view.WorkTableOrders;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Utils;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class AddOrder {
    public ChoiceBox<String> chbCustomers;
    public ChoiceBox<String> chbMark;
    public ChoiceBox<Integer> chbCapacity;
    public ChoiceBox<Integer> chbAmperage;
    public ChoiceBox<String> chbPolarity;
    public TextField txtfQuantity;
    public DatePicker dateDateExecution;
    public Button btnOk;
    public Button btnCancel;
    private Stage orderStage;
    private RecordOder recordOder;

    public void setOrderStage(Stage orderStage) {
        this.orderStage = orderStage;
    }

    public void setRecordOder(RecordOder recordOder) {
        this.recordOder = recordOder;
        setFields();
    }

    public void inputKey(KeyEvent keyEvent) {
        char c = keyEvent.getCharacter().charAt(0);
        if ((c < '0') || (c > '9')) {
            keyEvent.consume();
        }
    }

    public void addCustomer(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            String fxmlFile = "/fxml/addCustomer.fxml";
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            AddCustomer customerController = loader.getController();
            customerController.setOrderStage(orderStage);
            customerController.setAddOrder(this);
            stage.setTitle("Новый заказчик");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(orderStage);
            orderStage.hide();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBattery(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            String fxmlFile = "/fxml/addBattery.fxml";
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            AddBattery batteryController = loader.getController();
            batteryController.setOrderStage(orderStage);
            batteryController.setAddOrder(this);
            stage.setTitle("Новый заказчик");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(orderStage);
            orderStage.hide();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent) {
        if (!checkValues()){
            return;
        }
        saveOrder();
        WorkTableOrders.fillTablesItem();
        actionClose(actionEvent);
    }

    public void fillChoiceBox() {
        fillMarkBattery();
        fillItemToCustomers();
    }

    public void fillMarkBattery() {
        BatteryDAO batteryDAO = new BatteryDAO();
        Set<String> mark = new HashSet<>();

        for (BatteryEntity batteryEntity : batteryDAO.getAll()) {
            mark.add(batteryEntity.getMark());
        }

        chbMark.setItems(FXCollections.observableArrayList(mark));
    }

    public void fillItemToCustomers() {
        CustomerDAO customerDAO = new CustomerDAO();
        Set<String> customers = new HashSet<>();
        for (CustomersEntity customersEntity: customerDAO.getAll()) {
            customers.add(customersEntity.getOrganizationName());
        }
        chbCustomers.setItems(FXCollections.observableArrayList(customers));
    }

    @FXML
    private void initialize() {
        setActionOnChoiceBox();
        fillChoiceBox();
    }

    private void setActionOnChoiceBox() {
        setActionOnMark();
        setActionOnCapacity();
        setActionOnAmperage();
    }

    private void clearAndDisableChoiceBox() {
        clearChoiceBox();
        disableChoiceBox();
    }

    private void clearChoiceBox() {
        chbCustomers.getItems().clear();
        chbMark.getItems().clear();
        chbCapacity.getItems().clear();
        chbAmperage.getItems().clear();
        chbPolarity.getItems().clear();
    }

    private void disableChoiceBox() {
        chbCapacity.setDisable(true);
        chbAmperage.setDisable(true);
        chbPolarity.setDisable(true);
    }

    private void setActionOnMark() {
        chbMark.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.intValue() < 0) {
                return;
            }

            chbCapacity.getItems().clear();
            chbAmperage.getItems().clear();
            chbPolarity.getItems().clear();

            disableChoiceBox();

            BatteryDAO batteryDAO = new BatteryDAO();
            final String mark = chbMark.getItems().get(newValue.intValue());
            Set<Integer> capacity = new HashSet<>();
            for (BatteryEntity batteryEntity : batteryDAO.getByMark(mark)){
                capacity.add(batteryEntity.getCapacity());
            }

            chbCapacity.setItems(FXCollections.observableArrayList(capacity));
            chbCapacity.setDisable(false);
        });
    }

    private void setActionOnCapacity() {
        chbCapacity.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == -1) {
                return;
            }
            chbPolarity.setDisable(true);
            chbPolarity.getItems().clear();
            BatteryDAO batteryDAO = new BatteryDAO();
            final String mark = chbMark.getValue();
            final int capacity = chbCapacity.getItems().get(newValue.intValue());
            Set<Integer> amperage = new HashSet<>();
            for (BatteryEntity batteryEntity : batteryDAO.getByMarkCapacity(mark, capacity)) {
                amperage.add(batteryEntity.getAmperage());
            }
            chbAmperage.setItems(FXCollections.observableArrayList(amperage));
            chbAmperage.setDisable(false);
        });
    }

    private void setActionOnAmperage() {
        chbAmperage.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == -1) {
                return;
            }
            BatteryDAO batteryDAO = new BatteryDAO();
            final String mark = chbMark.getValue();
            final int capacity = chbCapacity.getValue();
            final int amperage = chbAmperage.getItems().get(newValue.intValue());
            Set<String> polarity = new HashSet<>();
            for (BatteryEntity batteryEntity : batteryDAO.getByMarkCapacityAmperage(mark, capacity, amperage)) {
                final String namePolarity = batteryEntity.getPolarity().getNamePolarity();
                polarity.add(namePolarity);
            }
            chbPolarity.setItems(FXCollections.observableArrayList(polarity));
            chbPolarity.setDisable(false);
        });
    }

    private void saveOrder() {
        OrderDAO orderDAO = new OrderDAO();
        if (recordOder == null) {
            OrdersEntity ordersEntity = getOrdersEntityWithSetFields(new OrdersEntity());
            orderDAO.add(ordersEntity);
        } else {
            OrdersEntity oldOrder = orderDAO.getOrderById(recordOder.getOrderId());
            OrdersEntity newOrder = getOrdersEntityWithSetFields(oldOrder);
            orderDAO.update(newOrder);
        }
    }

    private OrdersEntity getOrdersEntityWithSetFields(final OrdersEntity ordersEntity) {
        CustomerDAO customerDAO = new CustomerDAO();
        ordersEntity.setCustomer(customerDAO.getOrdersByOrganizationName(chbCustomers.getValue()));
        ordersEntity.setBattery(getBatteryEntity());
        final Date calendar = new Date(getCalendar().getTimeInMillis());
        ordersEntity.setDateExecution(calendar);
        final Integer quantityProduct = Integer.valueOf(txtfQuantity.getText());
        ordersEntity.setQuantityProduct(quantityProduct);
        return ordersEntity;
    }

    private BatteryEntity getBatteryEntity() {
        String mark = chbMark.getValue();
        int capacity = chbCapacity.getValue();
        int amperage = chbAmperage.getValue();
        String polarityName = chbPolarity.getValue();

        PolarityDAO polarityDAO = new PolarityDAO();
        PolarityEntity polarity = polarityDAO.getByName(polarityName);

        BatteryDAO batteryDAO = new BatteryDAO();
        return batteryDAO.getByMarkCapacityAmperagePolarity(mark, capacity, amperage, polarity.getPolarityId());
    }

    private Calendar getCalendar() {
        LocalDate date = dateDateExecution.getValue();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
        calendar.set(Calendar.MONTH, date.getMonthValue() - 1);
        calendar.set(Calendar.YEAR, date.getYear());
        return calendar;
    }

    private boolean checkValues() {
        if (Utils.countLengthTextField(txtfQuantity) == 0 || chbPolarity.getValue() == null || chbCustomers.getValue() == null){
            Utils.showErrorDialog("Ошибка", "Заполните все поля");
            return false;
        }
        return true;
    }

    private void setFields() {
        if (recordOder == null) {
            cleanTextField();
            clearAndDisableChoiceBox();
            fillChoiceBox();
            return;
        }
        setFieldsOfOrder();
    }

    private void cleanTextField() {
        txtfQuantity.setText("");
        dateDateExecution.setValue(LocalDate.now());
    }

    private void setFieldsOfOrder() {
        chbCustomers.getSelectionModel().select(recordOder.getOrganizationName());
        chbMark.getSelectionModel().select(recordOder.getMark());
        chbCapacity.getSelectionModel().select(Integer.valueOf(recordOder.getCapacity()));
        chbAmperage.getSelectionModel().select(Integer.valueOf(recordOder.getAmperage()));
        chbPolarity.getSelectionModel().select(recordOder.getNamePolarity());
        txtfQuantity.setText(String.valueOf(recordOder.getQuantityProduct()));

        Date date = recordOder.getDateExecution();
        dateDateExecution.setValue(date.toLocalDate());
    }
}
