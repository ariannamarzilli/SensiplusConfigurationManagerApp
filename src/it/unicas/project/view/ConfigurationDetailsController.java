package it.unicas.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import it.unicas.project.dao.ClusterDAO;
import it.unicas.project.model.Cluster;
import it.unicas.project.model.Configuration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.List;

public class ConfigurationDetailsController {

    ObservableList<String> hostControllerList = FXCollections.observableArrayList("SMART_DEVICE", "PC", "MCU");

    ObservableList<String> addressingTypeList = FXCollections.observableArrayList("No-Address", "Full-Address", "Short-Address");

    @FXML private JFXTextField driverTextField;

    @FXML private JFXComboBox<String> hostControllerComboBox;

    @FXML private JFXTextField apiOwnerTextField;

    @FXML private JFXTextField mcuTextField;

    @FXML private JFXComboBox<String> protocolComboBox;

    @FXML private JFXComboBox<String> addressingTypeComboBox;

    @FXML private JFXComboBox<String> clusterComboBox;

    private Configuration configuration;
    private Stage dialogStage;

    @FXML
    private void initialize() {

        hostControllerComboBox.setItems(hostControllerList);
        addressingTypeComboBox.setItems(addressingTypeList);

        ObservableList<String> allClusters = FXCollections.observableArrayList();
        List<Cluster> clusters = ClusterDAO.getInstance().fetchAll();
        clusters.stream().forEach(cluster -> allClusters.add(cluster.getId()));

        clusterComboBox.setItems(allClusters);
    }

    @FXML
    private void handleSaveChanges() {

        if (driverTextField.getText().isEmpty()) {
            configuration.setDriver("");
        } else {
            configuration.setDriver(driverTextField.getText());
        }
        if (hostControllerComboBox.getSelectionModel().getSelectedItem().isEmpty()) {
            configuration.setHostController("");
        } else {
            configuration.setHostController(hostControllerComboBox.getValue());
        }
        if (apiOwnerTextField.getText().isEmpty()) {
            configuration.setApiOwner("");
        } else {
            configuration.setApiOwner(apiOwnerTextField.getText());
        }
        if (mcuTextField.getText().isEmpty()) {
            configuration.setMcu("");
        } else {
            configuration.setMcu(mcuTextField.getText());
        }
        if (protocolComboBox.getSelectionModel().getSelectedItem().isEmpty()) {
            configuration.setProtocol("");
        } else {
            configuration.setProtocol(protocolComboBox.getValue());
        }
        if (addressingTypeComboBox.getValue().isEmpty()) {
            configuration.setAddressingType("");
        } else {
            configuration.setAddressingType(addressingTypeComboBox.getValue());
        }
        if (clusterComboBox.getSelectionModel().getSelectedItem().isEmpty()) {
            configuration.setIdCluster("");
        } else {
            configuration.setIdCluster(clusterComboBox.getValue());
        }

        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        this.dialogStage.close();
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;

        this.driverTextField.setText(configuration.getDriver());
        this.hostControllerComboBox.setValue(configuration.getHostController());
        this.apiOwnerTextField.setText(configuration.getApiOwner());
        this.mcuTextField.setText(configuration.getMcu());
        this.protocolComboBox.setValue(configuration.getProtocol());
        this.addressingTypeComboBox.setValue(configuration.getAddressingType());
        this.clusterComboBox.setValue(configuration.getIdCluster());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleAddressingType() {

        if (addressingTypeComboBox.getSelectionModel().getSelectedItem().equals("No-Address")) {
            ObservableList<String> protocolsNoAddress = FXCollections.observableArrayList("SPI", "SENSIBUS");
            protocolComboBox.setItems(protocolsNoAddress);
        } else if (addressingTypeComboBox.getSelectionModel().getSelectedItem().equals("Full-Address")) {
            ObservableList<String> protocolsFullAddress = FXCollections.observableArrayList("SENSIBUS");
            protocolComboBox.setItems(protocolsFullAddress);
        } else if (addressingTypeComboBox.getSelectionModel().getSelectedItem().equals("Short-Address")) {
            ObservableList<String> protocolsShortAddress = FXCollections.observableArrayList("SENSIBUS", "I2C");
            protocolComboBox.setItems(protocolsShortAddress);
        }
    }

    @FXML
    private void handleProtocol() {

        if (protocolComboBox.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention");
            alert.setHeaderText("Choose first the Addressing Type");
            alert.showAndWait();
        }
    }

}
