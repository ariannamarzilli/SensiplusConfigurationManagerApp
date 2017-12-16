package it.unicas.project.model;

public class Configuration {

    private String driver;
    private String hostController;
    private String apiOwner;
    private String mcu;
    private String protocol;
    private String addressingType;
    private String cluster;


    public Configuration() {
        this.driver = "";
        this.hostController = "";
        this.apiOwner = "";
        this.mcu = "";
        this.protocol = "";
        this.addressingType = "";
        this.cluster = "";
    }

    public Configuration(Configuration configuration) {

        this.setDriver(configuration.getDriver());
        this.setHostController(configuration.getHostController());
        this.setApiOwner(configuration.getApiOwner());
        this.setMcu(configuration.getMcu());
        this.setProtocol(configuration.getProtocol());
        this.setAddressingType(configuration.getAddressingType());
        this.setCluster(configuration.getCluster());
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getHostController() {
        return hostController;
    }

    public void setHostController(String hostController) {
        this.hostController = hostController;
    }

    public String getApiOwner() {
        return apiOwner;
    }

    public void setApiOwner(String apiOwner) {
        this.apiOwner = apiOwner;
    }

    public String getMcu() {
        return mcu;
    }

    public void setMcu(String mcu) {
        this.mcu = mcu;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddressingType() {
        return addressingType;
    }

    public void setAddressingType(String addressingType) {
        this.addressingType = addressingType;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    @Override
    public boolean equals(Object o) {
        Configuration configuration = (Configuration) o;

        if (this.getDriver().equals(configuration.getDriver()) &&
                this.getHostController().equals(configuration.getHostController()) &&
                this.getApiOwner().equals(configuration.getApiOwner()) &&
                this.getMcu().equals(configuration.getMcu()) &&
                this.getProtocol().equals(configuration.getMcu()) &&
                this.getAddressingType().equals(configuration.getAddressingType()) &&
                this.getCluster().equals(configuration.getCluster())) {
            return true;
        }
        return false;
    }

    public void checkNullField() {
        if (this.driver == null) {
            driver = "";
        }
        if (this.hostController == null) {
            hostController = "";
        }
        if (this.apiOwner == null) {
            apiOwner = "";
        }
        if (this.mcu == null) {
            mcu = "";
        }
        if (this.protocol == null) {
            protocol = "";
        }
        if (this.addressingType == null) {
            addressingType = "";
        }
        if (this.cluster == null) {
            cluster = "";
        }
    }
}
