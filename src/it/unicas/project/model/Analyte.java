package it.unicas.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ANALYTE")
public class Analyte {

    private String name;

    public String getName() {
        return name;
    }

    @XmlElement(name = "ANALYTE_NAME")
    public void setName(String name) {
        this.name = name;
    }

    public Analyte(String name) {
        this.name = name;
    }

    public Analyte (Analyte analyte){
        this.name = analyte.getName();
    }

    public Analyte() {
    }

    @Override
    public boolean equals(Object o) {

        try {
            String analyte = (String) o;
            if (this.name.equals(analyte)) {
                return true;
            }
            return false;
        } catch (ClassCastException e) {
            Analyte analyte = (Analyte) o;
            if (this.name.equals(analyte)) {
                return true;
            }
            return false;
        }
    }

}
