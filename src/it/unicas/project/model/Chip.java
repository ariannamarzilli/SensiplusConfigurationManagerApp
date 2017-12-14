package it.unicas.project.model;


import java.util.ArrayList;
import java.util.List;

public class Chip {

    String familyName;
    String id;
    List<SensingElementWithCalibration> sensingElementWithCalibrations;

    public Chip(String familyName, String id, List<SensingElementWithCalibration> sensingElementWithCalibrations) {
        this.familyName = familyName;
        this.id = id;
        this.sensingElementWithCalibrations = sensingElementWithCalibrations;
    }

    public Chip() {
    }

    public Chip(Chip chip){

        this.id = chip.getId();
        this.familyName = chip.getFamilyName();
        this.sensingElementWithCalibrations = chip.getSensingElementWithCalibrations();

    }

    public Chip(String id) {
        this.id = id;
        this.familyName = "";
        this.sensingElementWithCalibrations = new ArrayList<>();
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SensingElementWithCalibration> getSensingElementWithCalibrations() {
        return sensingElementWithCalibrations;
    }

    public void setSensingElementWithCalibrations(List<SensingElementWithCalibration> sensingElementWithCalibrations) {
        this.sensingElementWithCalibrations = sensingElementWithCalibrations;
    }

    public boolean equals(Chip chip) {
        if (this.id.equals(chip.getId()) &&
                this.familyName.equals(chip.getFamilyName())){
            return true;
        }
        return false;
    }

}
