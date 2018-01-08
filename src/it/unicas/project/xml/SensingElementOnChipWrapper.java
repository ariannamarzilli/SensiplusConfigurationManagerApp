package it.unicas.project.xml;

import it.unicas.project.model.SensingElementWithCalibration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "SENSING_ELEMENT_ONCHIP")
@XmlType(propOrder = {"sensingElementId", "calibrationParameters"})
public class SensingElementOnChipWrapper {

    private String sensingElementId;
    CalibrationParametersWrapper calibrationParameters;

    public SensingElementOnChipWrapper() {

    }

    public SensingElementOnChipWrapper(SensingElementWithCalibration sensingElementWithCalibration) {
        sensingElementId = sensingElementWithCalibration.getIdSensingElement();
        calibrationParameters = new CalibrationParametersWrapper(sensingElementWithCalibration);
    }

    public SensingElementOnChipWrapper(String sensingElementId, CalibrationParametersWrapper calibrationParameters) {
        this.sensingElementId = sensingElementId;
        this.calibrationParameters = calibrationParameters;
    }

    public String getSensingElementId() {
        return sensingElementId;
    }

    @XmlElement(name = "SENSING_ELEMENT_ID")
    public void setSensingElementId(String sensingElementId) {
        this.sensingElementId = sensingElementId;
    }

    public CalibrationParametersWrapper getCalibrationParameters() {
        return calibrationParameters;
    }

    @XmlElement(name = "CALIBRATION_PARAMETER")
    public void setCalibrationParameters(CalibrationParametersWrapper calibrationParameters) {
        this.calibrationParameters = calibrationParameters;
    }
}
