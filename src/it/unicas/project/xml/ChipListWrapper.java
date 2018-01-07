package it.unicas.project.xml;

import it.unicas.project.model.Chip;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Helper class to wrap a list of chips. This is used for saving the
 * list of chips to XML.
 */
@XmlRootElement(name = "CHIPS")
public class ChipListWrapper {

    private List<Chip> chips;

    @XmlElement(name = "CHIP")
    public List<Chip> getChips() {
        return chips;
    }

    public void setChips(List<Chip> chips) {
        this.chips = chips;
    }
}

