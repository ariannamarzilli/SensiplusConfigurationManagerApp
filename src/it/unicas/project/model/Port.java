package it.unicas.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "SENSING_ELEMENT_ONFAMILY")
public class Port {

    private String name;
    private String idSensingElement;
    private List<Analyte> analytes;

    public String getName() {
        return name;
    }

    @XmlElement(name = "SENSING_ELEMENT_PORT")
    public void setName(String name) {
        this.name = name;
    }

    public String getIdSensingElement() {
        return idSensingElement;
    }

    @XmlElement(name = "SENSING_ELEMENT_ID")
    public void setIdSensingElement(String idSensingElement) {
        this.idSensingElement = idSensingElement;
    }

    public List<Analyte> getAnalytes() {
        return analytes;
    }

    @XmlElement(name = "ANALYTE_LIST")
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
