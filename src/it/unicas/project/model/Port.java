package it.unicas.project.model;

public class Port {

    private String name;
    private String idSensingElement;
    private String nameSensingElement;

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

    public String getNameSensingElement() {
        return nameSensingElement;
    }

    public void setNameSensingElement(String nameSensingElement) {
        this.nameSensingElement = nameSensingElement;
    }

    public Port(String name) {
        this.name = name;
    }

    public Port (String name, String idSensingElement) {
        this.name = name;
        this.idSensingElement = idSensingElement;
    }

    public Port (String name, String idSensingElement, String nameSensingElement) {
        this.name = name;
        this.idSensingElement = idSensingElement;
        this.nameSensingElement = nameSensingElement;
    }

    public Port (String namePort,  SensingElement sensingElement){
        this.nameSensingElement = sensingElement.getName();
        this.idSensingElement = sensingElement.getId();
        this.name = namePort;
    }
}
