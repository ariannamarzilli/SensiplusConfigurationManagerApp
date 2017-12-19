package it.unicas.project.view;

import it.unicas.project.MainApp;
import it.unicas.project.dao.ChipDAO;
import it.unicas.project.dao.FamilyDAO;
import it.unicas.project.model.Chip;
import it.unicas.project.model.Family;
import it.unicas.project.model.Port;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.util.List;


public class ChipOverviewController {

    @FXML private TableView<Chip> chipTableView = new TableView<>();

    @FXML private TableColumn<Chip, String> idColumn;

    @FXML private TableColumn<Chip, String> familyColumn;

    @FXML private TableView<Port> SensingElementOnPortTableView = new TableView<>();

    @FXML private TableColumn<Port, String> portColumn;

    @FXML private TableColumn<Port, String> sensingElementColumn;

    private ObservableList<Chip> chipData;
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
        mainApp.showChipEditDialog(tempChip, false);
        if (!tempChip.equals(oldChip)) {
            ChipDAO.getInstance().create(tempChip);
            this.chipData.add(tempChip);
            chipTableView.setItems(chipData);
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
            Chip clickedChip = chipTableView.getSelectionModel().getSelectedItem();

            if (event.getClickCount() == 1) {

                List<Family> families = FamilyDAO.getInstance().fetchAll();
                Family choosedFamily = new Family();

                for (int i = 0; i < families.size(); i++) {
                    if (families.get(i).getName().equals(clickedChip.getFamilyName())) {
                        choosedFamily.setPorts(families.get(i).getPorts());
                    }
                }

                ObservableList<Port> ports = FXCollections.observableArrayList(choosedFamily.getPorts());

                portColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
                sensingElementColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdSensingElement()));

                SensingElementOnPortTableView.setItems(ports);

            } else if (event.getClickCount() == 2) {
                Chip oldChip = new Chip(clickedChip);
                mainApp.showChipEditDialog(clickedChip, true);
                if (!clickedChip.equals(oldChip)) {
                    ChipDAO.getInstance().update(clickedChip);

                    for (int i = 0; i < chipData.size(); i++) {
                        if (chipData.get(i).getId().equals(clickedChip.getId())) {
                            chipData.remove(i);
                        }
                    }
                    this.chipData.add(clickedChip);
                    chipTableView.setItems(chipData);
                }
            }
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setChipData(ObservableList<Chip> chipData) {
        this.chipData = chipData;
    }

}
