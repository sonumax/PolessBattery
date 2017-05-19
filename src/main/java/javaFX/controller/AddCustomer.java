package javaFX.controller;

import hibernate.dao.Impl.CustomerDAO;
import hibernate.tables.CustomersEntity;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.DialogManager;

public class AddCustomer {
    public TextField txtfCustomer;
    public TextField txtfAddress;
    public TextField txtfContactPerson;
    public TextField txtfPhone;
    public TextField txtfMail;
    public Button btnOk;
    public Button btnCancel;

    public void inputPhone(KeyEvent keyEvent) {
        char c = keyEvent.getCharacter().charAt(0);
        if ((c >= '0') && (c <= '9') || (c == '+') || (c == '(') || (c == ')')) {
            return;
        }
        keyEvent.consume();
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void actionAddCustomer(ActionEvent actionEvent) {
        if (!checkTextField()) {
            DialogManager.showInfoDialog("Ошибка", "Заполните все поля");
        }
        CustomerDAO customerDAO = new CustomerDAO();
        CustomersEntity newCustomer = createNewCustomer();
        customerDAO.add(newCustomer);
        actionClose(actionEvent);
    }

    private CustomersEntity createNewCustomer() {
        CustomersEntity newCustomer = new CustomersEntity();
        newCustomer.setOrganizationName(txtfCustomer.getText().trim());
        newCustomer.setAddress(txtfAddress.getText().trim());
        newCustomer.setContactPerson(txtfContactPerson.getText().trim());
        newCustomer.setPhone(txtfPhone.getText().trim());
        newCustomer.setMail(txtfMail.getText().trim());
        return newCustomer;
    }

    private boolean checkTextField() {
        if (countLengthTextField(txtfCustomer) == 0 || countLengthTextField(txtfAddress) == 0
                || countLengthTextField(txtfContactPerson) == 0 || countLengthTextField(txtfPhone) == 0
                || countLengthTextField(txtfMail) == 0) {
            return false;
        }
        return true;
    }

    private int countLengthTextField(TextField textField) {
        return textField.getText().trim().length();
    }
}
