package it.unicas.project.view;


import it.unicas.project.MainApp;
import it.unicas.project.dao.ChipDAO;
import it.unicas.project.model.Chip;
import it.unicas.project.model.SensingElementOnChip;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.util.Iterator;


public class ChipOverviewController {

    @FXML
    private TableView<SensingElementOnChip> chipTableView = new TableView<>();

    @FXML
    private TableColumn<SensingElementOnChip, String> idColumn;

    @FXML
    private TableColumn<SensingElementOnChip, String> familyColumn;

    @FXML
    private ListView<String> sensingElementList;

    @FXML
    private ListView<String> calibrationNameList;

    @FXML
    private ListView<Integer> calibrationParametersList;

    ObservableList<SensingElementOnChip> sensingElementOnChipData;

    SensingElementOnChip sensingElementOnChipClicked = new SensingElementOnChip(new Chip());
    private MainApp mainApp;

    @FXML
    private void initialize() {

        ChipDAO chipDAO = new ChipDAO();
        Iterable<SensingElementOnChip> sensingElementOnChips = chipDAO.fetchAll();
        Iterator<SensingElementOnChip> iterator = sensingElementOnChips.iterator();

        ObservableList<SensingElementOnChip> sensingElementOnChipObservableList = FXCollections.observableArrayList();

        while(iterator.hasNext()) {
            SensingElementOnChip sensingElementOnChip = iterator.next();

            /*
            in questo punto bisogna fare un controllo sui valori nulli
             */

            sensingElementOnChipObservableList.add(sensingElementOnChip);
        }

        this.setSensingElementOnChipData(sensingElementOnChipObservableList);

        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getChip().getId()));
        familyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getChip().getId()));

    }

    @FXML
    private void handleNew() {
        SensingElementOnChip tempChip = new SensingElementOnChip(new Chip());
        SensingElementOnChip oldChip = new SensingElementOnChip(new Chip());
        mainApp.showChipEditDialog(tempChip);
        if (!tempChip.equals(oldChip)) {
            ChipDAO.getInstance().create(tempChip);
            mainApp.getSensingElementOnChipData().add(tempChip);
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
                SensingElementOnChip chip = chipTableView.getItems().get(selectedIndex);
                ChipDAO.getInstance().delete(chip);
                chipTableView.getItems().remove(selectedIndex);
            }
        }

    }

   /* @FXML
    private void handleClickOnChip(MouseEvent event) {

        sensingElementOnChipClicked.getCalibrationList().clear();
        sensingElementOnChipClicked.setIdSensingElement("");

        sensingElementList.getSelectionModel().clearSelection();
        calibrationNameList.getSelectionModel().clearSelection();
        calibrationParametersList.getSelectionModel().clearSelection();

        if ((!sensingElementOnChipData.isEmpty()) && chipTableView.getSelectionModel().getSelectedItem() != null) {
            SensingElementOnChip chipClicked = chipTableView.getSelectionModel().getSelectedItem();

            if (event.getClickCount() == 1) {


                ObservableList<String> sensingElements = FXCollections.observableArrayList();

                for (int i = 0; i < sensingElementOnChipData.size(); i++) {
                    if (sensingElementOnChipData.get(i).getChip().equals(chipClicked.getChip())) {

                        sensingElements.add(sensingElementOnChipData.get(i).getIdSensingElement());
                    }
                }

                if (sensingElements.size() != 0) {
                    sensingElementList.setItems(sensingElements);
                }

            } else if (event.getClickCount() == 2) {
                SensingElementOnChip oldChip = new SensingElementOnChip(chipClicked);   //copia
                mainApp.showChipEditDialog(chipClicked);
                if (!chipClicked.equals(oldChip)) {
                    ChipDAO.getInstance().update(chipClicked);
                    mainApp.getSensingElementOnChipData().add(chipClicked);
                }
            }
        }
    }*/

    @FXML
    private void handleClickOnSensingElement() {

        if (!sensingElementList.getSelectionModel().getSelectedItems().isEmpty()) {

            String sensingElementIdClicked = sensingElementList.getSelectionModel().getSelectedItems().toString();
            ObservableList<String> calibrationNames = FXCollections.observableArrayList();
            boolean found = false;
            int index = 0;

            while (!found && index < sensingElementOnChipData.size()) {
                if (sensingElementOnChipData.get(index).getIdSensingElement().equals(sensingElementIdClicked)) {
                    found = true;
                    sensingElementOnChipData.get(index).getCalibrationList().stream().forEach(calibration -> calibrationNames.add(calibration.getName()));
                    sensingElementOnChipClicked.setIdSensingElement(sensingElementIdClicked);
                    sensingElementOnChipClicked.setCalibrationList(sensingElementOnChipData.get(index).getCalibrationList());
                }

                index = index + 1;
            }

            if (calibrationNames.size() != 0) {
                calibrationNameList.setItems(calibrationNames);
            }

        }
    }

   /* @FXML
    private void handleClickOnCalibrationName() {

        if (!calibrationNameList.getSelectionModel().getSelectedItems().isEmpty()) {

            String calibrationName = calibrationNameList.getSelectionModel().getSelectedItem();
            ObservableList<Integer> parameters = FXCollections.observableArrayList();

            boolean found = false;
            int index = 0;

            while (!found && index <sensingElementOnChipClicked.getCalibrationList().size()) {

                if (sensingElementOnChipClicked.getCalibrationList().get(index).getName().equals(calibrationName)) {
                    found = true;
                    parameters.setAll(sensingElementOnChipClicked.getCalibrationList().get(index).getM(), sensingElementOnChipClicked.getCalibrationList().get(index).getN());
                }
                index++;
            }

            calibrationNameList.setItems(parameters);

        }
    }*/

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setSensingElementOnChipData(ObservableList<SensingElementOnChip> sensingElementOnChipData) {
        this.sensingElementOnChipData = sensingElementOnChipData;
    }
}
