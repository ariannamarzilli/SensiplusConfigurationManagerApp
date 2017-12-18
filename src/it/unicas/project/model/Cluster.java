package it.unicas.project.model;

import java.util.ArrayList;
import java.util.List;

public class Cluster {



    private String id;
    List<ChipWithCalibration> chipWithCalibrations;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ChipWithCalibration> getChipWithCalibrations() {
        return chipWithCalibrations;
    }

    public void setChipWithCalibrations(List<ChipWithCalibration> chipWithCalibrations) {
        this.chipWithCalibrations = chipWithCalibrations;
    }

    public Cluster(String id, List<ChipWithCalibration> chipWithCalibrations) {
        this.id = id;
        this.chipWithCalibrations = chipWithCalibrations;
    }

    public Cluster(String id) {
        this.id = id;
    }

    public boolean equals(Cluster cluster) {
        if (this.id.equals(cluster.getId()) &&
                this.chipWithCalibrations.equals(cluster.getChipWithCalibrations())){
            return true;
        }
        return false;
    }

    public void checkNullField() {
        if (this.chipWithCalibrations == null) {
            chipWithCalibrations = new ArrayList<>();
        }
    }
}
