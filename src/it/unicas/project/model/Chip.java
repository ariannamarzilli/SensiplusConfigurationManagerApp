package it.unicas.project.model;


public class Chip {

    String familyName;
    String id;

    public Chip(String familyName, String id) {
        this.familyName = familyName;
        this.id = id;
    }

    public Chip() {
    }

    public Chip(Chip chip){

        this.id = chip.getId();
        this.familyName = chip.getFamilyName();

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

}
