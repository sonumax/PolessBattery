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
import util.DialogManager;

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

    public void setRecordOder(RecordOder recordOder) {
        this.recordOder = recordOder;
    }

    @FXML
    private void initialize() {
        if (recordOder != null) {
            chbCustomers.setValue(recordOder.getOrganizationName());
            chbMark.setValue(recordOder.getMark());
            chbCapacity.setValue(recordOder.getCapacity());
            chbAmperage.setValue(recordOder.getAmperage());
            chbPolarity.setValue(recordOder.getNamePolarity());
            txtfQuantity.setText(String.valueOf(recordOder.getQuantityProduct()));

            Date date = recordOder.getDateExecution();
            dateDateExecution.setAccessibleText(date.toString());
        }
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
            stage.setTitle("Новый заказчик");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(orderStage);
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
            stage.setTitle("Новый заказчик");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(orderStage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void actionSave(ActionEvent actionEvent) {
        if (!checkValues()){
            DialogManager.showInfoDialog("Ошибка", "Заполните все поля");
            return;
        }
        OrderDAO orderDAO = new OrderDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        CustomersEntity inputCustomer = customerDAO.getOrdersByOrganizationName(chbCustomers.getValue());

        String mark = chbMark.getValue();
        int capacity = chbCapacity.getValue();
        int amperage = chbAmperage.getValue();
        String polarityId = chbPolarity.getValue();
        int quantity = Integer.valueOf(txtfQuantity.getText());

        LocalDate date = dateDateExecution.getValue();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
        cal.set(Calendar.MONTH, date.getMonthValue());
        cal.set(Calendar.YEAR, date.getYear());

        PolarityDAO polarityDAO = new PolarityDAO();
        PolarityEntity polarity = polarityDAO.getByName(polarityId);

        BatteryDAO batteryDAO = new BatteryDAO();
        BatteryEntity inputBattery = batteryDAO.getByMarkCapacityAmperagePolarity(mark, capacity, amperage, polarity.getPolarityId());

        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setCustomer(inputCustomer);
        ordersEntity.setBattery(inputBattery);
        ordersEntity.setQuantityProduct(quantity);
        ordersEntity.setDateExecution(new Date(cal.getTimeInMillis()));

        orderDAO.add(ordersEntity);

        actionClose(actionEvent);
    }

    public void setOrderStage(Stage orderStage) {
        this.orderStage = orderStage;
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
        if (chbCustomers == null || chbMark == null
                || chbCapacity == null || chbAmperage == null || chbPolarity == null
                || txtfQuantity.getText().trim().length() == 0){
            return false;
        }
        return true;
    }
}
