package it.unicas.project.xml;

import it.unicas.project.model.ChipWithCalibration;
import it.unicas.project.model.Family;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = {"familyId", "id", "sensingElementOnChip"})
public class ChipWrapper {

    private String familyId;
    private String id;
    List<SensingElementOnChipWrapper> sensingElementOnChip = new ArrayList<>();

    public ChipWrapper() {
    }

    public ChipWrapper(ChipWithCalibration chipWithCalibration, Family family) {
        id = chipWithCalibration.getChip().getId();
        familyId = family.getId();

        for (int i = 0; i < chipWithCalibration.getSensingElementWithCalibrations().size(); i++) {
            SensingElementOnChipWrapper sensingElementOnChipXml = new SensingElementOnChipWrapper(chipWithCalibration.getSensingElementWithCalibrations().get(i));
            sensingElementOnChip.add(sensingElementOnChipXml);
        }
    }


    public ChipWrapper(String familyId, String id, List<SensingElementOnChipWrapper> sensingElementOnChip) {
        familyId = familyId;
        this.id = id;
        this.sensingElementOnChip = sensingElementOnChip;
    }

    public String getFamilyId() {
        return familyId;
    }

    @XmlElement(name = "FAMILY_ID")
    public void setFamilyId(String familyId) {
        familyId = familyId;
    }

    public String getId() {
        return id;
    }

    @XmlElement(name = "SERIAL_NUMBER")
    public void setId(String id) {
        this.id = id;
    }

    public List<SensingElementOnChipWrapper> getSensingElementOnChip() {
        return sensingElementOnChip;
    }

    @XmlElement(name = "SENSING_ELEMENT_ONCHIP")
    public void setSensingElementOnChip(List<SensingElementOnChipWrapper> sensingElementOnChip) {
        this.sensingElementOnChip = sensingElementOnChip;
    }
}
