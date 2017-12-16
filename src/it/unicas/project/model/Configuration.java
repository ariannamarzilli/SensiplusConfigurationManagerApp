package it.unicas.project.model;

public class Configuration {

    private String driver;
    private String hostController;
    private String apiOwner;
    private String mcu;
    private String protocol;
    private String addressingType;
<<<<<<< HEAD
    private String idCluster;
    private int id;


=======
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

>>>>>>> 1bba8cf022dd3829bba439921b8280859eabd3a6
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

<<<<<<< HEAD
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
=======
    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
>>>>>>> 1bba8cf022dd3829bba439921b8280859eabd3a6
    }

    @Override
    public boolean equals(Object o) {
        Configuration configuration = (Configuration) o;

<<<<<<< HEAD
        if (this.getDriver().equals(((Configuration) o).getDriver()) &&
                this.getAddressingType().equals(((Configuration) o).getAddressingType()) &&
                this.getApiOwner().equals(((Configuration) o).getApiOwner()) &&
                this.getHostController().equals(((Configuration) o).getHostController()) &&
                this.getMcu().equals(((Configuration) o).getMcu()) &&
                this.getProtocol().equals(((Configuration) o).getProtocol()) &&
                this.getIdCluster().equals(((Configuration) o).getIdCluster())) {
=======
        if (this.getDriver().equals(configuration.getDriver()) &&
                this.getHostController().equals(configuration.getHostController()) &&
                this.getApiOwner().equals(configuration.getApiOwner()) &&
                this.getMcu().equals(configuration.getMcu()) &&
                this.getProtocol().equals(configuration.getMcu()) &&
                this.getAddressingType().equals(configuration.getAddressingType()) &&
                this.getCluster().equals(configuration.getCluster())) {
>>>>>>> 1bba8cf022dd3829bba439921b8280859eabd3a6
            return true;
        }
        return false;
    }

    public void checkNullField() {
<<<<<<< HEAD
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


=======
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
>>>>>>> 1bba8cf022dd3829bba439921b8280859eabd3a6
}
