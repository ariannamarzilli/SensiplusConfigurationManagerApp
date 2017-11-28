package it.unicas.project.view;

import it.unicas.project.model.SensingElement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.soap.Text;


public class SensingElementEditDialogController {
    @FXML
    private TextField idTextField;
    @FXML
    private TextField rSenseTextField;
    @FXML
    private TextField inGainTextField;
    @FXML
    private TextField outGainTextField;
    @FXML
    private TextField contactsTextField;
    @FXML
    private TextField frequencyTextField;
    @FXML
    private TextField harmonicTextField;
    @FXML
    private TextField dcBiasTextField;
    @FXML
    private TextField modeVITextField;
    @FXML
    private TextField measureTechniqueTextField;
    @FXML
    private TextField measureTypeTextField;
    @FXML
    private TextField filterTextField;
    @FXML
    private TextField phaseShiftModeTextField;
    @FXML
    private TextField phaseShiftTextField;
    @FXML
    private TextField iqTextField;
    @FXML
    private TextField conversionRateTextField;
    @FXML
    private TextField inPortADCTextField;
    @FXML
    private TextField nDataTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField rangeMinTextField;
    @FXML
    private TextField rangeMaxTextField;
    @FXML
    private TextField defaultAlarmThresholdTextField;
    @FXML
    private TextField multiplierTextField;
    @FXML
    private TextField measureUnitTextField;


    private Stage dialogStage;
    private SensingElement sensingElement;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param sensingElement
     */
    public void setSensingElement(SensingElement sensingElement) {
        this.sensingElement = sensingElement;

        idTextField.setText(sensingElement.getId());
        rSenseTextField.setText(""+sensingElement.getrSense());
        inGainTextField.setText(""+sensingElement.getInGain());
        outGainTextField.setText(""+sensingElement.getOutGain());
        contactsTextField.setText(sensingElement.getContacts());
        frequencyTextField.setText(""+sensingElement.getFrequency());
        harmonicTextField.setText(sensingElement.getHarmonic());
        dcBiasTextField.setText(""+sensingElement.getDcBias());
        modeVITextField.setText(sensingElement.getModeVI());
        measureTechniqueTextField.setText(sensingElement.getMeasureTechnique());
        measureTypeTextField.setText(""+sensingElement.getMeasureType());
        filterTextField.setText(""+sensingElement.getFilter());
        phaseShiftModeTextField.setText(""+sensingElement.getPhaseShiftMode());
        phaseShiftTextField.setText(""+sensingElement.getPhaseShift());
        iqTextField.setText(""+sensingElement.getIq());
        conversionRateTextField.setText(""+sensingElement.getConversionRate());
        inPortADCTextField.setText(""+sensingElement.getInPortADC());
        nDataTextField.setText(""+sensingElement.getnData());
        nameTextField.setText(sensingElement.getName());
        rangeMinTextField.setText(""+sensingElement.getRangeMin());
        rangeMaxTextField.setText(""+sensingElement.getRangeMax());
        defaultAlarmThresholdTextField.setText(""+sensingElement.getDefaultAlarmThreshold());
        multiplierTextField.setText(""+sensingElement.getMultiplier());
        measureUnitTextField.setText(""+sensingElement.getMeasureUnit());


    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            sensingElement.setId(idTextField.getText());
            sensingElement.setrSense(Integer.parseInt(rSenseTextField.getText()));
            sensingElement.setInGain(Integer.parseInt(inGainTextField.getText()));
            sensingElement.setOutGain(Integer.parseInt(outGainTextField.getText()));
            sensingElement.setContacts(contactsTextField.getText());
            sensingElement.setConversionRate(Integer.parseInt(conversionRateTextField.getText()));
            sensingElement.setMeasureType(measureTypeTextField.getText());
            sensingElement.setDcBias(Integer.parseInt(dcBiasTextField.getText()));
            sensingElement.setFilter(Integer.parseInt(filterTextField.getText()));
            sensingElement.setFrequency(Integer.parseInt(frequencyTextField.getText()));
            sensingElement.setHarmonic(harmonicTextField.getText());
            sensingElement.setInPortADC(inPortADCTextField.getText());
            sensingElement.setIq(iqTextField.getText());
            sensingElement.setMeasureTechnique(measureTechniqueTextField.getText());
            sensingElement.setMeasureType(measureTypeTextField.getText());
            sensingElement.setModeVI(modeVITextField.getText());
            sensingElement.setnData(Integer.parseInt(nDataTextField.getText()));
            sensingElement.setPhaseShift(Integer.parseInt(phaseShiftTextField.getText()));
            sensingElement.setPhaseShiftMode(phaseShiftModeTextField.getText());
            sensingElement.setMultiplier(Integer.parseInt(multiplierTextField.getText()));
            sensingElement.setName(nameTextField.getText());
            sensingElement.setRangeMin(Double.parseDouble(rangeMinTextField.getText()));
            sensingElement.setRangeMax(Double.parseDouble(rangeMaxTextField.getText()));
            sensingElement.setDefaultAlarmThreshold(Double.parseDouble(defaultAlarmThresholdTextField.getText()));
            sensingElement.setMultiplier(Integer.parseInt(multiplierTextField.getText()));
            sensingElement.setMeasureUnit(measureUnitTextField.getText());


            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameTextField.getText() == null || nameTextField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";

        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
