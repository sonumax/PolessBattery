package javaFX.controller;

import hibernate.dao.Impl.BatteryComponentDAO;
import hibernate.dao.Impl.BatteryDAO;
import hibernate.dao.Impl.ComponentDAO;
import hibernate.dao.Impl.PolarityDAO;
import hibernate.tables.BatteryComponentsEntity;
import hibernate.tables.BatteryEntity;
import hibernate.tables.ComponentEntity;
import hibernate.tables.PolarityEntity;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import util.DialogManager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AddBattery {
    public TextField txtfMark;
    public TextField txtfCapacity;
    public TextField txtfAmperage;
    public TextField txtfLead;
    public TextField txtfSeparator;
    public TextField txtfPlatePlus;
    public TextField txtfPlateMinus;
    public ChoiceBox<String> chcbPolarity;
    public ChoiceBox<Integer> chcbHandle;
    public ChoiceBox<String> chcbPatch;
    public CheckBox chkLiner;
    public CheckBox chkbIndicator;
    public Button btnOk;
    public Button btnCancel;

    @FXML
    private void initialize() {
        fillChoiceBox();
    }

    public void inputNumber(KeyEvent keyEvent) {
        char c = keyEvent.getCharacter().charAt(0);
        if ((c < '0') || (c > '9')) {
            keyEvent.consume();
        }
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void actionSave(ActionEvent actionEvent) {
        if(!checkValues()) {
            DialogManager.showInfoDialog("Ошибка", "Заполните все поля");
            return;
        }
        addNewBattery();
        System.out.println("Add");
        actionClose(actionEvent);

    }

    private void addNewBattery() {
        BatteryDAO batteryDAO = new BatteryDAO();
        BatteryEntity newBattery = new BatteryEntity();
        setBatteryCharacteristics(newBattery);
        batteryDAO.add(newBattery);
        setBatteryComponents(newBattery);
    }

    private void setBatteryCharacteristics(BatteryEntity newBattery) {
        newBattery.setMark(txtfMark.getText());
        newBattery.setCapacity(Integer.valueOf(txtfCapacity.getText()));
        newBattery.setAmperage(Integer.valueOf(txtfAmperage.getText()));

        PolarityDAO polarityDAO = new PolarityDAO();
        newBattery.setPolarity(polarityDAO.getByName(chcbPolarity.getValue()));
    }

    private void setBatteryComponents(BatteryEntity newBattery) {
        BatteryComponentDAO batteryComponentDAO = new BatteryComponentDAO();
        ComponentDAO componentDAO = new ComponentDAO();
        Collection<ComponentEntity> componentEntities = componentDAO.getAll();
        for (ComponentEntity componentEntity : componentEntities) {
            String nameComponent = componentEntity.getNameComponent();
            int count = getCountComponent(nameComponent);
            if (count == 0) {
                return;
            }
            BatteryComponentsEntity batteryComponentsEntity = new BatteryComponentsEntity();
            batteryComponentsEntity.setBattery(newBattery);
            batteryComponentsEntity.setComponent(componentEntity);
            batteryComponentsEntity.setCountComponents(count);
            batteryComponentDAO.add(batteryComponentsEntity);
//            batteryComponentsEntities.add(createBatteryComponent(newBattery, componentEntity, count));
        }
    }

    private int getCountComponent(String nameComponent) {
        int count = 0;
        switch (nameComponent) {
            case "Сплав свинца":
                count = Integer.valueOf(txtfLead.getText());
                break;
            case "Блок":
            case "Крышка":
            case "Верхняя этикетка":
            case "Передняя этикетка":
            case "Знаки безопасности":
            case "Деревянный поддон":
            case "Листавой картон":
            case "Манипуляционные знаки":
            case "Бланк сертификата качества":
                count = 1;
                break;
            case "Положительная пластина":
                count = Integer.valueOf(txtfPlatePlus.getText());
                break;
            case "Отрицательная пластина":
                count = Integer.valueOf(txtfPlateMinus.getText());
                break;
            case "Сепараторная лента":
                count = Integer.valueOf(txtfSeparator.getText());
                break;
            case "Вкладыш":
                if (!chkLiner.isSelected()) {
                    return 0;
                }
                count = 6;
                break;
            case "Ручка":
                count = chcbHandle.getValue();
                break;
            case "Накладка":
                if (chcbPatch.getValue().equals("Накладка")) {
                    count = 1;
                }
                break;
            case "Красный колпачок":
                if (chcbPatch.getValue().equals("Колпачки")) {
                    count = 1;
                }
                break;
            case "Чёрный колпачок":
                if (chcbPatch.getValue().equals("Колпачки")) {
                    count = 1;
                }
                break;
            case "Пробка":
                count = 6;
                break;
            case "Индикатор заряда АКБ":
                if (!chkbIndicator.isSelected()) {
                    return 0;
                }
                count = 1;
                break;
            case "Стрейч-плёнка":
                count = 5;
                break;
        }
        return count;
    }

    private BatteryComponentsEntity createBatteryComponent(BatteryEntity battery, ComponentEntity component, int count) {
        BatteryComponentsEntity batteryComponentsEntity = new BatteryComponentsEntity();
        batteryComponentsEntity.setBattery(battery);
        batteryComponentsEntity.setComponent(component);
        batteryComponentsEntity.setCountComponents(count);
        return batteryComponentsEntity;
    }

    private boolean checkValues() {
        if (txtfMark.getText().trim().length()==0 || txtfCapacity.getText().trim().length()==0 ||
                txtfAmperage.getText().trim().length() == 0 || txtfLead.getText().trim().length() == 0 ||
                txtfSeparator.getText().trim().length() == 0 || txtfPlatePlus.getText().trim().length() == 0 ||
                txtfPlateMinus.getText().trim().length() == 0 || chcbPolarity.getValue().trim().length() == 0 ||
                chcbPatch.getValue().trim().length() == 0){
            return false;
        }
        return true;
    }

    private void fillChoiceBox() {
        addItemToPolarity();
        addItemToHandle();
        addItemToPatch();
    }

    private void addItemToPolarity() {
        Set<String> polaritySet = new HashSet<>();
        PolarityDAO polarityDAO = new PolarityDAO();
        for (PolarityEntity polarityEntity : polarityDAO.getAll()) {
            polaritySet.add(polarityEntity.getNamePolarity());
        }

        chcbPolarity.setItems(FXCollections.observableArrayList(polaritySet));
    }

    private void addItemToHandle() {
        Set<Integer> handleSet = new HashSet<>();
        handleSet.add(0);
        handleSet.add(1);
        handleSet.add(2);

        chcbHandle.setItems(FXCollections.observableArrayList(handleSet));
    }

    private void addItemToPatch() {
        Set<String> patchSet = new HashSet<>();
        patchSet.add("Накладка");
        patchSet.add("Колпачки");
        chcbPatch.setItems(FXCollections.observableArrayList(patchSet));
    }
}
