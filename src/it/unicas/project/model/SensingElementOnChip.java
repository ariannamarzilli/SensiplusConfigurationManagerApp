package it.unicas.project.model;

import java.util.ArrayList;
import java.util.List;

public class SensingElementOnChip {

    private Chip chip;
    private List<Calibration> calibrationList;
    private String idSensingElement;

    public SensingElementOnChip(Chip chip, List<Calibration> calibrationList, int n, int m, String idSensingElement) {
        this.chip = chip;
        this.calibrationList = calibrationList;
        this.idSensingElement = idSensingElement;
    }

    public SensingElementOnChip(Chip chip) {
        this.chip = chip;
        this.calibrationList = new ArrayList<>();
        this.idSensingElement = "";
    }

    public Chip getChip() {
        return chip;
    }

    public List<Calibration> getCalibrationList() {
        return calibrationList;
    }

    public void setCalibrationList(List<Calibration> calibrationList) {
        this.calibrationList = calibrationList;
    }



    public void setChip(Chip chip) {
        this.chip = chip;
    }

    public String getIdSensingElement() {
        return idSensingElement;
    }

    public void setIdSensingElement(String idSensingElement) {
        this.idSensingElement = idSensingElement;
    }
}
