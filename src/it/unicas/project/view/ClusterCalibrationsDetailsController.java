package it.unicas.project.view;

import it.unicas.project.MainApp;
import it.unicas.project.model.*;
import it.unicas.project.util.CSMN;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class ClusterCalibrationsDetailsController {

    private MainApp mainApp;
    private Stage dialogStage;
    private Cluster cluster;
    private boolean isAnUpdate;
    private ObservableList<CSMN> csmnObservableList = FXCollections.observableArrayList();

    @FXML private TableView<CSMN> parameterTableView = new TableView<>();
    @FXML private TableColumn<CSMN, String> chipColumn;
    @FXML private TableColumn<CSMN, String> sensingElementColumn;
    @FXML private TableColumn<CSMN, String> mColumn;
    @FXML private TableColumn<CSMN, String> nColumn;

    @FXML
    private void initialize() {


        parameterTableView.setFixedCellSize(35);
        parameterTableView.setEditable(true);

        chipColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getChip().getId()));
        sensingElementColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdSensingElement()));
        mColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getM().toString()));
        nColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getN().toString()));

        parameterTableView.getSelectionModel().cellSelectionEnabledProperty().setValue(true);

        mColumn.setEditable(true);
        mColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CSMN, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<CSMN, String> event) {
                        event.getTableView().getItems().get(event.getTablePosition().getRow()).setM(Integer.parseInt(event.getNewValue()));
                    }
                }
        );

        nColumn.setEditable(true);
        nColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CSMN, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CSMN, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setN(Integer.parseInt(event.getNewValue()));
            }
        });

    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;

        for (int i = 0; i < cluster.getChipWithCalibrations().size(); i++) {
            for (int j = 0; j < cluster.getChipWithCalibrations().get(i).getSensingElementWithCalibrations().size(); j++) {
                CSMN csmn = new CSMN();
                csmn.setChip(cluster.getChipWithCalibrations().get(i).getChip());
                csmn.setIdSensingElement(cluster.getChipWithCalibrations().get(i).getSensingElementWithCalibrations().get(j).getIdSensingElement());
                csmn.setM(cluster.getChipWithCalibrations().get(i).getSensingElementWithCalibrations().get(j).getM());
                csmn.setN(cluster.getChipWithCalibrations().get(i).getSensingElementWithCalibrations().get(j).getN());
                csmnObservableList.add(csmn);
            }
        }

        parameterTableView.setItems(csmnObservableList);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
        mainApp.setCancelPressed(true);
    }

    @FXML
    private void handleSave() {

        mainApp.setCancelPressed(false);
        for (int i = 0; i < csmnObservableList.size(); i++) {

            for (int j = 0; j < cluster.getChipWithCalibrations().size(); j++) {
                for (int h = 0; h < cluster.getChipWithCalibrations().get(j).getSensingElementWithCalibrations().size(); h++) {

                    if (csmnObservableList.get(i).getChip().getId().equals(cluster.getChipWithCalibrations().get(j).getChip().getId()) &&
                            csmnObservableList.get(i).getIdSensingElement().equals(cluster.getChipWithCalibrations().get(j).getSensingElementWithCalibrations().get(h).getIdSensingElement())) {
                        cluster.getChipWithCalibrations().get(j).getSensingElementWithCalibrations().get(h).setM(csmnObservableList.get(i).getM());
                        cluster.getChipWithCalibrations().get(j).getSensingElementWithCalibrations().get(h).setN(csmnObservableList.get(i).getN());
                    }
                }
            }
        }

        dialogStage.close();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}

