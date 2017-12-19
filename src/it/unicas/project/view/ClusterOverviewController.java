package it.unicas.project.view;
/*
import it.unicas.project.MainApp;
import it.unicas.project.dao.ClusterDAO;
import it.unicas.project.model.Chip;
import it.unicas.project.model.ChipWithCalibration;
import it.unicas.project.model.Cluster;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.util.List;


public class ClusterOverviewController {

    @FXML private TableView<Cluster> idClusterTableView;
    @FXML private TableColumn<Cluster, String> idClusterColumn;

    @FXML private TableView<Chip> chipTableView;
    @FXML private TableColumn<Chip, String> idChipColumn;

    @FXML private TableView<ChipWithCalibration> calibrationTableView;

    ObservableList<Cluster> clusterData;
    private MainApp mainApp;

    @FXML
    private void initialize() {
        List<Cluster> clusters = ClusterDAO.getInstance().fetchAll();

        ObservableList<Cluster> clusterObservableList = FXCollections.observableArrayList();

        for (int i = 0; i < clusters.size(); i++) {
            clusters.get(i).checkNullField();
            clusterObservableList.add(clusters.get(i));
        }

        this.setClusterData(clusterObservableList);

        idClusterColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));

        idClusterTableView.setItems(clusterData);
    }

    @FXML
    private void handleNew() {
        Cluster clusterToModify = new Cluster();
        Cluster oldCluster = new Cluster();
        mainApp.showClusterEditDialog(clusterToModify, false);

        if (!clusterToModify.equals(oldCluster)) {
            ClusterDAO.getInstance().create(clusterToModify);
            clusterData.add(clusterToModify);

            idClusterTableView.setItems(clusterData);
        }
    }

    @FXML
    private void handleDelete() {
        int selectedIndex = idClusterTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Confirm delete");
            alert.setHeaderText("Are you sure you want to delete this item?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                Cluster cluster = idClusterTableView.getItems().get(selectedIndex);
                ClusterDAO.getInstance().delete(cluster);

                for (int i = 0; i < clusterData.size(); i++) {
                    if (clusterData.get(i).getId().equals(cluster.getId())) {
                        clusterData.remove(i);
                    }
                }

                idClusterTableView.setItems(clusterData);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Cluster Selected");
            alert.setContentText("Please select a Cluster in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleClickOnIdCluster(MouseEvent event) {

        if (!clusterData.isEmpty() && idClusterTableView.getSelectionModel().getSelectedItem() != null) {

            Cluster cluster = idClusterTableView.getSelectionModel().getSelectedItem();

            if (event.getClickCount() == 1) {

                calibrationTableView.getItems().clear();
                chipTableView.getItems().clear();

                ObservableList<Chip> chips = FXCollections.observableArrayList();
                for (int i = 0; i < cluster.getChipWithCalibrations().size(); i++) {
                    chips.add(cluster.getChipWithCalibrations().get(i).getChip());
                }

                idChipColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
                chipTableView.setItems(chips);

            } else if (event.getClickCount() == 2) {

                Cluster oldCluster = new Cluster(cluster);
                mainApp.showClusterEditDialog(cluster, true);
                if (!cluster.equals(oldCluster)) {
                    ClusterDAO.getInstance().update(cluster);

                    for (int i = 0; i < clusterData.size(); i++) {
                        if (clusterData.get(i).getId().equals(cluster.getId()))){
                            clusterData.remove(i);
                        }
                    }

                    clusterData.add(cluster);
                    idClusterTableView.setItems(clusterData);
                }
            }
        }
    }

    @FXML
    private void handleClickOnChip(MouseEvent event) {


    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setClusterData(ObservableList<Cluster> clusterData) {
        this.clusterData = clusterData;
    }
}
*/
