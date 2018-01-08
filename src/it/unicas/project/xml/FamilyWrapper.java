package it.unicas.project.xml;

import it.unicas.project.model.Analyte;
import it.unicas.project.model.Family;
import it.unicas.project.model.Port;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "FAMILY")
@XmlType(propOrder = {"name", "id", "hwVersion", "sysclock", "osctrim", "measureType", "ports", "analyteList"})
public class FamilyWrapper {

    private String id;
    private String name;
    private String hwVersion;
    private String sysclock;
    private String osctrim;
    private List<Port> ports;
    private List<String> measureType;
    private List<Analyte> analyteList = new ArrayList<>();

    public FamilyWrapper() {
    }

    public FamilyWrapper(Family family) {

        id = family.getId();
        name = family.getName();
        hwVersion = family.getHwVersion();
        sysclock = family.getSysclock();
        osctrim = family.getOsctrim();
        ports = family.getPorts();
        measureType = family.getMeasureType();

        for(int i = 0; i < family.getPorts().size(); i++) {
            if (!family.getPorts().get(i).getAnalytes().isEmpty()) {
                for (int j = 0; j < family.getPorts().get(i).getAnalytes().size(); j++) {
                    analyteList.add(family.getPorts().get(i).getAnalytes().get(j));
                }
            }
            family.getPorts().get(i).setAnalytes(null);
        }
    }

    public String getId() {
        return id;
    }

    @XmlElement(name = "FAMILY_ID")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "FAMILY_NAME")
    public void setName(String name) {
        this.name = name;
    }

    public String getHwVersion() {
        return hwVersion;
    }

    @XmlElement(name = "HW_VERSION")
    public void setHwVersion(String hwVersion) {
        this.hwVersion = hwVersion;
    }

    public String getSysclock() {
        return sysclock;
    }

    @XmlElement(name = "SYS_CLOCK")
    public void setSysclock(String sysclock) {
        this.sysclock = sysclock;
    }

    public String getOsctrim() {
        return osctrim;
    }

    @XmlElement(name = "OSC_TRIM")
    public void setOsctrim(String osctrim) {
        this.osctrim = osctrim;
    }

    public List<Port> getPorts() {
        return ports;
    }

    @XmlElement(name = "SENSING_ELEMENT_ONFAMILY")
    public void setPorts(List<Port> portName) {
        this.ports = portName;
    }

    public List<String> getMeasureType() {
        return measureType;
    }

    @XmlElement(name = "MEASURE_TYPE")
    public void setMeasureType(List<String> measureType) {
        this.measureType = measureType;
    }


    public List<Analyte> getAnalyteList() {
        return analyteList;
    }

    @XmlElementWrapper(name = "ANALYTE_LIST")
    @XmlElement(name = "ANALYTE")
    public void setAnalyteList(List<Analyte> analyteList) {
        this.analyteList = analyteList;
    }
}
