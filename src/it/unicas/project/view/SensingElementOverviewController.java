package it.unicas.project.view;

import it.unicas.project.MainApp;
import it.unicas.project.dao.SensingElementDAO;
import it.unicas.project.model.SensingElement;
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

public class SensingElementOverviewController {

    @FXML
    private TableView<SensingElement> sensingElementTableView = new TableView<>();
    @FXML
    private TableColumn<SensingElement, String> idColumn;
    @FXML
    private TableColumn<SensingElement, String> nameColumn;
    @FXML
    private TableColumn<SensingElement, String> measureTechniqueColumn;
    @FXML
    private TableColumn<SensingElement, String> filterColumn;
    @FXML
    private TableColumn<SensingElement, String> conversionRateColumn;
    @FXML
    private TableColumn<SensingElement, String> inPortADCColumn;
    @FXML
    private TableColumn<SensingElement, String> nDataColumn;
    @FXML
    private TableColumn<SensingElement, String> rangeMinColumn;
    @FXML
    private TableColumn<SensingElement, String> rangeMaxColumn;
    @FXML
    private TableColumn<SensingElement, String> defaultAlarmThresholdColumn;
    @FXML
    private TableColumn<SensingElement, String> multiplierColumn;
    @FXML
    private TableColumn<SensingElement, String> measureUnitColumn;

    private MainApp mainApp;
    ObservableList<SensingElement> sensingElementsData;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        SensingElementDAO sensingElementDAO = new SensingElementDAO();
        List<SensingElement> sensingElements = sensingElementDAO.fetchAll();

        // Defining table data in an Observable List
        ObservableList<SensingElement> sensingElementObservableList = FXCollections.observableArrayList();

        for (int i = 0; i < sensingElements.size(); i++) {
            sensingElements.get(i).checkNullField();
            sensingElementObservableList.add(sensingElements.get(i));
        }

        this.setSensingElementsData(sensingElementObservableList);

        // Associating the data with the table columns
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        measureTechniqueColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMeasureTechnique()));
        filterColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFilter().toString()));
        conversionRateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getConversionRate().toString()));
        inPortADCColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInPortADC()));
        nDataColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getnData().toString()));
        rangeMinColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRangeMin().toString()));
        rangeMaxColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRangeMax().toString()));
        defaultAlarmThresholdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDefaultAlarmThreshold().toString()));
        multiplierColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMultiplier().toString()));
        measureUnitColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMeasureUnit()));

        sensingElementTableView.setItems(sensingElementsData);
    }

    public void setSensingElementsData (ObservableList<SensingElement> sensingElementsData) {
        this.sensingElementsData = sensingElementsData;
    }

    @FXML
    public void handleNew() {
        SensingElement tempSensingElement = new SensingElement();
        SensingElement oldSensingElement = new SensingElement();
        mainApp.showSensingElementEditDialog(tempSensingElement, false);

        if (!tempSensingElement.equals(oldSensingElement)) {
            SensingElementDAO.getInstance().create(tempSensingElement);
            sensingElementsData.add(tempSensingElement);

            sensingElementTableView.setItems(sensingElementsData);
        }
    }

    @FXML
    public void handleDelete() {
        int selectedIndex = sensingElementTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Confirm delete");
            alert.setHeaderText("Are you sure you want to delete this item?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                SensingElement sensingElement = sensingElementTableView.getItems().get(selectedIndex);
                SensingElementDAO.getInstance().delete(sensingElement);

                for (int i = 0; i < sensingElementsData.size(); i++) {
                    if (sensingElementsData.get(i).getId().equals(sensingElement.getId())) {
                        sensingElementsData.remove(i);
                    }
                }

                sensingElementTableView.setItems(sensingElementsData);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Sensing Element Selected");
            alert.setContentText("Please select a Sensing Element in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    public void handleDoubleClick(MouseEvent event) {
        if (event.getClickCount() == 2 && (!sensingElementsData.isEmpty()) && sensingElementTableView.getSelectionModel().getSelectedItem() != null) {
            SensingElement sensingElement = sensingElementTableView.getSelectionModel().getSelectedItem();
            SensingElement oldSensingElement = new SensingElement(sensingElement);
            mainApp.showSensingElementEditDialog(sensingElement, true);
            if (!sensingElement.equals(oldSensingElement)) {
                SensingElementDAO.getInstance().update(sensingElement);

                sensingElementTableView.setItems(sensingElementsData);
                sensingElementTableView.refresh();
            }
        }
    }

}
