package javaFX;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class AddOrder {
    public ChoiceBox chbCustomers;
    public ChoiceBox chbMark;
    public ChoiceBox chbCapacity;
    public ChoiceBox chbAmperage;
    public ChoiceBox chbPolarity;
    public TextField txtfQuantity;
    public DatePicker dateDateExecution;
    public Button btnOk;
    public Button btnCancel;

    public void test(KeyEvent keyEvent) {
        char c = keyEvent.getCharacter().charAt(0);
        if ( ((c < '0') || (c > '9'))) {
            keyEvent.consume();
        }
    }
}
