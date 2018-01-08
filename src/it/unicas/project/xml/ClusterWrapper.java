package it.unicas.project.xml;

import it.unicas.project.model.Cluster;
import it.unicas.project.model.Family;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "CLUSTER")
@XmlType(propOrder = {"id", "chips"})
public class ClusterWrapper {

    private String id;
    List<ChipWrapper> chips = new ArrayList<>();

    public ClusterWrapper() {
    }

    public ClusterWrapper(Cluster cluster, List<Family> families) {
        id = cluster.getId();
        Family family = new Family();

        for (int i = 0; i < families.size(); i++) {
            for (int j = 0; j < cluster.getChipWithCalibrations().size(); j++) {
                if (cluster.getChipWithCalibrations().get(j).getChip().getFamilyName().equals(families.get(i).getName())) {
                    family = families.get(i);
                }
            }
        }

        for (int i = 0; i < cluster.getChipWithCalibrations().size(); i++) {
            ChipWrapper chipXml = new ChipWrapper(cluster.getChipWithCalibrations().get(i), family);
            chips.add(chipXml);
        }
    }

    public ClusterWrapper(String id, List<ChipWrapper> chips) {
        this.id = id;
        this.chips = chips;
    }

    public String getId() {
        return id;
    }

    @XmlElement(name = "CLUSTER_ID")
    public void setId(String id) {
        this.id = id;
    }

    public List<ChipWrapper> getChips() {
        return chips;
    }

    @XmlElement(name = "CHIP")
    public void setChips(List<ChipWrapper> chips) {
        this.chips = chips;
    }
}
