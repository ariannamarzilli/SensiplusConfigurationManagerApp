package it.unicas.project.view;

import it.unicas.project.model.SensingElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SensingElementEditDialogController {

    ObservableList<String> rSenseList = FXCollections
            .observableArrayList("50", "500", "5000", "50000");

    ObservableList<String> inGainList = FXCollections
            .observableArrayList( "1", "12", "20", "40");

    ObservableList<String> outGainList = FXCollections
            .observableArrayList("0", "1", "2", "3", "4", "5", "6", "7");

    ObservableList<String> contactsList = FXCollections
            .observableArrayList("TWO", "FOUR");

    ObservableList<String> harmonicList = FXCollections
            .observableArrayList("FIRST_HARMONIC", "SECOND_HARMONIC", "THIRD_HARMONIC");

    ObservableList<String> modeVIList = FXCollections
            .observableArrayList("VOUT_IIN", "VIN_IIN", "VOUT_VIN", "VOUT_VOUT");

    ObservableList<String> measureTechniqueList = FXCollections
            .observableArrayList("DIRECT", "EIS", "POT", "ENERGY_SPECTROSCOPY", "ULTRASOUND");

    ObservableList<String> measureTypeList = FXCollections
            .observableArrayList("IN-PHASE", "QUADRATURE", "MODULE", "PHASE", "RESISTANCE", "CAPACITANCE", "INDUCTANCE");

    ObservableList<String> phaseShiftModeList = FXCollections
            .observableArrayList(    "QUADRANTS", "COARSE", "FINE");

    ObservableList<String> iqList = FXCollections
            .observableArrayList(    "IN_PHASE", "QUADRATURE");

    ObservableList<String> inPortADCList = FXCollections
            .observableArrayList(  "IA", "VA");

    ObservableList<String> measureUniteList = FXCollections
            .observableArrayList(  "O=Ohm", "F=Farad", "H=Henry", "C=Celsius", "%=relative", "V=Voltage", "A=Current",
                    "L=Lumen", "t=time");


    @FXML
    private TextField idTextField;
    @FXML
    private ComboBox<String> rSenseComboBox;
    @FXML
    private ComboBox<String> inGainComboBox;
    @FXML
    private ComboBox<String> outGainComboBox;
    @FXML
    private ComboBox<String> contactsComboBox;
    @FXML
    private TextField frequencyTextField;
    @FXML
    private ComboBox<String> harmonicComboBox;
    @FXML
    private TextField dcBiasTextField;
    @FXML
    private ComboBox<String> modeVIComboBox;
    @FXML
    private ComboBox<String> measureTechniqueComboBox;
    @FXML
    private ComboBox<String> measureTypeComboBox;
    @FXML
    private TextField filterTextField;
    @FXML
    private ComboBox<String> phaseShiftModeComboBox;
    @FXML
    private TextField phaseShiftTextField;
    @FXML
    private ComboBox<String> iqComboBox;
    @FXML
    private TextField conversionRateTextField;
    @FXML
    private ComboBox<String> inPortADCComboBox;
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
    private ComboBox<String> measureUnitComboBox;

    private Stage dialogStage;
    private SensingElement sensingElement;
    private boolean okClicked = false;
    boolean cancelClicked = false;
    Integer emptyIntegerForDirectMeasure = -2049;
    Double emptyDoubleForDirectMeasure = 2049.0;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        rSenseComboBox.setItems(rSenseList);
        inGainComboBox.setItems(inGainList);
        outGainComboBox.setItems(outGainList);
        contactsComboBox.setItems(contactsList);
        harmonicComboBox.setItems(harmonicList);
        modeVIComboBox.setItems(modeVIList);
        measureTechniqueComboBox.setItems(measureTechniqueList);
        measureTypeComboBox.setItems(measureTypeList);
        phaseShiftModeComboBox.setItems(phaseShiftModeList);
        iqComboBox.setItems(iqList);
        inPortADCComboBox.setItems(inPortADCList);
        measureUnitComboBox.setItems(measureUniteList);
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
        rSenseComboBox.setValue(""+sensingElement.getrSense());
        inGainComboBox.setValue(""+sensingElement.getInGain());
        outGainComboBox.setValue(""+sensingElement.getOutGain());
        contactsComboBox.setValue(sensingElement.getContacts());
        if (sensingElement.getFrequency() == emptyDoubleForDirectMeasure) {
            frequencyTextField.setText("");
        } else {
            frequencyTextField.setText(""+sensingElement.getFrequency());
        }
        frequencyTextField.setText(""+sensingElement.getFrequency());
        harmonicComboBox.setValue(sensingElement.getHarmonic());
        if (sensingElement.getDcBias() == emptyIntegerForDirectMeasure) {
            dcBiasTextField.setText("");
        } else {
            dcBiasTextField.setText(""+sensingElement.getDcBias());
        }
        modeVIComboBox.setValue(sensingElement.getModeVI());
        measureTechniqueComboBox.setValue(sensingElement.getMeasureTechnique());
        measureTypeComboBox.setValue(sensingElement.getMeasureType());
        filterTextField.setText(""+sensingElement.getFilter());
        phaseShiftModeComboBox.setValue(sensingElement.getPhaseShiftMode());
        if (sensingElement.getPhaseShift() == emptyDoubleForDirectMeasure) {
            phaseShiftTextField.setText("");
        } else {
            phaseShiftTextField.setText(""+sensingElement.getPhaseShift());
        }
        iqComboBox.setValue(sensingElement.getIq());
        conversionRateTextField.setText(""+sensingElement.getConversionRate());
        inPortADCComboBox.setValue(sensingElement.getInPortADC());
        nDataTextField.setText(""+sensingElement.getnData());
        nameTextField.setText(sensingElement.getName());
        rangeMinTextField.setText(""+sensingElement.getRangeMin());
        rangeMaxTextField.setText(""+sensingElement.getRangeMax());
        defaultAlarmThresholdTextField.setText(""+sensingElement.getDefaultAlarmThreshold());
        multiplierTextField.setText(""+sensingElement.getMultiplier());
        measureUnitComboBox.setValue(sensingElement.getMeasureUnit());

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

            if (measureTechniqueComboBox.getValue().equals("DIRECT")) {
                sensingElement.setId(idTextField.getText());
                sensingElement.setrSense(new String());
                sensingElement.setInGain(new String());
                sensingElement.setOutGain(new String());
                sensingElement.setContacts(new String());
                sensingElement.setConversionRate(Double.parseDouble(conversionRateTextField.getText()));
                sensingElement.setMeasureType(new String());
                // using value out of the range
                sensingElement.setDcBias(emptyIntegerForDirectMeasure);
                sensingElement.setFilter(Integer.parseInt(filterTextField.getText()));
                sensingElement.setFrequency(emptyDoubleForDirectMeasure);
                sensingElement.setHarmonic(new String());
                sensingElement.setInPortADC(inPortADCComboBox.getValue());
                sensingElement.setIq(new String());
                sensingElement.setMeasureTechnique(measureTechniqueComboBox.getSelectionModel().getSelectedItem());
                sensingElement.setModeVI(new String());
                sensingElement.setnData(Integer.parseInt(nDataTextField.getText()));
                sensingElement.setPhaseShift(emptyDoubleForDirectMeasure);
                sensingElement.setPhaseShiftMode(new String());
                sensingElement.setName(nameTextField.getText());
                sensingElement.setRangeMin(Double.parseDouble(rangeMinTextField.getText()));
                sensingElement.setRangeMax(Double.parseDouble(rangeMaxTextField.getText()));
                sensingElement.setDefaultAlarmThreshold(Double.parseDouble(defaultAlarmThresholdTextField.getText()));
                sensingElement.setMultiplier(Integer.parseInt(multiplierTextField.getText()));
                sensingElement.setMeasureUnit(measureUnitComboBox.getValue());
            } else {
                sensingElement.setId(idTextField.getText());
                sensingElement.setrSense(rSenseComboBox.getValue());
                sensingElement.setInGain(inGainComboBox.getValue());
                sensingElement.setOutGain(outGainComboBox.getValue());
                sensingElement.setContacts(contactsComboBox.getValue());
                sensingElement.setConversionRate(Double.parseDouble(conversionRateTextField.getText()));
                sensingElement.setMeasureType(measureTypeComboBox.getValue());
                sensingElement.setDcBias(Integer.parseInt(dcBiasTextField.getText()));
                sensingElement.setFilter(Integer.parseInt(filterTextField.getText()));
                sensingElement.setFrequency(Double.parseDouble(frequencyTextField.getText()));
                sensingElement.setHarmonic(harmonicComboBox.getValue());
                sensingElement.setInPortADC(inPortADCComboBox.getValue());
                sensingElement.setIq(iqComboBox.getValue());
                sensingElement.setMeasureTechnique(measureTechniqueComboBox.getValue());
                sensingElement.setModeVI(modeVIComboBox.getValue());
                sensingElement.setnData(Integer.parseInt(nDataTextField.getText()));
                sensingElement.setPhaseShift(Double.parseDouble(phaseShiftTextField.getText()));
                sensingElement.setPhaseShiftMode(phaseShiftModeComboBox.getValue());
                sensingElement.setName(nameTextField.getText());
                sensingElement.setRangeMin(Double.parseDouble(rangeMinTextField.getText()));
                sensingElement.setRangeMax(Double.parseDouble(rangeMaxTextField.getText()));
                sensingElement.setDefaultAlarmThreshold(Double.parseDouble(defaultAlarmThresholdTextField.getText()));
                sensingElement.setMultiplier(Integer.parseInt(multiplierTextField.getText()));
                sensingElement.setMeasureUnit(measureUnitComboBox.getValue());
            }
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        cancelClicked = true;
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = new String();

        if (idTextField.getText() == null || idTextField.getText().length() == 0 || idTextField.getText().isEmpty() || idTextField.getText().equals(" ")) {
            errorMessage += "No valid ID!\n";
        }

        if (Double.parseDouble(frequencyTextField.getText()) > 5000000.0 || Double.parseDouble(frequencyTextField.getText()) < 0.0){
            errorMessage += "No valid Frequency!\n";
        }

        if (Integer.parseInt(dcBiasTextField.getText()) > 2048 || Integer.parseInt(dcBiasTextField.getText()) < -2048){
            errorMessage += "No valid DCBias!\n";
        }

        if (Integer.parseInt(filterTextField.getText()) > 256 || Integer.parseInt(filterTextField.getText()) < 0){
            errorMessage += "No valid Filter!\n";
        }

        if (Double.parseDouble(phaseShiftTextField.getText()) > 360 || Double.parseDouble(phaseShiftTextField.getText()) < 0){
            errorMessage += "No valid Phase Shift!\n";
        }

        if (Double.parseDouble(conversionRateTextField.getText()) > 100000 || Double.parseDouble(conversionRateTextField.getText()) < 0){
            errorMessage += "No valid Conversion Rate!\n";
        }

        if (Integer.parseInt(nDataTextField.getText()) > 16 || Integer.parseInt(filterTextField.getText()) < 0){
            errorMessage += "No valid NData!\n";
        }

        if (Double.parseDouble(rangeMinTextField.getText()) > Math.pow(10, 21) || Double.parseDouble(rangeMaxTextField.getText()) < -Math.pow(10, 21)){
            errorMessage += "No valid Range Min!\n";
        }

        if (Double.parseDouble(rangeMaxTextField.getText()) > Math.pow(10, 21) ||
                Double.parseDouble(rangeMaxTextField.getText()) < -Math.pow(10, 21) ||
                Double.parseDouble(rangeMinTextField.getText()) > Double.parseDouble(rangeMaxTextField.getText())){
            errorMessage += "No valid Range Max!\n";
        }

        if (Double.parseDouble(defaultAlarmThresholdTextField.getText()) > Math.pow(10, 21) || Double.parseDouble(defaultAlarmThresholdTextField.getText()) < -Math.pow(10, 21)){
            errorMessage += "No valid Alarm Threshold!\n";
        }

        if (Integer.parseInt(multiplierTextField.getText()) >= 21 && Integer.parseInt(multiplierTextField.getText()) <= -21){
            errorMessage += "No valid Multiplier!\n";
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

    public boolean isCancelClicked() {return cancelClicked;}
}
