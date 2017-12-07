package it.unicas.project.model;

import java.util.ArrayList;
import java.util.List;

public class Family {

    private String id;
    private String name;
    private String hwVersion;
    private String sysclock;
    private String osctrim;
    private List<String> portName;
    private List<String> measureType;


    public Family(){
        this.id = "";
        this.name = "";
        this.hwVersion = "";
        this.sysclock = "";
        this.osctrim = "";
        this.portName = new ArrayList<>();
        this.measureType = new ArrayList<>();
    }

    public Family(String id){

        this.id = id;
        this.name = "";
        this.hwVersion = "";
        this.sysclock = "";
        this.osctrim = "";
        this.portName = new ArrayList<>();
        this.measureType = new ArrayList<>();
    }

    public Family (String id, String name, String hwVersion,
                   String sysclock, String osctrim, List<String> portName,
                   List<String> measureType){

        this.id = id;
        this.name = name;
        this.hwVersion = hwVersion;
        this.sysclock = sysclock;
        this.osctrim = osctrim;
        this.portName = portName;
        this.measureType = measureType;
    }


    public Family(Family family) {
        this.portName = new ArrayList<>();
        this.measureType = new ArrayList<>();
        this.setId(family.getId());
        this.setName(family.getName());
        this.setHwVersion(family.getHwVersion());
        this.setSysclock(family.getSysclock());
        this.setOsctrim(family.getOsctrim());

        for (int i = 0; i < family.getPortName().size(); i++) {
            this.portName.add(family.getPortName().get(i));
        }

        for (int i = 0; i < family.getMeasureType().size(); i++) {
            this.measureType.add(family.getMeasureType().get(i));
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHwVersion() {
        return hwVersion;
    }

    public void setHwVersion(String hwVersion) {
        this.hwVersion = hwVersion;
    }

    public String getSysclock() {
        return sysclock;
    }

    public void setSysclock(String sysclock) {
        this.sysclock = sysclock;
    }

    public String getOsctrim() {
        return osctrim;
    }

    public void setOsctrim(String osctrim) {
        this.osctrim = osctrim;
    }

    public List<String> getPortName() {
        return portName;
    }

    public void setPortName(List<String> portName) {
        this.portName = portName;
    }

    public List<String> getMeasureType() {
        return measureType;
    }

    public void setMeasureType(List<String> measureType) {
        this.measureType = measureType;
    }

    public boolean equals(Family family) {
        if (this.id.equals(family.getId()) &&
                this.name.equals(family.getName()) &&
                this.hwVersion.equals(family.getHwVersion()) &&
                this.sysclock.equals(family.getSysclock()) &&
                this.osctrim.equals(family.getOsctrim()) &&
                this.portName.equals(family.getPortName()) &&
                this.measureType.equals(family.getMeasureType())) {
            return true;
        }
        return false;
    }

    public void checkNullField() {

        if (this.getName() == null) {
            this.setName("");
        }

        if (this.getId() == null) {
            this.setId("");
        }

        if (this.getHwVersion() == null) {
            this.setHwVersion("");
        }

        if (this.getSysclock() == null) {
            this.setSysclock("");
        }

        if (this.getOsctrim() == null) {
            this.setOsctrim("");
        }

        if (this.getMeasureType() == null) {
            this.measureType = new ArrayList<>();
        }

        if (this.getPortName() == null) {
            this.portName = new ArrayList<>();
        }
    }
}
