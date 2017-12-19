package it.unicas.project.view;

import it.unicas.project.MainApp;
import it.unicas.project.dao.ConfigurationDAO;
import it.unicas.project.model.Configuration;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import java.util.List;

public class ConfigurationOverviewController {

    @FXML private TableView<Configuration> configurationTableView = new TableView<>();

    @FXML private TableColumn<Configuration, String> driverColumn;

    @FXML private TableColumn<Configuration, String> hostControllerColumn;

    @FXML private TableColumn<Configuration, String> apiOwnerColumn;

    @FXML private TableColumn<Configuration, String> mcuColumn;

    @FXML private TableColumn<Configuration, String> protocolColumn;

    @FXML private TableColumn<Configuration, String> addressingTypeColumn;

    @FXML private TableColumn<Configuration, String> clusterColumn;

    ObservableList<Configuration> configurationData;
    private MainApp mainApp;

    public void initialize() {

        List<Configuration> configurations = ConfigurationDAO.getInstance().fetchAll();
        ObservableList<Configuration> configurationObservableList = FXCollections.observableArrayList();

        for (int i = 0; i < configurations.size(); i++) {
            configurations.get(i).checkNullField();
            configurationObservableList.add(configurations.get(i));
        }

        this.setConfigurationData(configurationObservableList);

        driverColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDriver()));
        hostControllerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHostController()));
        apiOwnerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApiOwner()));
        mcuColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMcu()));
        protocolColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProtocol()));
        addressingTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddressingType()));
        clusterColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdCluster()));

        configurationTableView.setItems(configurationData);

    }

    @FXML
    private void handleNew() {
        Configuration configuration = new Configuration();
        Configuration oldConfiguration = new Configuration(configuration);
        mainApp.showConfigurationEditDialog(configuration);
        if (!configuration.equals(oldConfiguration)) {
            ConfigurationDAO.getInstance().create(configuration);
            configurationData.add(configuration);

            configurationTableView.setItems(configurationData);
        }
    }

    @FXML
    private void handleDelete() {
        int selectedIndex = configurationTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Confirm delete");
            alert.setHeaderText("Are you sure you want to delete this item?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                Configuration configuration = configurationTableView.getItems().get(selectedIndex);
                ConfigurationDAO.getInstance().delete(configuration);
                configurationTableView.getItems().remove(selectedIndex);
            }
        }
    }

    @FXML
    private void handleClick(MouseEvent event) {

        if ((!configurationData.isEmpty()) && configurationTableView.getSelectionModel().getSelectedItem() != null) {

            Configuration configuration = configurationTableView.getSelectionModel().getSelectedItem();

            if (event.getClickCount() == 2) {
                Configuration oldConfiguration = new Configuration(configuration);
                mainApp.showConfigurationEditDialog(configuration);
                if (!configuration.equals(oldConfiguration)) {
                    ConfigurationDAO.getInstance().update(configuration);
                    configurationData.remove(oldConfiguration);
                    configurationData.add(configuration);
                    configurationTableView.setItems(configurationData);
                }
            }
        }

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setConfigurationData(ObservableList<Configuration> configurationData) {
        this.configurationData = configurationData;
    }
}
