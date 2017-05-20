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

    @FXML
    private void initialize() {
        fillChoiceBox();
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
        OrderDAO orderDAO = new OrderDAO();
        if (recordOder == null) {
            OrdersEntity ordersEntity = getOrdersEntityWithSetFields(new OrdersEntity());
            orderDAO.add(ordersEntity);
        } else {
            OrdersEntity oldOrder = orderDAO.getOrderById(recordOder.getOrderId());
            OrdersEntity newOrder = getOrdersEntityWithSetFields(oldOrder);
            orderDAO.update(newOrder);
        }
        WorkTableOrders.fillTablesItem();
        actionClose(actionEvent);
    }

    private OrdersEntity getOrdersEntityWithSetFields(OrdersEntity ordersEntity) {
        CustomerDAO customerDAO = new CustomerDAO();
        CustomersEntity inputCustomer = customerDAO.getOrdersByOrganizationName(chbCustomers.getValue());
        BatteryEntity inputBattery = getBatteryEntity();
        Calendar calendar = getCalendar();
        int quantity = Integer.valueOf(txtfQuantity.getText());
        return getOrdersEntityWithSetFields(ordersEntity,inputCustomer, inputBattery, calendar, quantity);
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

    private OrdersEntity getOrdersEntityWithSetFields(OrdersEntity ordersEntity,CustomersEntity inputCustomer, BatteryEntity inputBattery, Calendar calendar, int quantity) {
        ordersEntity.setCustomer(inputCustomer);
        ordersEntity.setBattery(inputBattery);
        ordersEntity.setQuantityProduct(quantity);
        ordersEntity.setDateExecution(new Date(calendar.getTimeInMillis()));
        return ordersEntity;
    }

    public void setOrderStage(Stage orderStage) {
        this.orderStage = orderStage;
    }

    public void setRecordOder(RecordOder recordOder) {
        this.recordOder = recordOder;
        setFields();
    }

    private void fillChoiceBox() {
        addItemOfBattery();
        addItemOfCustomers();
        addItemToPolaritys();
    }

    private void addItemOfBattery() {
        BatteryDAO batteryDAO = new BatteryDAO();
        Set<String> mark= new HashSet<>();
        Set<Integer> capacity = new HashSet<>();
        Set<Integer> amperage = new HashSet<>();

        for (BatteryEntity batteryEntity : batteryDAO.getAll()) {
            mark.add(batteryEntity.getMark());
            capacity.add(batteryEntity.getCapacity());
            amperage.add(batteryEntity.getAmperage());
        }

        chbMark.setItems(FXCollections.observableArrayList(mark));
        chbCapacity.setItems(FXCollections.observableArrayList(capacity));
        chbAmperage.setItems(FXCollections.observableArrayList(amperage));
    }

    private void addItemOfCustomers() {
        CustomerDAO customerDAO = new CustomerDAO();
        Set<String> customers = new HashSet<>();
        for (CustomersEntity customersEntity: customerDAO.getAll()) {
            customers.add(customersEntity.getOrganizationName());
        }
        chbCustomers.setItems(FXCollections.observableArrayList(customers));
    }

    private void addItemToPolaritys() {
        PolarityDAO polarityDAO = new PolarityDAO();
        Set<String> polaritys = new HashSet<>();

        for (PolarityEntity polarityEntity : polarityDAO.getAll()) {
            polaritys.add(polarityEntity.getNamePolarity());
        }

        chbPolarity.setItems(FXCollections.observableArrayList(polaritys));
    }

    private boolean checkValues() {
        if (Utils.countLengthTextField(txtfQuantity) == 0){
            Utils.showErrorDialog("Ошибка", "Заполните все поля");
            return false;
        }
        return true;
    }

    private void setFields() {
        if (recordOder == null) {
            setFirstItem();
            cleanTextField();
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

    private void setFirstItem() {
        chbMark.getSelectionModel().selectFirst();
        chbCapacity.getSelectionModel().selectFirst();
        chbAmperage.getSelectionModel().selectFirst();
        chbCustomers.getSelectionModel().selectFirst();
        chbPolarity.getSelectionModel().selectFirst();
    }
}
