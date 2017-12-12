package it.unicas.project.view;

/*
import it.unicas.project.MainApp;
import it.unicas.project.dao.ChipDAO;
import it.unicas.project.model.Chip;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.util.Iterator;
*/

public class ChipOverviewController {

    /*
    @FXML
    private TableView<Chip> chipTableView = new TableView<>();

    @FXML
    private TableColumn<Chip, String> idColumn;

    @FXML
    private TableColumn<Chip, String> familyColumn;

    ObservableList<Chip> chipData;
    private MainApp mainApp;

    @FXML
    private void initialize() {
        ChipDAO chipDAO = new ChipDAO();
        Iterable<Chip> chips = chipDAO.fetchAll();
        Iterator<Chip> iterator = chips.iterator();

        ObservableList<Chip> chipObservableList = FXCollections.observableArrayList();


        while(iterator.hasNext()) {
            Chip chip = iterator.next();
            chip.checkNullField(); //se m e n sono nulli nel database, per non trattare con handler null gli assegli Integer.MAX_VALUE
            chipObservableList.add(chip);
        }

        this.setChipData(chipObservableList);

        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        familyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFamilyId()));
    }

    @FXML
    private void handleNew() {
        Chip tempChip = new Chip();
        Chip oldChip = new Chip();
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

        if ((!chipData.isEmpty()) && chipTableView.getSelectionModel().getSelectedItem() != null) {

            Chip chip = chipTableView.getSelectionModel().getSelectedItem();

            if (event.getClickCount() == 1) {



            } else if (event.getClickCount() == 2) {
                Chip oldChip = new Chip(Chip);
                mainApp.showFamilyEditDialog(chip);
                if (!chip.equals(oldChip)) {
                    ChipDAO.getInstance().update(chip);
                    mainApp.getFamilyData().add(chip);
                }
            }
        }
    }

    @FXML
    private void handleClickOnSensingElement() {

    }

    @FXML
    private void handleClickOnCalibrationName() {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setChipData(ObservableList<Chip> chipData) {
        this.chipData = chipData;
    }

    */
}
