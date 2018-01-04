package it.unicas.project.util;

import it.unicas.project.model.Chip;

public class CSMN {

    private Chip chip;
    private String idSensingElement;
    private Integer m;
    private Integer n;

    public CSMN() {
        chip = new Chip();
        idSensingElement = "";
        m = 1;
        n = 0;
    }

    public Chip getChip() {
        return chip;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
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
}
