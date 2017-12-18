package it.unicas.project.model;

import java.util.ArrayList;
import java.util.List;

public class SensingElementWithCalibration {

    private String idSensingElement;
    private Integer m;
    private Integer n;


    public SensingElementWithCalibration(String idSensingElement, Integer m, Integer n) {
        this.idSensingElement = idSensingElement;
        this.n = n;
        this.m = m;
    }

    public SensingElementWithCalibration() {
        this.idSensingElement = "";
        this.m = 1;
        this.n = 0;
    }

    public SensingElementWithCalibration(SensingElementWithCalibration sensingElementWithCalibration) {
        this.setIdSensingElement(sensingElementWithCalibration.getIdSensingElement());
        this.setM(sensingElementWithCalibration.getM());
        this.setN(sensingElementWithCalibration.getN());
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

    @Override
    public boolean equals(Object sensingElementWithCalibration) {
        SensingElementWithCalibration se = (SensingElementWithCalibration) sensingElementWithCalibration;

        if (se.getIdSensingElement().equals(this.idSensingElement) &&
                se.getN() == this.n &&
                se.getM() == this.m) {
            return true;
        }

        return false;
    }
}
