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

    public SensingElementWithCalibration(SensingElementWithCalibration sensingElementWithCalibration) {
        calibrationList = new ArrayList<>();
        this.setIdSensingElement(sensingElementWithCalibration.getIdSensingElement());

        for (int i = 0; i < sensingElementWithCalibration.getCalibrationList().size(); i++) {
            this.calibrationList.add(sensingElementWithCalibration.getCalibrationList().get(i));
        }
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

    public void checkNullField() {
        if (calibrationList == null) {
            calibrationList = new ArrayList<>();
        }
        if (idSensingElement == null) {
            idSensingElement = "";
        }
    }

    @Override
    public boolean equals(Object sensingElementWithCalibration) {
        SensingElementWithCalibration se = (SensingElementWithCalibration) sensingElementWithCalibration;

        if (this.calibrationList.equals(se.getCalibrationList()) &&
                this.idSensingElement.equals(se.getIdSensingElement())) {
            return true;
        }
        return false;
    }
}
