package it.unicas.project.view;

import it.unicas.project.MainApp;
import it.unicas.project.dao.ChipDAO;
import it.unicas.project.model.Chip;
import it.unicas.project.model.SensingElementWithCalibration;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.util.Iterator;
import java.util.List;


public class ChipOverviewController {

    @FXML
    private TableView<Chip> chipTableView = new TableView<>();

    @FXML
    private TableColumn<Chip, String> idColumn;

    @FXML
    private TableColumn<Chip, String> familyColumn;

    @FXML
    private ListView<String> sensingElementList;

    @FXML
    private ListView<String> calibrationNameList;

    @FXML
    private ListView<Integer> calibrationParametersList;

    private ObservableList<Chip> chipData;

    private Chip clickedChip;
    private SensingElementWithCalibration clickedSensingElementWithCalibrations;

    private MainApp mainApp;

    @FXML
    private void initialize() {

        ChipDAO chipDAO = new ChipDAO();
        List<Chip> chips = chipDAO.fetchAll();

        ObservableList<Chip> chipObservableList = FXCollections.observableArrayList();

        for (int i = 0; i < chips.size(); i++) {
            chips.get(i).checkNullField();
            chipObservableList.add(chips.get(i));
        }

        this.setChipData(chipObservableList);

        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        familyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFamilyName()));

        chipTableView.setItems(chipData);
    }

    @FXML
    private void handleNew() {
        Chip tempChip = new Chip();
        Chip oldChip = new Chip(tempChip);
        mainApp.showChipEditDialog(tempChip);
        if (!tempChip.equals(oldChip)) {
            ChipDAO.getInstance().create(tempChip);
            mainApp.getChipData().add(tempChip);
        }
    }

    @FXML
    private void handleDelete() {

        int selectedIndex = chipTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Confirm delete");
            alert.setHeaderText("Are you sure you want to delete this item?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                Chip chip = chipTableView.getItems().get(selectedIndex);
                ChipDAO.getInstance().delete(chip);
                chipTableView.getItems().remove(selectedIndex);
            }
        }

    }

    @FXML
    private void handleClickOnChip(MouseEvent event) {

        sensingElementList.getSelectionModel().clearSelection();
        calibrationNameList.getSelectionModel().clearSelection();
        calibrationParametersList.getSelectionModel().clearSelection();

        if ((!chipData.isEmpty()) && chipTableView.getSelectionModel().getSelectedItem() != null) {
            clickedChip = chipTableView.getSelectionModel().getSelectedItem();

            if (event.getClickCount() == 1) {

                ObservableList<String> sensingElements = FXCollections.observableArrayList();

                for (int i = 0; i < clickedChip.getSensingElementWithCalibrations().size(); i++) {
                    sensingElements.add(clickedChip.getSensingElementWithCalibrations().get(i).getIdSensingElement());
                }

                if (sensingElements.size() != 0) {
                    sensingElementList.setItems(sensingElements);
                }

            } else if (event.getClickCount() == 2) {
                Chip oldChip = new Chip(clickedChip);   //copia
                mainApp.showChipEditDialog(clickedChip);
                if (!clickedChip.equals(oldChip)) {
                    ChipDAO.getInstance().update(clickedChip);
                    mainApp.getChipData().add(clickedChip);
                }
            }
        }
    }

    @FXML
    private void handleClickOnSensingElement() {

        if (!sensingElementList.getSelectionModel().getSelectedItems().isEmpty()) {

            String sensingElementIdClicked = sensingElementList.getSelectionModel().getSelectedItem();
            ObservableList<String> calibrationNames = FXCollections.observableArrayList();
            clickedSensingElementWithCalibrations = new SensingElementWithCalibration();

            for (int i = 0; i < clickedChip.getSensingElementWithCalibrations().size(); i++) {
                if (clickedChip.getSensingElementWithCalibrations().get(i).getIdSensingElement().equals(sensingElementIdClicked)) {
                    clickedSensingElementWithCalibrations = clickedChip.getSensingElementWithCalibrations().get(i);
                }
            }

            for (int i = 0; i < clickedSensingElementWithCalibrations.getCalibrationList().size(); i++) {
                calibrationNames.add(clickedSensingElementWithCalibrations.getCalibrationList().get(i).getName());
            }

            if (calibrationNames.size() != 0) {
                calibrationNameList.setItems(calibrationNames);
            }

        }
    }

    @FXML
    private void handleClickOnCalibrationName() {

        if (!calibrationNameList.getSelectionModel().getSelectedItems().isEmpty()) {

            String calibrationNameClicked = calibrationNameList.getSelectionModel().getSelectedItem();
            ObservableList<Integer> parameters = FXCollections.observableArrayList();

            for (int i = 0; i < clickedSensingElementWithCalibrations.getCalibrationList().size(); i++) {
                if (clickedSensingElementWithCalibrations.getCalibrationList().get(i).getName().equals(calibrationNameClicked)) {
                    parameters.add(clickedSensingElementWithCalibrations.getCalibrationList().get(i).getM());
                    parameters.add(clickedSensingElementWithCalibrations.getCalibrationList().get(i).getN());
                }
            }

            calibrationParametersList.setItems(parameters);

        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setChipData(ObservableList<Chip> chipData) {
        this.chipData = chipData;
    }
}
