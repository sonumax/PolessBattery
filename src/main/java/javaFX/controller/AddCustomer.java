package javaFX.controller;

import hibernate.dao.Impl.CustomerDAO;
import hibernate.tables.CustomersEntity;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.Utils;

public class AddCustomer {
    public TextField txtfCustomer;
    public TextField txtfAddress;
    public TextField txtfContactPerson;
    public TextField txtfPhone;
    public TextField txtfMail;
    public Button btnOk;
    public Button btnCancel;

    private Stage orderStage;
    private AddOrder addOrder;

    public void setOrderStage(Stage orderStage) {
        this.orderStage = orderStage;
    }

    public void setAddOrder(AddOrder addOrder) {
        this.addOrder = addOrder;
    }

    public void inputPhone(KeyEvent keyEvent) {
        char inputKye = keyEvent.getCharacter().charAt(0);
        if ((inputKye >= '0') && (inputKye <= '9') || (inputKye == '+') || (inputKye == '(') || (inputKye == ')')) {
            return;
        }
        keyEvent.consume();
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        orderStage.show();
    }

    public void actionAddCustomer(ActionEvent actionEvent) {
        if (!checkTextField()) {
            return;
        }
        saveNewCustomer();
        actionClose(actionEvent);
    }

    private void saveNewCustomer() {
        CustomerDAO customerDAO = new CustomerDAO();
        CustomersEntity newCustomer = getNewCustomer();
        customerDAO.add(newCustomer);
        addOrder.fillItemToCustomers();
        addOrder.chbCustomers.setValue(newCustomer.getOrganizationName());
    }

    private CustomersEntity getNewCustomer() {
        CustomersEntity newCustomer = new CustomersEntity();
        newCustomer.setOrganizationName(txtfCustomer.getText().trim());
        newCustomer.setAddress(txtfAddress.getText().trim());
        newCustomer.setContactPerson(txtfContactPerson.getText().trim());
        newCustomer.setPhone(txtfPhone.getText().trim());
        newCustomer.setMail(txtfMail.getText().trim());
        return newCustomer;
    }

    private boolean checkTextField() {
        if (Utils.countLengthTextField(txtfCustomer) == 0 || Utils.countLengthTextField(txtfAddress) == 0
                || Utils.countLengthTextField(txtfContactPerson) == 0 || Utils.countLengthTextField(txtfPhone) == 0
                || Utils.countLengthTextField(txtfMail) == 0) {
            Utils.showErrorDialog("Ошибка", "Заполните все поля");
            return false;
        }
        return true;
    }
}
