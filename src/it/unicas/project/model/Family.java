package it.unicas.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

public class Family {

    private String id;
    private String name;
    private String hwVersion;
    private String sysclock;
    private String osctrim;
    private List<Port> ports;
    private List<String> measureType;


    public Family(){
        this.id = "";
        this.name = "MEASURING_INSTRUMENT";
        this.hwVersion = "RUN4";
        this.sysclock = "10000000";
        this.osctrim = "0X06";
        this.ports = new ArrayList<>();
        this.measureType = new ArrayList<>();
    }

    public Family(String id){

        this.id = id;
        this.name = "";
        this.hwVersion = "";
        this.sysclock = "";
        this.osctrim = "";
        this.ports = new ArrayList<>();
        this.measureType = new ArrayList<>();
    }

    public Family (String id, String name, String hwVersion,
                   String sysclock, String osctrim, List<Port> portName,
                   List<String> measureType){

        this.id = id;
        this.name = name;
        this.hwVersion = hwVersion;
        this.sysclock = sysclock;
        this.osctrim = osctrim;
        this.ports = portName;
        this.measureType = measureType;
    }


    public Family(Family family) {
        this.ports = new ArrayList<>();
        this.measureType = new ArrayList<>();
        this.setId(family.getId());
        this.setName(family.getName());
        this.setHwVersion(family.getHwVersion());
        this.setSysclock(family.getSysclock());
        this.setOsctrim(family.getOsctrim());

        for (int i = 0; i < family.getPorts().size(); i++) {
            this.ports.add(family.getPorts().get(i));
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

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> portName) {
        this.ports = portName;
    }

    public List<String> getMeasureType() {
        return measureType;
    }

    public void setMeasureType(List<String> measureType) {
        this.measureType = measureType;
    }

    @Override
    public boolean equals(Object o) {
        Family family = (Family) o;

        if (this.id.equals(family.getId()) &&
                this.name.equals(family.getName()) &&
                this.hwVersion.equals(family.getHwVersion()) &&
                this.sysclock.equals(family.getSysclock()) &&
                this.osctrim.equals(family.getOsctrim()) &&
                this.ports.equals(family.getPorts()) &&
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

        if (this.getPorts() == null) {
            this.ports = new ArrayList<>();
        }
    }
}
