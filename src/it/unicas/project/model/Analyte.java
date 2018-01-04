package it.unicas.project.model;

public class Analyte {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Analyte(String name) {
        this.name = name;
    }

    public Analyte (Analyte analyte){
        this.name = analyte.getName();
    }



    @Override
    public boolean equals(Object o) {
        Analyte analyte = (Analyte) o;
        if (this.name.equals(analyte.getName())) {
            return true;
        }
        return false;
    }
}
