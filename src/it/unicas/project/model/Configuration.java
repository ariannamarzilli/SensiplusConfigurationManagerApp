package it.unicas.project.model;

public class Configuration {

    private String driver;
    private String hostController;
    private String apiOwner;
    private String mcu;
    private String protocol;
    private String addressingType;
    private String idCluster;
    private int id;


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

    public String getIdCluster() {
        return idCluster;
    }

    public void setIdCluster(String idCluster) {
        this.idCluster = idCluster;
    }

    public int getId() {
        return id;
    }

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

    public Configuration(Configuration configuration){
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

        if (this.getDriver().equals(((Configuration) o).getDriver()) &&
                this.getAddressingType().equals(((Configuration) o).getAddressingType()) &&
                this.getApiOwner().equals(((Configuration) o).getApiOwner()) &&
                this.getHostController().equals(((Configuration) o).getHostController()) &&
                this.getMcu().equals(((Configuration) o).getMcu()) &&
                this.getProtocol().equals(((Configuration) o).getProtocol()) &&
                this.getIdCluster().equals(((Configuration) o).getIdCluster())) {
            return true;
        }
        return false;
    }

    public void checkNullField() {
        if (this.getDriver() == null) {
            this.setDriver("");
        }
        if (this.getProtocol() == null){
            this.setProtocol("");
        }
        if (this.getMcu() == null){
            this.setMcu("");
        }
        if (this.getHostController() == null){
            this.setHostController("");
        }
        if (this.getApiOwner() == null){
            this.setApiOwner("");
        }
        if (this.getAddressingType() == null){
            this.setAddressingType("");
        }


    }


}
