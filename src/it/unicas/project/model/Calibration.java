package it.unicas.project.model;

public class Calibration {

    public Calibration(String name, int n, int m) {

        this.name = name;
        this.n = n;
        this.m = m;
    }

    public Calibration(String name) {
        this.name = name;
        this.n = 0;
        this.m = 0;
    }

    public Calibration() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private int n;
    private  int m;

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }
}
