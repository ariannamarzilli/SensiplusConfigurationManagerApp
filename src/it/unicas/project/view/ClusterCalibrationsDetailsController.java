package it.unicas.project.view;

import it.unicas.project.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;



public class ClusterCalibrationsDetailsController {

    private Stage dialogStage;
    private Cluster cluster;

    @FXML private TableView<SensingElementWithCalibration> parameterTableView = new TableView<>();
    @FXML private TableColumn<ChipWithCalibration, String> chipColumn;
    @FXML private TableColumn<SensingElementWithCalibration, String> sensingElementColumn;
    @FXML private TableColumn<SensingElementWithCalibration, String> mColumn;
    @FXML private TableColumn<SensingElementWithCalibration, String> nColumn;

    @FXML
    private void initialize() {

        sensingElementColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdSensingElement()));
        mColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getM().toString()));
        nColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getN().toString()));

        mColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<SensingElementWithCalibration, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<SensingElementWithCalibration, String> event) {
                        event
                                .getTableView()
                                .getItems()
                                .get(
                                        event.getTablePosition()
                                             .getRow()
                                )
                                .setM(
                                        Integer.parseInt(event.getNewValue())
                                );
                    }
                }
        );

        nColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SensingElementWithCalibration, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<SensingElementWithCalibration, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setN(Integer.parseInt(event.getNewValue()));
            }
        });

    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
        ObservableList<SensingElementWithCalibration> sensingElementWithCalibrations = FXCollections.observableArrayList();
        this.cluster.getChipWithCalibrations().forEach( c -> c.getSensingElementWithCalibrations().forEach( s -> sensingElementWithCalibrations.add(s)));
        parameterTableView.setItems(sensingElementWithCalibrations);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleSave() {

    }
}
