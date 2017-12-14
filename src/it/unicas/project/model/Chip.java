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
        familyName = "";
        id = "";
        sensingElementWithCalibrations = new ArrayList<>();
    }

    public Chip(Chip chip){
        sensingElementWithCalibrations = new ArrayList<>();
        setFamilyName(chip.getFamilyName());
        setId(chip.getId());
        for (int i = 0; i < chip.getSensingElementWithCalibrations().size(); i++) {
            sensingElementWithCalibrations.add(chip.getSensingElementWithCalibrations().get(i));
        }
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

    public void checkNullField() {
        if (this.familyName == null) {
            familyName = "";
        }
        if (this.id == null) {
            id = "";
        }
        if (this.sensingElementWithCalibrations == null) {
            sensingElementWithCalibrations = new ArrayList<>();
        }
    }
}
