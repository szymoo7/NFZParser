package org.gui.frontend;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import pl.backend.client.Middleman;
import pl.backend.client.enums.Case;
import pl.backend.client.enums.Provinces;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    private static Middleman middleman = new Middleman();
    private static final List<Provinces> PROVINCES_LIST = Arrays.asList(Provinces.values());

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
    private ListView<Queue> nfzDatesListView;
    @FXML
    private ChoiceBox<Provinces> visitDateProvincesChoiceBox;
    @FXML
    private ChoiceBox<Case> visitDateCase;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        provincesChoiceBox.setItems(FXCollections.observableArrayList(PROVINCES_LIST));
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
        // TODO

    }
}