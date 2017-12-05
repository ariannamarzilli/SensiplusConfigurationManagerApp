package it.unicas.project.model;

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
        this.setPortName(null);
        this.setMeasureType(null);
    }

    public Family(String id){

        this.id = id;
        this.name = "";
        this.hwVersion = "";
        this.sysclock = "";
        this.osctrim = "";
        this.setPortName(null);
        this.setMeasureType(null);
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
}
