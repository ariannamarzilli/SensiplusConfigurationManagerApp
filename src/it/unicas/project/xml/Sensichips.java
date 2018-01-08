package it.unicas.project.xml;

import it.unicas.project.model.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "Sensichips")
@XmlType(propOrder = {"sensingElements", "families", "clusters", "configurations"})
public class Sensichips {

    private List<SensingElement> sensingElements;
    private List<FamilyWrapper> families;
    private List<ClusterWrapper> clusters;
    private List<Configuration> configurations;

    public List<SensingElement> getSensingElements() {
        return sensingElements;
    }

    @XmlElementWrapper(name = "SENSING_ELEMENTS")
    @XmlElement(name = "SENSING_ELEMENT")
    public void setSensingElements(List<SensingElement> sensingElements) {
        this.sensingElements = sensingElements;
    }

    public List<FamilyWrapper> getFamilies() {
        return families;
    }

    @XmlElementWrapper(name = "FAMILIES")
    @XmlElement(name = "FAMILY")
    public void setFamilies(List<FamilyWrapper> families) {
        this.families = families;
    }


    public List<ClusterWrapper> getClusters() {
        return clusters;
    }

    @XmlElement(name = "CLUSTER")
    public void setClusters(List<ClusterWrapper> clusters) {
        this.clusters = clusters;
    }


    public List<Configuration> getConfigurations() {
        return configurations;
    }

    @XmlElementWrapper(name = "CONFIGURATIONS")
    @XmlElement(name = "CONFIGURATION")
    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }
}