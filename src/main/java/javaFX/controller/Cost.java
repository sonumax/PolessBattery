package javaFX.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Cost {

    public Label costLabel;
    public Button cancelButton;
    private double priceOrder;

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setPriceOrder(double priceOrder) {
        this.priceOrder = priceOrder;
        String formattedPrice = String.format("%.2f", priceOrder);
        costLabel.setText(formattedPrice);
    }
}
