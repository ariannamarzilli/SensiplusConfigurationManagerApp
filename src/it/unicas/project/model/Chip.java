package it.unicas.project.model;

import java.util.ArrayList;
import java.util.List;

public class Chip {

    String familyName;
    String id;

    public Chip(String familyName, String id) {
        this.familyName = familyName;
        this.id = id;
    }

    public Chip() {
        familyName = "";
        id = "";
    }

    public Chip(Chip chip){
        setFamilyName(chip.getFamilyName());
        setId(chip.getId());

    }

    public Chip(String id) {
        this.id = id;
        this.familyName = "";
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
    }
}
