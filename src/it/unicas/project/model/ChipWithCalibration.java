package it.unicas.project.model;

import java.util.ArrayList;
import java.util.List;

public class ChipWithCalibration {

    private Chip chip;
    private List<SensingElementWithCalibration> sensingElementWithCalibrations;

    public Chip getChip() {
        return chip;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
    }

    public List<SensingElementWithCalibration> getSensingElementWithCalibrations() {
        return sensingElementWithCalibrations;
    }

    public void setSensingElementWithCalibrations(List<SensingElementWithCalibration> sensingElementWithCalibrations) {
        this.sensingElementWithCalibrations = sensingElementWithCalibrations;
    }

    public ChipWithCalibration() {
        chip = new Chip();
        sensingElementWithCalibrations = new ArrayList<>();
    }

    public ChipWithCalibration(Chip chip, List<SensingElementWithCalibration> sensingElementWithCalibrations) {
        this.chip = chip;
        this.sensingElementWithCalibrations = sensingElementWithCalibrations;
    }

    public ChipWithCalibration(Chip chip) {
        this.chip = chip;
        sensingElementWithCalibrations = new ArrayList<>();
    }

    @Override
    public boolean equals(Object c) {
        ChipWithCalibration chipWithCalibration = (ChipWithCalibration) c;

        if (this.chip.equals(chipWithCalibration.getChip()) &&
                this.sensingElementWithCalibrations.equals(chipWithCalibration.getSensingElementWithCalibrations())){
            return true;
        }
        return false;
    }

    public void checkNullField() {
        if (this.chip == null) {
            chip = new Chip();
        }

        if (this.sensingElementWithCalibrations == null){
            sensingElementWithCalibrations = new ArrayList<>();
        }
    }
}
