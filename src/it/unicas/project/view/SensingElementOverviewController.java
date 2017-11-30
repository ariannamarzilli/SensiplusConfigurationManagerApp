package it.unicas.project.view;

import it.unicas.project.MainApp;
import it.unicas.project.model.SensingElement;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import it.unicas.project.dao.SensingElementDAO;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class SensingElementOverviewController {

    @FXML
    private TableView<SensingElement> sensingElementTableView;
    @FXML
    private TableColumn<SensingElement, String> nameColumn;
    @FXML
    private Label idLabel;
    @FXML
    private Label rSenseLabel;
    @FXML
    private Label inGainLabel;
    @FXML
    private Label outGainLabel;
    @FXML
    private Label contactsLabel;
    @FXML
    private Label frequencyLabel;
    @FXML
    private Label harmonicLabel;
    @FXML
    private Label dcBiasLabel;
    @FXML
    private Label modeVILabel;
    @FXML
    private Label measureTechniqueLabel;
    @FXML
    private Label measureTypeLabel;
    @FXML
    private Label filterLabel;
    @FXML
    private Label phaseShiftLabel;
    @FXML
    private Label phaseShiftModeLabel;
    @FXML
    private Label iqLabel;
    @FXML
    private Label conversionRateLabel;
    @FXML
    private Label inPortADCLabel;
    @FXML
    private Label nDataLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label rangeMinLabel;
    @FXML
    private Label rangeMaxLabel;
    @FXML
    private Label defaultAlarmThresholdLabel;
    @FXML
    private Label multiplierLabel;
    @FXML
    private Label measureUnit;


    boolean cancelClicked = false;

    private MainApp mainApp;


    private Stage dialogStage;
    private boolean okClicked = false;
    private boolean verifylen = true;
    Integer emptyIntegerForDirectMeasure = -2049;
    Double emptyDoubleForDirectMeasure = 2049.0;


    @FXML
    private void initialize() {

        SensingElementDAO sensingElementDAO = new SensingElementDAO();
        Iterable<SensingElement> sensingElements = sensingElementDAO.fetchAll();
        Iterator<SensingElement> iterator = sensingElements.iterator();
        ObservableList<SensingElement> sensingElementObservableList = FXCollections.observableArrayList();
        //sensingElementTableView.setItems(sensingElementObservableList);



        while (iterator.hasNext()) {
            SensingElement sensingElement = iterator.next();
            sensingElementObservableList.add(sensingElement);
        }

        this.setMainApp(mainApp);
        mainApp.setSensingElementData(sensingElementObservableList);


        // Initialize the sensing element table with the columns.
        nameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getName().toString())
        );

        // Clear sensing element details.
        showSensingElementDetails(null);





        // Listen for selection changes and show the Sensing Element details when changed.
        sensingElementTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSensingElementDetails(newValue));

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public SensingElementOverviewController() {
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        //Add observable list data to the table
        sensingElementTableView.setItems(mainApp.getSensingElementData());
    }

    private void showSensingElementDetails(SensingElement sensingElement) {
        if (sensingElement != null) {
            idLabel.setText(sensingElement.getId());
            rSenseLabel.setText(""+sensingElement.getrSense());
            inGainLabel.setText(""+sensingElement.getInGain());
            outGainLabel.setText(""+sensingElement.getOutGain());
            contactsLabel.setText(sensingElement.getContacts());
            if (sensingElement.getFrequency().equals(emptyDoubleForDirectMeasure)) {
                frequencyLabel.setText("");
            } else {
                frequencyLabel.setText(""+sensingElement.getFrequency());
            }
            harmonicLabel.setText(sensingElement.getHarmonic());
            if (sensingElement.getDcBias().equals(emptyIntegerForDirectMeasure)) {
                dcBiasLabel.setText("");
            } else {
                dcBiasLabel.setText(""+sensingElement.getDcBias());
            }
            modeVILabel.setText(sensingElement.getModeVI());
            measureTechniqueLabel.setText(sensingElement.getMeasureTechnique());
            measureTypeLabel.setText(""+sensingElement.getMeasureType());
            filterLabel.setText(""+sensingElement.getFilter());
            phaseShiftModeLabel.setText(""+sensingElement.getPhaseShiftMode());
            if (sensingElement.getPhaseShift().equals(emptyDoubleForDirectMeasure)) {
                phaseShiftLabel.setText("");
            } else {
                phaseShiftLabel.setText(""+sensingElement.getPhaseShift());
            }
            iqLabel.setText(""+sensingElement.getIq());
            conversionRateLabel.setText(""+sensingElement.getConversionRate());
            inPortADCLabel.setText(""+sensingElement.getInPortADC());
            nDataLabel.setText(""+sensingElement.getnData());
            nameLabel.setText(""+sensingElement.getName());
            rangeMinLabel.setText(""+sensingElement.getRangeMin());
            rangeMaxLabel.setText(""+sensingElement.getRangeMax());
            defaultAlarmThresholdLabel.setText(""+sensingElement.getDefaultAlarmThreshold());
            multiplierLabel.setText(""+sensingElement.getMultiplier());
            measureUnit.setText(""+sensingElement.getMeasureUnit());

        } else {
            idLabel.setText("");
            rSenseLabel.setText("");
            inGainLabel.setText("");
            outGainLabel.setText("");
            contactsLabel.setText("");
            frequencyLabel.setText("");
            harmonicLabel.setText("");
            dcBiasLabel.setText("");
            modeVILabel.setText("");
            measureTechniqueLabel.setText("");
            measureTypeLabel.setText("");
            filterLabel.setText("");
            phaseShiftLabel.setText("");
            phaseShiftLabel.setText("");
            iqLabel.setText("");
            conversionRateLabel.setText("");
            inPortADCLabel.setText("");
            nDataLabel.setText("");
            nameLabel.setText("");
            rangeMinLabel.setText("");
            rangeMaxLabel.setText("");
            defaultAlarmThresholdLabel.setText("");
            multiplierLabel.setText("");
            measureUnit.setText("");
        }
    }

    //called when the user clicks on a button named delete
    @FXML
    private void handleDeleteSensingElement() {
        int selectedIndex = sensingElementTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            SensingElement sensingElement = sensingElementTableView.getItems().get(selectedIndex);

            SensingElementDAO.getInstance().delete(sensingElement);
            sensingElementTableView.getItems().remove(selectedIndex);

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Sensing Element Selected");
            alert.setContentText("Please select a Sensing Element in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleCreateSensingElement() {
        SensingElement tempSensingElement = new SensingElement();
        mainApp.showSensingElementEditDialog(tempSensingElement);

        SensingElementDAO.getInstance().create(tempSensingElement);
        mainApp.getSensingElementData().add(tempSensingElement);

    }

    @FXML
    private void handleUpdateSensingElement() {
        int selectedIndex = sensingElementTableView.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            SensingElement sensingElement = sensingElementTableView.getItems().get(selectedIndex);
            mainApp.showSensingElementEditDialog(sensingElement);
            SensingElementDAO.getInstance().update(sensingElement);
            showSensingElementDetails(sensingElement);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Sensing Element Selected");
            alert.setContentText("Please select a Sensing Element in the table.");
            alert.showAndWait();
        }
    }

    /*@FXML
    private void handleEditSensingElement() {
        SensingElement selectedColleghi = sensingElementTableView.getSelectionModel().getSelectedItem();
        if (selectedColleghi != null) {
            mainApp.showSensingElementEditDialog(selectedColleghi);

            SensingElementDAO.getInstance().update(selectedColleghi);


        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Colleghi Selected");
            alert.setContentText("Please select a Colleghi in the table.");

            alert.showAndWait();
        }
    }*/

    public boolean isCancelClicked() {
        return cancelClicked;
    }


    @FXML
    private void handleCancel() {
            cancelClicked = true;
            dialogStage.close();
    }




}
