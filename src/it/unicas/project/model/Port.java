package it.unicas.project.model;

public class Port {

    private String name;
    private String idSensingElement;

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

    public Port(String name) {
        this.name = name;
        this.idSensingElement = "";
    }

    public Port (String name, String idSensingElement) {
        this.name = name;
        this.idSensingElement = idSensingElement;
    }

    public Port (String name, String idSensingElement, String nameSensingElement) {
        this.name = name;
        this.idSensingElement = idSensingElement;
    }

    public Port (String namePort,  SensingElement sensingElement){
        this.idSensingElement = sensingElement.getId();
        this.name = namePort;
    }

    @Override
    public boolean equals(Object o) {
        Port port = (Port) o;
        if (this.name.equals(port.getName()) &&
                this.idSensingElement.equals(port.getIdSensingElement())) {
            return true;
        }
        return false;
    }

}
