package it.unicas.project.model;

import java.util.ArrayList;
import java.util.List;

public class SensingElementWithCalibration {

    private List<Calibration> calibrationList;
    private String idSensingElement;

    public SensingElementWithCalibration(List<Calibration> calibrationList, String idSensingElement) {
        this.calibrationList = calibrationList;
        this.idSensingElement = idSensingElement;
    }

    public SensingElementWithCalibration() {
        this.calibrationList = new ArrayList<>();
        this.idSensingElement = "";
    }



    public List<Calibration> getCalibrationList() {
        return calibrationList;
    }

    public void setCalibrationList(List<Calibration> calibrationList) {
        this.calibrationList = calibrationList;
    }




    public String getIdSensingElement() {
        return idSensingElement;
    }

    public void setIdSensingElement(String idSensingElement) {
        this.idSensingElement = idSensingElement;
    }
}
