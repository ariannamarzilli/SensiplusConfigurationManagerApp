package it.unicas.project.model;

import java.util.List;

public class Port {

    private String name;
    private String idSensingElement;
    private List<Analyte> analytes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdSensingElement() {
        return idSensingElement;
    }

    public void setIdSensingElement(String idSensingElement) {
        this.idSensingElement = idSensingElement;
    }

    public List<Analyte> getAnalytes() {
        return analytes;
    }

    public void setAnalytes(List<Analyte> analytes) {
        this.analytes = analytes;
    }

    public Port(String name) {
        this.name = name;
        this.idSensingElement = "";
    }

    public Port (String name, String idSensingElement) {
        this.name = name;
        this.idSensingElement = idSensingElement;
    }

    public Port (String name, String idSensingElement, List<Analyte> analytes) {
        this.name = name;
        this.idSensingElement = idSensingElement;
        this.analytes = analytes;
    }

    public  Port(String name, String idSensingElement, String nameSe, List<Analyte> analytes){
        this.name = name;
        this.idSensingElement = idSensingElement;
        this.analytes = analytes;
    }
    public Port (String namePort,  SensingElement sensingElement){
        this.idSensingElement = sensingElement.getId();
        this.name = namePort;
    }

    @Override
    public boolean equals(Object o) {
        Port port = (Port) o;
        if (this.name.equals(port.getName()) &&
                this.idSensingElement.equals(port.getIdSensingElement()) &&
                this.analytes.equals(port.getAnalytes())) {
            return true;
        }
        return false;
    }

}
