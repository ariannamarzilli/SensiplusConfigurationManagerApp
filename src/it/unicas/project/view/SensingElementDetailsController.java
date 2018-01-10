package it.unicas.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import it.unicas.project.model.SensingElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class SensingElementDetailsController {

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
            .observableArrayList(  "O = Ohm", "F = Farad", "H = Henry", "C = Celsius", "% = relative", "V = Voltage", "A = Current",
                    "L = Lumen", "t = time");

    @FXML
    private JFXTextField idTextField;
    @FXML
    private JFXComboBox<String> rSenseComboBox;
    @FXML
    private JFXComboBox<String> inGainComboBox;
    @FXML
    private JFXComboBox<String> outGainComboBox;
    @FXML
    private JFXComboBox<String> contactsComboBox;
    @FXML
    private JFXTextField frequencyTextField;
    @FXML
    private JFXComboBox<String> harmonicComboBox;
    @FXML
    private JFXTextField dcBiasTextField;
    @FXML
    private JFXComboBox<String> modeVIComboBox;
    @FXML
    private JFXComboBox<String> measureTechniqueComboBox;
    @FXML
    private JFXComboBox<String> measureTypeComboBox;
    @FXML
    private JFXTextField filterTextField;
    @FXML
    private JFXComboBox<String> phaseShiftModeComboBox;
    @FXML
    private JFXTextField phaseShiftTextField;
    @FXML
    private JFXComboBox<String> iqComboBox;
    @FXML
    private JFXTextField conversionRateTextField;
    @FXML
    private JFXComboBox<String> inPortADCComboBox;
    @FXML
    private JFXTextField nDataTextField;
    @FXML
    private JFXTextField nameTextField;
    @FXML
    private JFXTextField rangeMinTextField;
    @FXML
    private JFXTextField rangeMaxTextField;
    @FXML
    private JFXTextField defaultAlarmThresholdTextField;
    @FXML
    private JFXTextField multiplierTextField;
    @FXML
    private JFXComboBox<String> measureUnitComboBox;

    private Integer emptyIntegerForDirectMeasure = Integer.MAX_VALUE;
    private Double emptyDoubleForDirectMeasure = Double.MAX_VALUE;
    private SensingElement sensingElement;
    private Stage dialogStage;
    private boolean isAnUpdate;


    /**
     * This method is automatically called after the fxml file has been loaded.
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
     * Sets the person to be edited in the dialog.
     *
     * @param sensingElement to be edited.
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

        if (sensingElement.getMeasureUnit().equals("O")) {
            measureUnitComboBox.setValue("O = Ohm");
        } else if (sensingElement.getMeasureUnit().equals("F")) {
            measureUnitComboBox.setValue("F = Farad");
        } else if (sensingElement.getMeasureUnit().equals("H")) {
            measureUnitComboBox.setValue("H = Henry");
        } else if (sensingElement.getMeasureUnit().equals("C")) {
            measureUnitComboBox.setValue("C = Celsius");
        } else if (sensingElement.getMeasureUnit().equals("%")) {
            measureUnitComboBox.setValue("% = relative");
        } else if (sensingElement.getMeasureUnit().equals("V")) {
            measureUnitComboBox.setValue("V = Voltage");
        } else if (sensingElement.getMeasureUnit().equals("A")) {
            measureUnitComboBox.setValue("A = Current");
        } else if (sensingElement.getMeasureUnit().equals("L")) {
            measureUnitComboBox.setValue("L = Lumen");
        } else if (sensingElement.getMeasureUnit().equals("t")) {
            measureUnitComboBox.setValue("t = time");
        } else {
            measureUnitComboBox.setValue(null);
        }

        if (measureTechniqueComboBox.getSelectionModel().getSelectedItem().equals("DIRECT")) {
            rSenseComboBox.setDisable(true);
            inGainComboBox.setDisable(true);
            outGainComboBox.setDisable(true);
            contactsComboBox.setDisable(true);
            frequencyTextField.setDisable(true);
            harmonicComboBox.setDisable(true);
            dcBiasTextField.setDisable(true);
            modeVIComboBox.setDisable(true);
            measureTypeComboBox.setDisable(true);
            phaseShiftModeComboBox.setDisable(true);
            phaseShiftTextField.setDisable(true);
            iqComboBox.setDisable(true);
        }
    }

    /**
     * Called when the user clicks Save Changes.
     */
    @FXML
    private void handleSaveChanges() {
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

                if (measureUnitComboBox.getValue().startsWith("O")) {
                    sensingElement.setMeasureUnit("O");
                } else if (measureUnitComboBox.getValue().startsWith("F")) {
                    sensingElement.setMeasureUnit("F");
                } else if (measureUnitComboBox.getValue().startsWith("H")) {
                    sensingElement.setMeasureUnit("H");
                } else if (measureUnitComboBox.getValue().startsWith("C")) {
                    sensingElement.setMeasureUnit("C");
                } else if (measureUnitComboBox.getValue().startsWith("%")) {
                    sensingElement.setMeasureUnit("%");
                } else if (measureUnitComboBox.getValue().startsWith("V")) {
                    sensingElement.setMeasureUnit("V");
                } else if (measureUnitComboBox.getValue().startsWith("A")) {
                    sensingElement.setMeasureUnit("A");
                } else if (measureUnitComboBox.getValue().startsWith("L")) {
                    sensingElement.setMeasureUnit("L");
                } else {
                    sensingElement.setMeasureUnit("");
                }

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
                if (measureUnitComboBox.getValue().startsWith("O")) {
                    sensingElement.setMeasureUnit("O");
                } else if (measureUnitComboBox.getValue().startsWith("F")) {
                    sensingElement.setMeasureUnit("F");
                } else if (measureUnitComboBox.getValue().startsWith("H")) {
                    sensingElement.setMeasureUnit("H");
                } else if (measureUnitComboBox.getValue().startsWith("C")) {
                    sensingElement.setMeasureUnit("C");
                } else if (measureUnitComboBox.getValue().startsWith("%")) {
                    sensingElement.setMeasureUnit("%");
                } else if (measureUnitComboBox.getValue().startsWith("V")) {
                    sensingElement.setMeasureUnit("V");
                } else if (measureUnitComboBox.getValue().startsWith("A")) {
                    sensingElement.setMeasureUnit("A");
                } else if (measureUnitComboBox.getValue().startsWith("L")) {
                    sensingElement.setMeasureUnit("L");
                } else {
                    sensingElement.setMeasureUnit("");
                }
            }

            dialogStage.close();
        }
    }

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

        if (Double.parseDouble(rangeMinTextField.getText()) > Math.pow(10, 21) || Double.parseDouble(rangeMaxTextField.getText()) < -Math.pow(10, -21)){
            errorMessage += "No valid Range Min!\n";
        }

        if (Double.parseDouble(rangeMaxTextField.getText()) > Math.pow(10, 21) ||
                Double.parseDouble(rangeMaxTextField.getText()) < -Math.pow(10, -21) ||
                Double.parseDouble(rangeMinTextField.getText()) > Double.parseDouble(rangeMaxTextField.getText())){
            errorMessage += "No valid Range Max!\n";
        }

        if (Double.parseDouble(defaultAlarmThresholdTextField.getText()) > Math.pow(10, 21) || Double.parseDouble(defaultAlarmThresholdTextField.getText()) < -Math.pow(10, -21)){
            errorMessage += "No valid Alarm Threshold!\n";
        }

        if (Integer.parseInt(multiplierTextField.getText()) >= 21 || Integer.parseInt(multiplierTextField.getText()) <= -21 ||
                Integer.parseInt(multiplierTextField.getText())%3 != 0){
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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * If the sensor makes direct measurement, some widgets must be disabled.
     */
    @FXML
    public void handleMeasureTechnique() {
        if (measureTechniqueComboBox.getSelectionModel().getSelectedItem().equals("DIRECT")) {
            rSenseComboBox.setDisable(true);
            inGainComboBox.setDisable(true);
            outGainComboBox.setDisable(true);
            contactsComboBox.setDisable(true);
            frequencyTextField.setDisable(true);
            harmonicComboBox.setDisable(true);
            dcBiasTextField.setDisable(true);
            modeVIComboBox.setDisable(true);
            measureTypeComboBox.setDisable(true);
            phaseShiftModeComboBox.setDisable(true);
            phaseShiftTextField.setDisable(true);
            iqComboBox.setDisable(true);
        }
        else {
            rSenseComboBox.setDisable(false);
            inGainComboBox.setDisable(false);
            outGainComboBox.setDisable(false);
            contactsComboBox.setDisable(false);
            frequencyTextField.setDisable(false);
            harmonicComboBox.setDisable(false);
            dcBiasTextField.setDisable(false);
            modeVIComboBox.setDisable(false);
            measureTypeComboBox.setDisable(false);
            phaseShiftModeComboBox.setDisable(false);
            phaseShiftTextField.setDisable(false);
            iqComboBox.setDisable(false);
        }
    }

    /**
     * If it's an update, it prevents from changing id.
     * @return
     */
    public void setAnUpdate(boolean isAnUpdate) {
        this.isAnUpdate = isAnUpdate;
        handleIdTextField(isAnUpdate);
    }

    public boolean isAnUpdate() {
        return isAnUpdate;
    }

    private void handleIdTextField(boolean isAnUpdate) {
        idTextField.setDisable(isAnUpdate);
    }

}
