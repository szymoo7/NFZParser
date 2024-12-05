package org.gui.frontend;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import pl.backend.client.Middleman;
import pl.backend.client.enums.*;
import pl.backend.client.pojos.ProvisionsData;
import pl.backend.client.pojos.Queue;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    private static final List<Provinces> PROVINCES_LIST = Arrays.asList(Provinces.values());
    private static final List<Cases> CASES_LIST = Arrays.asList(Cases.values());
    private static final List<Gender> GENDERS_LIST = Arrays.asList(Gender.values());
    private static final List<Age> AGE_LIST = Arrays.asList(Age.values());
    private static final List<Privileges> PRIVILEGES_LIST = Arrays.asList(Privileges.values());
    private static final List<Announcements> ANNOUNCEMENTS = Arrays.asList(Announcements.values());
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
    @FXML
    private ChoiceBox<Provinces> provisionChoiceBox;
    @FXML
    private DatePicker provisionDatePickerFrom;
    @FXML
    private DatePicker provisionDatePickerTo;
    @FXML
    private TextField provisionMedicineProduct;
    @FXML
    private TextField provisionActiveSubstance;
    @FXML
    private TextField atcTextField;
    @FXML
    private ChoiceBox<Gender> provisionGender;
    @FXML
    private ChoiceBox<Age> provisionAge;
    @FXML
    private ChoiceBox<Privileges> provisionAdditional;
    @FXML
    private ChoiceBox<Announcements> provisionAnnouncement;
    @FXML
    private Button showProvisionsButton;
    @FXML
    private TableView<ProvisionsData> provisionTableView;
    @FXML
    private TableColumn<ProvisionsData, String> medicineProductColumn;
    @FXML
    private TableColumn<ProvisionsData, String> internationalNameColumn;
    @FXML
    private TableColumn<ProvisionsData, String> doseColumn;
    @FXML
    private TableColumn<ProvisionsData, String> packColumn;
    @FXML
    private TableColumn<ProvisionsData, Double> marketPriceColumn;
    @FXML
    private TableColumn<ProvisionsData, Double> patientPriceColumn;
    @FXML
    private Label loadingProvisionsLabel;
    @FXML
    private Label loadingPlacesLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        provincesChoiceBox.setItems(FXCollections.observableArrayList(PROVINCES_LIST));
        visitDateCase.setItems(FXCollections.observableArrayList(CASES_LIST));
        visitDateProvincesChoiceBox.setItems(FXCollections.observableArrayList(PROVINCES_LIST));
        provisionChoiceBox.setItems(FXCollections.observableArrayList(PROVINCES_LIST));
        provisionGender.setItems(FXCollections.observableArrayList(GENDERS_LIST));
        provisionAge.setItems(FXCollections.observableArrayList(AGE_LIST));
        provisionAdditional.setItems(FXCollections.observableArrayList(PRIVILEGES_LIST));
        provisionAnnouncement.setItems(FXCollections.observableArrayList(ANNOUNCEMENTS));
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
        medicineProductColumn.setCellValueFactory(new PropertyValueFactory<>("medicineProduct"));
        internationalNameColumn.setCellValueFactory(new PropertyValueFactory<>("internationalName"));
        doseColumn.setCellValueFactory(new PropertyValueFactory<>("dose"));
        packColumn.setCellValueFactory(new PropertyValueFactory<>("pack"));
        marketPriceColumn.setCellValueFactory(new PropertyValueFactory<>("marketPrice"));
        patientPriceColumn.setCellValueFactory(new PropertyValueFactory<>("patientPrice"));
    }

    @FXML
    protected void onShowPlacesButtonClick() {
        loadingPlacesLabel.setVisible(true);
        String cityName = cityTextField.getText().isEmpty() ? null : cityTextField.getText();
        String code = provincesChoiceBox.getValue() != null ? provincesChoiceBox.getValue().getCode() : null;

        nfzPlacesListView.getItems().clear();
        new Thread(() -> {
            List<String> localities = middleman.getLocalities(cityName, code);
            Platform.runLater(() -> {
                nfzPlacesListView.setItems(FXCollections.observableArrayList(localities));
                loadingPlacesLabel.setVisible(false);
            });

        }).start();
    }

    @FXML
    protected void onShowDatesButtonClick() {
        int status = visitDateCase.getValue()
                != null ? visitDateCase.getValue().getNumber() : 0;
        String provinceCode = visitDateProvincesChoiceBox.getValue()
                != null ? visitDateProvincesChoiceBox.getValue().getCode() : null;
        String benefitName = visitDateBenefitNameTextField.getText().isEmpty()
                ? null : visitDateBenefitNameTextField.getText();
        boolean forChildren = benefitForChildrenCheckBox.isSelected();
        String providerName = visitDateProviderNameTextField.getText().isEmpty()
                ? null : visitDateProviderNameTextField.getText();
        String providerPlaceName = visitDateProviderPlaceNameTextField.getText().isEmpty()
                ? null : visitDateProviderPlaceNameTextField.getText();
        String providerPlaceStreetName = visitDateProviderPlaceStreetNameTextField.getText().isEmpty()
                ? null : visitDateProviderPlaceStreetNameTextField.getText();
        String providerCityName = visitDateProviderCityNameTextField.getText().isEmpty()
                ? null : visitDateProviderCityNameTextField.getText();
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

    @FXML
    protected void onShowProvisionsButtonClick() {
        loadingProvisionsLabel.setVisible(true);
        String provinceCode = provisionChoiceBox.getValue()
                != null ? provisionChoiceBox.getValue().getCode() : null;
        LocalDateTime dateFrom = provisionDatePickerFrom.getValue()
                != null ? provisionDatePickerFrom.getValue().atStartOfDay() : null;
        LocalDateTime dateTo = provisionDatePickerTo.getValue()
                != null ? provisionDatePickerTo.getValue().atStartOfDay() : null;
        String medicineProduct = provisionMedicineProduct.getText().isEmpty()
                ? null : provisionMedicineProduct.getText();
        String activeSubstance = provisionActiveSubstance.getText().isEmpty()
                ? null : provisionActiveSubstance.getText();
        String atc = atcTextField.getText().isEmpty()
                ? null : atcTextField.getText();
        String gender = provisionGender.getValue() != null
                ? provisionGender.getValue().getCode() : null;
        int ageGroup = provisionAge.getValue()
                != null ? provisionAge.getValue().getNumber() : 0;
        String privilegesAdditional = provisionAdditional.getValue() != null
                ? provisionAdditional.getValue().getCode() : null;
        String announcement = provisionAnnouncement.getValue() != null
                ? provisionAnnouncement.getValue().getCode() : null;
        new Thread(() -> {
            List<ProvisionsData> drugs = middleman.getProvisions(provinceCode, dateFrom, dateTo, medicineProduct,
                    activeSubstance, atc, gender, ageGroup, privilegesAdditional, announcement);
            Platform.runLater(() -> {
                    provisionTableView.setItems(FXCollections.observableArrayList(drugs));
                    loadingProvisionsLabel.setVisible(false);
            });
        }).start();
    }
}