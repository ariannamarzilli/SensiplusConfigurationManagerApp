package it.unicas.project.model;

public class Analyte {

    private String name;
    private String idSensingElement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Analyte(String name, String idSensingElement) {
        this.name = name;
        this.idSensingElement = idSensingElement;
    }

    public Analyte (Analyte analyte){
        this.name = analyte.getName();
        this.idSensingElement = analyte.getIdSensingElement();
    }

    public String getIdSensingElement() {
        return idSensingElement;
    }

    public void setIdSensingElement(String idSensingElement) {
        this.idSensingElement = idSensingElement;
    }

    @Override
    public boolean equals(Object o) {
        Analyte analyte = (Analyte) o;
        if (this.name.equals(analyte.getName()) &&
                this.idSensingElement.equals(analyte.getIdSensingElement())) {
            return true;
        }
        return false;
    }
}
