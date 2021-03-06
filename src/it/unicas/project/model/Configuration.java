package it.unicas.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CONFIGURATION")
@XmlType(propOrder = {"driver", "hostController", "apiOwner", "mcu", "protocol", "addressingType", "idCluster"})
public class Configuration {

    private String driver;
    private String hostController;
    private String apiOwner;
    private String mcu;
    private String protocol;
    private String addressingType;
    private String idCluster;
    private int id;



    public Configuration() {
        this.driver = "";
        this.hostController = "";
        this.apiOwner = "";
        this.mcu = "";
        this.protocol = "";
        this.addressingType = "";
        this.idCluster = "";
    }

    public String getDriver() {
        return driver;
    }

    @XmlElement(name = "DRIVER")
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getHostController() {
        return hostController;
    }

    @XmlElement(name = "HOST_CONTROLLER")
    public void setHostController(String hostController) {
        this.hostController = hostController;
    }

    public String getApiOwner() {
        return apiOwner;
    }

    @XmlElement(name = "API_OWNER")
    public void setApiOwner(String apiOwner) {
        this.apiOwner = apiOwner;
    }

    public String getMcu() {
        return mcu;
    }

    @XmlElement(name = "MCU")
    public void setMcu(String mcu) {
        this.mcu = mcu;
    }

    public String getProtocol() {
        return protocol;
    }

    @XmlElement(name = "PROTOCOL")
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddressingType() {
        return addressingType;
    }

    @XmlElement(name = "ADDRESSING_TYPE")
    public void setAddressingType(String addressingType) {
        this.addressingType = addressingType;
    }

    public String getIdCluster() {
        return idCluster;
    }

    @XmlElement(name = "CLUSTER_ID")
    public void setIdCluster(String idCluster) {
        this.idCluster = idCluster;
    }

    public int getId() {
        return id;
    }

    @XmlTransient()
    public void setId(int id) {
        this.id = id;
    }

    public Configuration(String driver, String hostController, String apiOwner, String mcu, String protocol, String addressingType, String idCluster) {
        this.driver = driver;
        this.hostController = hostController;
        this.apiOwner = apiOwner;
        this.mcu = mcu;
        this.protocol = protocol;
        this.addressingType = addressingType;
        this.idCluster = idCluster;
    }

    public Configuration(String driver, String hostController, String apiOwner, String mcu, String protocol, String addressingType, String idCluster, int id) {
        this.driver = driver;
        this.hostController = hostController;
        this.apiOwner = apiOwner;
        this.mcu = mcu;
        this.protocol = protocol;
        this.addressingType = addressingType;
        this.idCluster = idCluster;
        this.id = id;
    }

    public Configuration(Configuration configuration) {
        this.driver = configuration.getDriver();
        this.hostController = configuration.getHostController();
        this.apiOwner = configuration.getApiOwner();
        this.mcu = configuration.getMcu();
        this.protocol = configuration.getProtocol();
        this.addressingType = configuration.getAddressingType();
        this.idCluster = configuration.getIdCluster();

    }

    @Override
    public boolean equals(Object o) {
        Configuration configuration = (Configuration) o;

        if (this.getDriver().equals(configuration.getDriver()) &&
                this.getAddressingType().equals(configuration.getAddressingType()) &&
                this.getProtocol().equals(configuration.getProtocol()) &&
                this.getMcu().equals(configuration.getMcu()) &&
                this.getApiOwner().equals(configuration.getApiOwner()) &&
                this.getHostController().equals(configuration.getHostController()) &&
                this.getIdCluster().equals(configuration.getIdCluster())) {
            return true;
        }
        return false;
    }

    public void checkNullField() {
        if (this.getDriver() == null) {
            this.setDriver("");
        }
        if (this.getAddressingType() == null) {
            this.setAddressingType("");
        }
        if (this.getHostController() == null) {
            this.setHostController("");
        }
        if (this.getApiOwner() == null) {
            this.setApiOwner("");
        }
        if (this.getMcu() == null) {
            this.setMcu("");
        }
        if (this.getProtocol() == null) {
            this.setProtocol("");
        }


    }


}
