package it.unicas.project.model;

public class Calibration {

    private String name;
    private Integer n;
    private Integer m;

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

    public Calibration(Calibration calibration) {
        this.setName(calibration.getName());
        this.setN(calibration.getN());
        this.setM(calibration.getM());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public void checkNullField() {

        if (name == null) {
            this.setName("");
        }
        if (m == null ) {
            m = Integer.MAX_VALUE;
        }
        if (n == null) {
            n = Integer.MAX_VALUE;
        }
    }

    @Override
    public boolean equals(Object calibration) {

        Calibration c = (Calibration) calibration;
        if (this.name.equals(c.getName()) &&
                this.m.equals(c.getM()) &&
                this.n.equals(c.getN())) {
            return true;
        }
        return false;
    }
}
