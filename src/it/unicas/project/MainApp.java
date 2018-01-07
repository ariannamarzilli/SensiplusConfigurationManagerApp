package it.unicas.project;

import it.unicas.project.dao.*;
import it.unicas.project.model.*;
import it.unicas.project.view.*;
import it.unicas.project.xml.FamilyListWrapper;
import it.unicas.project.xml.SensingElementListWrapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.prefs.Preferences;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootWindow;
    private Stage clusterStage;
    private boolean isCancelPressed;

    private ObservableList<Family> familyData = FXCollections.observableArrayList();
    private ObservableList<Chip> chipData = FXCollections.observableArrayList();
    private ObservableList<Cluster> clusterData = FXCollections.observableArrayList();
    private ObservableList<Configuration> configurationData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sensiplus Configuration Manager");
        initFirstWindow();
    }

    public void initFirstWindow() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FirstWindow.fxml"));
            AnchorPane firstWindow = (AnchorPane) loader.load();

            Scene scene = new Scene(firstWindow);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            FirstWindowController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showSecondWindow() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SecondWindow.fxml"));
            rootWindow = (BorderPane) loader.load();
            Scene scene = new Scene(rootWindow);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.show();

            SecondWindowController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSettings() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Connection.fxml"));
            AnchorPane settingsOverview = (AnchorPane) loader.load();
            rootWindow.setCenter(settingsOverview);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSensingElementOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SensingElementOverview.fxml"));
            AnchorPane sensingElementOverview = (AnchorPane) loader.load();
            rootWindow.setCenter(sensingElementOverview);
            SensingElementOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showFamilyOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FamilyOverview.fxml"));
            AnchorPane familyOverview = (AnchorPane) loader.load();
            rootWindow.setCenter(familyOverview);
            FamilyOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChipOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ChipOverview.fxml"));
            AnchorPane chipOverview = (AnchorPane) loader.load();
            rootWindow.setCenter(chipOverview);
            ChipOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showClusterOverview() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ClusterOverview.fxml"));
            AnchorPane clusterOverview = (AnchorPane) loader.load();
            rootWindow.setCenter(clusterOverview);
            ClusterOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showConfigurationOverview() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ConfigurationOverview.fxml"));
            AnchorPane configurationOverview = (AnchorPane) loader.load();
            rootWindow.setCenter(configurationOverview);
            ConfigurationOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showSensingElementEditDialog(SensingElement sensingElement, boolean isAnUpdate) {
        try{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((MainApp.class.getResource("view/SensingElementDetails.fxml")));
            AnchorPane page = (AnchorPane) loader.load();

            //dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Show Sensing Element");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //set sensing element into the controller
            SensingElementDetailsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSensingElement(sensingElement);
            controller.setAnUpdate(isAnUpdate);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showFamilyEditDialog(Family family, boolean isAnUpdate) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FamilyDetails.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Show Family");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            FamilyDetailsController controller = loader.getController();
            controller.setFamily(family);
            controller.setDialogStage(dialogStage);
            controller.setAnUpdate(isAnUpdate);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChipEditDialog(Chip chip, boolean isAnUpdate) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ChipDetails.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Show Chip");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ChipDetailsController controller = loader.getController();
            controller.setChip(chip);
            controller.setDialogStage(dialogStage);
            controller.setAnUpdate(isAnUpdate);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showClusterEditDialog(Cluster cluster, boolean isAnUpdate) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ClusterDetails.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //dialog stage
            Stage dialogStage = new Stage();
            this.setClusterStage(dialogStage);
            dialogStage.setTitle("Show Clusters");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ClusterDetailsController controller = loader.getController();
            controller.setAnUpdate(isAnUpdate);
            controller.setCluster(cluster);
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showClusterCalibrationsEditDialog(Cluster cluster) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ClusterCalibrationsDetails.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = getClusterStage();
            dialogStage.setTitle("Show Cluster Calibrations");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ClusterCalibrationsDetailsController controller = loader.getController();
            controller.setCluster(cluster);
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showConfigurationEditDialog(Configuration configuration) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ConfigurationDetails.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Show Configuration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ConfigurationDetailsController controller = loader.getController();
            controller.setConfiguration(configuration);
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Stage getClusterStage() {
        return clusterStage;
    }

    public void setClusterStage(Stage clusterStage) {
        this.clusterStage = clusterStage;
    }

    public boolean isCancelPressed() {
        return isCancelPressed;
    }

    public void setCancelPressed(boolean cancelPressed) {
        isCancelPressed = cancelPressed;
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

        } else {
            prefs.remove("filePath");
        }
    }

    /**
     * Saves the current data to the specified file.
     *
     * @param file
     */
    public void saveDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(SensingElementListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            SensingElementListWrapper sensingElementListWrapper = new SensingElementListWrapper();
            FamilyListWrapper familyListWrapper = new FamilyListWrapper();

            ObservableList<SensingElement> sensingElementData = FXCollections.observableList(SensingElementDAO.getInstance().fetchAll());
            ObservableList<Family> familyData = FXCollections.observableList(FamilyDAO.getInstance().fetchAll());
            ObservableList<Chip> chipData = FXCollections.observableList(ChipDAO.getInstance().fetchAll());
            ObservableList<Cluster> clusterData = FXCollections.observableList(ClusterDAO.getInstance().fetchAll());
            ObservableList<Configuration> configurationData = FXCollections.observableList(ConfigurationDAO.getInstance().fetchAll());

            sensingElementListWrapper.setSensingElements(sensingElementData);
            familyListWrapper.setFamilies(familyData);

            // Marshalling and saving XML to the file.
            m.marshal(sensingElementListWrapper, file);

            // Save the file path to the registry.
            setFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}



