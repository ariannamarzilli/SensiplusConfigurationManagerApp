package it.unicas.project.xml;

import it.unicas.project.model.Cluster;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Helper class to wrap a list of clusters. This is used for saving the
 * list of clusters to XML.
 */
@XmlRootElement(name = "CLUSTERS")
public class ClusterListWrapper {

    private List<Cluster> clusters;

    @XmlElement(name = "CLUSTER")
    public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }
}