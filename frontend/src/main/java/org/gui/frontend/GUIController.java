package org.gui.frontend;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import pl.backend.client.Middleman;
import pl.backend.client.enums.Cases;
import pl.backend.client.enums.Provinces;
import pl.backend.client.pojos.Queue;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    private static final List<Provinces> PROVINCES_LIST = Arrays.asList(Provinces.values());
    private static final List<Cases> CASES_LIST = Arrays.asList(Cases.values());
    private static final Middleman middleman = new Middleman();

    @FXML
    private ImageView nfzAlpha;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab visitDate;
    @FXML
    private Tab stats;
    @FXML
    private Tab drugsRefunds;
    @FXML
    private Tab nfzPlaces;
    @FXML
    private ListView<String> nfzPlacesListView;
    @FXML
    private TextField cityTextField;
    @FXML
    private ChoiceBox<Provinces> provincesChoiceBox;
    @FXML
    private Button showPlacesButton;
    @FXML
    private Button showDatesButton;
    @FXML
    private TableView<Queue> nfzDatesTableView;
    @FXML
    private TableColumn<Queue, String> benefitColumn;
    @FXML
    private TableColumn<Queue, String> providerColumn;
    @FXML
    private TableColumn<Queue, String> placeColumn;
    @FXML
    private TableColumn<Queue, String> addressColumn;
    @FXML
    private TableColumn<Queue, String> localityColumn;
    @FXML
    private TableColumn<Queue, String> phoneColumn;
    @FXML
    private TableColumn<Queue, String> dateColumn;
    @FXML
    private ChoiceBox<Provinces> visitDateProvincesChoiceBox;
    @FXML
    private ChoiceBox<Cases> visitDateCase;
    @FXML
    private TextField visitDateBenefitNameTextField;
    @FXML
    private CheckBox benefitForChildrenCheckBox;
    @FXML
    private TextField visitDateProviderNameTextField;
    @FXML
    private TextField visitDateProviderPlaceNameTextField;
    @FXML
    private TextField visitDateProviderPlaceStreetNameTextField;
    @FXML
    private TextField visitDateProviderCityNameTextField;
    @FXML
    private Label loadingText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        provincesChoiceBox.setItems(FXCollections.observableArrayList(PROVINCES_LIST));
        visitDateCase.setItems(FXCollections.observableArrayList(CASES_LIST));
        visitDateProvincesChoiceBox.setItems(FXCollections.observableArrayList(PROVINCES_LIST));
        benefitColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAttributes().getBenefit()));
        providerColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAttributes().getProvider()));
        placeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAttributes().getPlace()));
        addressColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAttributes().getAddress()));
        localityColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAttributes().getLocality()));
        phoneColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAttributes().getPhone()));
        dateColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAttributes().getDate()));
    }

    @FXML
    protected void onShowPlacesButtonClick() {
        String cityName = cityTextField.getText();
        String code = provincesChoiceBox.getValue().getCode();
        if (cityName.isEmpty()) {
            nfzPlacesListView.getItems().clear();
            return;
        }
        nfzPlacesListView.getItems().clear();
        middleman.getLocalities(cityName, code).forEach(nfzPlacesListView.getItems()::add);
    }

    @FXML
    protected void onShowDatesButtonClick() {
        int status = visitDateCase.getValue().getNumber();
        String provinceCode = visitDateProvincesChoiceBox.getValue().getCode();
        String benefitName = visitDateBenefitNameTextField.getText();
        boolean forChildren = benefitForChildrenCheckBox.isSelected();
        String providerName = visitDateProviderNameTextField.getText();
        String providerPlaceName = visitDateProviderPlaceNameTextField.getText();
        String providerPlaceStreetName = visitDateProviderPlaceStreetNameTextField.getText();
        String providerCityName = visitDateProviderCityNameTextField.getText();
        loadingText.setVisible(true);

        new Thread(() -> {
            List<Queue> queues = middleman.getQueues(status, provinceCode, benefitName,
                    forChildren, providerName, providerPlaceName, providerPlaceStreetName, providerCityName);
            Platform.runLater(() -> {
                nfzDatesTableView.setItems(FXCollections.observableArrayList(queues));
                loadingText.setVisible(false);
            });
        }).start();
    }
}