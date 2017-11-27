package it.unicas.project.view;

import it.unicas.project.MainApp;
import it.unicas.project.model.SensingElement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import it.unicas.project.dao.SensingElementDAO;



public class SensingElementOverviewController {

    @FXML
    private TableView<SensingElement> sensingElementTableView;
    @FXML
    private TableColumn<SensingElement, String> nameColumn;
    @FXML
    private Label nameLabel;
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


    boolean cancelClicked = false;

    private MainApp mainApp;


    private Stage dialogStage;
    private SensingElement sensingElement;
    private boolean okClicked = false;
    private boolean verifylen = true;


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        // Clear Colleghi details.
        showSensingElementDetails(null);

        // Listen for selection changes and show the Colleghi details when changed.
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
            nameLabel.setText(sensingElement.getId());
            rSenseLabel.setText(""+sensingElement.getrSense());
            inGainLabel.setText(""+sensingElement.getInGain());
            outGainLabel.setText(""+sensingElement.getOutGain());
            contactsLabel.setText(sensingElement.getContacts());
            frequencyLabel.setText(""+sensingElement.getFrequency());
            harmonicLabel.setText(sensingElement.getHarmonic());
            dcBiasLabel.setText(""+sensingElement.getDcBias());
            modeVILabel.setText(sensingElement.getModeVI());
            measureTechniqueLabel.setText(sensingElement.getMeasureTechnique());
            measureTypeLabel.setText(""+sensingElement.getMeasureType());
            filterLabel.setText(""+sensingElement.getFilter());
            phaseShiftModeLabel.setText(""+sensingElement.getPhaseShiftMode());
            phaseShiftLabel.setText(""+sensingElement.getPhaseShift());
            iqLabel.setText(""+sensingElement.getIq());
            conversionRateLabel.setText(""+sensingElement.getConversionRate());
            inPortADCLabel.setText(""+sensingElement.getInPortADC());
            nDataLabel.setText(""+sensingElement.getnData());
        } else {
            nameLabel.setText("");
            rSenseLabel.setText(""+0);
            inGainLabel.setText(""+0);
            outGainLabel.setText(""+0);
            contactsLabel.setText("");
            frequencyLabel.setText(""+0);
            harmonicLabel.setText("");
            dcBiasLabel.setText(""+0);
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
        boolean okClicked = mainApp.showSensingElementEditDialog(tempSensingElement);

        if (okClicked) {
            SensingElementDAO.getInstance().create(tempSensingElement);
            mainApp.getSensingElementData().add(tempSensingElement);
        }
    }

    @FXML
    private void handleUpdateSensingElement() {
        int selectedIndex = sensingElementTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            SensingElement sensingElement = sensingElementTableView.getItems().get(selectedIndex);
            SensingElementDAO.getInstance().update(sensingElement);
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
    private void handleEditSensingElement() {
        SensingElement selectedColleghi = sensingElementTableView.getSelectionModel().getSelectedItem();
        if (selectedColleghi != null) {
            boolean okClicked = mainApp.showSensingElementEditDialog(selectedColleghi);
            if (okClicked) {
                SensingElementDAO.getInstance().update(selectedColleghi);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Colleghi Selected");
            alert.setContentText("Please select a Colleghi in the table.");

            alert.showAndWait();
        }
    }

    public boolean isCancelClicked() {
        return cancelClicked;
    }


    @FXML
    private void handleCancel() {
            cancelClicked = true;
            dialogStage.close();
    }

    public void setSensingElement(SensingElement sensingElement) {
        this.sensingElement = sensingElement;
        nameColumn.setText(sensingElement.getId());

    }



}
