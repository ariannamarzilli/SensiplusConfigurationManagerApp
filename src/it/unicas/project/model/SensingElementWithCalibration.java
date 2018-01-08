package it.unicas.project.model;

import javax.xml.bind.annotation.*;

public class SensingElementWithCalibration {

    private String idSensingElement;
    private Integer m;
    private Integer n;
    private String portName;


    public SensingElementWithCalibration(String idSensingElement, Integer m, Integer n) {
        this.idSensingElement = idSensingElement;
        this.n = n;
        this.m = m;
        this.portName = "";
    }

    public SensingElementWithCalibration() {
        this.idSensingElement = "";
        this.m = 1;
        this.n = 0;
        this.portName = "";
    }

    public SensingElementWithCalibration(SensingElementWithCalibration sensingElementWithCalibration) {
        this.setIdSensingElement(sensingElementWithCalibration.getIdSensingElement());
        this.setM(sensingElementWithCalibration.getM());
        this.setN(sensingElementWithCalibration.getN());
        this.setPortName(sensingElementWithCalibration.getPortName());
    }


    public SensingElementWithCalibration(String idSensingElement, Integer m, Integer n, String portName) {
        this.idSensingElement = idSensingElement;
        this.m = m;
        this.n = n;
        this.portName = portName;
    }

    public String getIdSensingElement() {
        return idSensingElement;
    }

    public void setIdSensingElement(String idSensingElement) {
        this.idSensingElement = idSensingElement;
    }

    public Integer getM() {
        return m;
    }

    public void setM(Integer m) {
        this.m = m;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public void checkNullField() {

        if (idSensingElement == null) {
            idSensingElement = "";
        }
        if (n == null){
            n = Integer.MAX_VALUE;
        }
        if (m == null){
            m = Integer.MAX_VALUE;
        }
    }

    public String getPortName() {
        return portName;
    }

    @XmlTransient
    public void setPortName(String portName) {
        this.portName = portName;
    }

    @Override
    public boolean equals(Object sensingElementWithCalibration) {
        SensingElementWithCalibration se = (SensingElementWithCalibration) sensingElementWithCalibration;

        if (se.getIdSensingElement().equals(this.idSensingElement) &&
                se.getN() == this.n &&
                se.getM() == this.m &&
                se.getPortName().equals(this.portName)) {
            return true;
        }

        return false;
    }
}
