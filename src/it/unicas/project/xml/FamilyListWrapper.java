package it.unicas.project.xml;

import it.unicas.project.model.Family;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Helper class to wrap a list of families. This is used for saving the
 * list of families to XML.
 */
@XmlRootElement(name = "FAMILIES")
public class FamilyListWrapper {

    private List<Family> families;

    @XmlElement(name = "FAMILY")
    public List<Family> getFamilies() {
        return families;
    }

    public void setFamilies(List<Family> families) {
        this.families = families;
    }
}
