package it.unicas.project.xml;

import it.unicas.project.model.Configuration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Helper class to wrap a list of configurations. This is used for saving the
 * list of configurations to XML.
 */
@XmlRootElement(name = "CONFIGURATIONS")
public class ConfigurationListWrapper {

    private List<Configuration> configurations;

    @XmlElement(name = "CONFIGURATION")
    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }
}