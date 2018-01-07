package it.unicas.project.xml;

import it.unicas.project.model.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Sensichips")
public class Sensichips {

    private List<SensingElement> sensingElements;
    private List<Family> families;
    private List<Chip> chips;
    private List<Cluster> clusters;
    private List<Configuration> configurations;


    public List<SensingElement> getSensingElements() {
        return sensingElements;
    }

    @XmlElement(name = "SENSING_ELEMENTS")
    public void setSensingElements(List<SensingElement> sensingElements) {
        this.sensingElements = sensingElements;
    }


    public List<Family> getFamilies() {
        return families;
    }

    @XmlElement(name = "FAMILIES")
    public void setFamilies(List<Family> families) {
        this.families = families;
    }


    public List<Chip> getChips() {
        return chips;
    }

    @XmlElement(name = "CHIPS")
    public void setChips(List<Chip> chips) {
        this.chips = chips;
    }


    public List<Cluster> getClusters() {
        return clusters;
    }

    @XmlElement(name = "CLUSTERS")
    public void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }


    public List<Configuration> getConfigurations() {
        return configurations;
    }

    @XmlElement(name = "CONFIGURATIONS")
    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }
}
