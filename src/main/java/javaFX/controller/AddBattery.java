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
        selectFirstItem();
    }

    public void inputTextOnlyNumber(KeyEvent keyEvent) {
        char key = keyEvent.getCharacter().charAt(0);
        if ((key < '0') || (key > '9')) {
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
            return;
        }
        addNewBattery();
        actionClose(actionEvent);
    }

    private void addNewBattery() {
        BatteryDAO batteryDAO = new BatteryDAO();
        BatteryEntity newBattery = new BatteryEntity();
        setBatteryCharacteristics(newBattery);
        batteryDAO.add(newBattery);
        setBatteryComponents(newBattery);
    }

    private void setBatteryCharacteristics(final BatteryEntity newBattery) {
        newBattery.setMark(txtfMark.getText());
        newBattery.setCapacity(Integer.valueOf(txtfCapacity.getText()));
        newBattery.setAmperage(Integer.valueOf(txtfAmperage.getText()));

        PolarityDAO polarityDAO = new PolarityDAO();
        newBattery.setPolarity(polarityDAO.getByName(chcbPolarity.getValue()));
    }

    private void setBatteryComponents(final BatteryEntity newBattery) {
        BatteryComponentDAO batteryComponentDAO = new BatteryComponentDAO();
        ComponentDAO componentDAO = new ComponentDAO();

        Collection<ComponentEntity> allComponents = componentDAO.getAll();
        for (ComponentEntity component : allComponents) {
            String nameComponent = component.getNameComponent();
            int count = getCountComponent(nameComponent);
            if (count == 0) {
                continue;
            }
            BatteryComponentsEntity newBatteryComponent = createBatteryComponent(newBattery, component, count);
            batteryComponentDAO.add(newBatteryComponent);
        }
    }

    private int getCountComponent(String nameComponent) {
        int countComponent = 0;
        switch (nameComponent) {
            case "Сплав свинца":
                countComponent = Integer.valueOf(txtfLead.getText());
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
                countComponent = 1;
                break;
            case "Положительная пластина":
                countComponent = Integer.valueOf(txtfPlatePlus.getText());
                break;
            case "Отрицательная пластина":
                countComponent = Integer.valueOf(txtfPlateMinus.getText());
                break;
            case "Сепараторная лента":
                countComponent = Integer.valueOf(txtfSeparator.getText());
                break;
            case "Вкладыш":
                if (!chkLiner.isSelected()) {
                    return 0;
                }
                countComponent = 6;
                break;
            case "Ручка":
                countComponent = chcbHandle.getValue();
                break;
            case "Накладка":
                if (chcbPatch.getValue().equals("Накладка")) {
                    countComponent = 1;
                }
                break;
            case "Красный колпачок":
                if (chcbPatch.getValue().equals("Колпачки")) {
                    countComponent = 1;
                }
                break;
            case "Чёрный колпачок":
                if (chcbPatch.getValue().equals("Колпачки")) {
                    countComponent = 1;
                }
                break;
            case "Пробка":
                countComponent = 6;
                break;
            case "Индикатор заряда АКБ":
                if (!chkbIndicator.isSelected()) {
                    return 0;
                }
                countComponent = 1;
                break;
            case "Стрейч-плёнка":
                countComponent = 5;
                break;
        }
        return countComponent;
    }

    private BatteryComponentsEntity createBatteryComponent(final BatteryEntity battery, final ComponentEntity component, final int count) {
        BatteryComponentsEntity newBatteryComponent = new BatteryComponentsEntity();
        newBatteryComponent.setBatteryId(battery.getBatteryId());
        newBatteryComponent.setComponentId(component.getComponentId());
        newBatteryComponent.setCountComponents(count);
        return newBatteryComponent;
    }

    private boolean checkValues() {
        if (txtfMark.getText().trim().length()==0 || txtfCapacity.getText().trim().length()==0 ||
                txtfAmperage.getText().trim().length() == 0 || txtfLead.getText().trim().length() == 0 ||
                txtfSeparator.getText().trim().length() == 0 || txtfPlatePlus.getText().trim().length() == 0 ||
                txtfPlateMinus.getText().trim().length() == 0){
            DialogManager.showErrorDialog("Ошибка", "Заполните все поля");
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

    private void selectFirstItem() {
        chcbPatch.getSelectionModel().selectFirst();
        chcbHandle.getSelectionModel().selectFirst();
        chcbPolarity.getSelectionModel().selectFirst();
    }
}
