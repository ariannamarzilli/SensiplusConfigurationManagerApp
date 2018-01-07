package it.unicas.project.xml;

import it.unicas.project.model.SensingElement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Helper class to wrap a list of sensing elements. This is used for saving the
 * list of sensing elements to XML.
 */
@XmlRootElement(name = "SENSING_ELEMENTS")
public class SensingElementListWrapper {

    private List<SensingElement> sensingElements;

    @XmlElement(name = "SENSING_ELEMENT")
    public List<SensingElement> getSensingElements() {
        return sensingElements;
    }

    public void setSensingElements(List<SensingElement> sensingElements) {
        this.sensingElements = sensingElements;
    }
}
