package it.unicas.project.xml;

import it.unicas.project.model.SensingElementWithCalibration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name = "CALIBRATION_PARAMETER")
@XmlType(propOrder = {"m", "n"})
public class CalibrationParametersWrapper {

    private Integer m;
    private Integer n;

    public CalibrationParametersWrapper() {
    }

    public CalibrationParametersWrapper(SensingElementWithCalibration sensingElementWithCalibration) {
        m = sensingElementWithCalibration.getM();
        n = sensingElementWithCalibration.getN();
    }

    public CalibrationParametersWrapper(Integer m, Integer n) {
        this.m = m;
        this.n = n;
    }

    public Integer getM() {
        return m;
    }

    @XmlElement(name = "M")
    public void setM(Integer m) {
        this.m = m;
    }

    public Integer getN() {
        return n;
    }

    @XmlElement(name = "N")
    public void setN(Integer n) {
        this.n = n;
    }
}
