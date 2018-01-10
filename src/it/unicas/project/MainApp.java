package it.unicas.project;

import it.unicas.project.dao.*;
import it.unicas.project.model.*;
import it.unicas.project.view.*;
import it.unicas.project.xml.ClusterWrapper;
import it.unicas.project.xml.FamilyWrapper;
import it.unicas.project.xml.Sensichips;
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            JAXBContext context = JAXBContext.newInstance(Sensichips.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Sensichips sensichips = new Sensichips();


            // configuration
            ObservableList<Configuration> configurationData = FXCollections.observableList(ConfigurationDAO.getInstance().fetchAll());


            //cluster
            List<Cluster> clusters = ClusterDAO.getInstance().fetchAll();
            clusters = handleClusterData(configurationData, clusters);
            List<ClusterWrapper> clusterXmlList = new ArrayList<>();
            for (int i = 0; i < clusters.size(); i++) {
                ClusterWrapper clusterXml = new ClusterWrapper(clusters.get(i), FamilyDAO.getInstance().fetchAll());
                clusterXmlList.add(clusterXml);
            }
            ObservableList<ClusterWrapper> clusterData = FXCollections.observableList(clusterXmlList);


            //family
            List<Family> families = FamilyDAO.getInstance().fetchAll();
            families = handleFamilyData(clusters, families);
            List<FamilyWrapper> familyWrappers = new ArrayList<>();

            for (int i = 0; i < families.size(); i++) {
                FamilyWrapper familyWrapper = new FamilyWrapper(families.get(i));
                familyWrappers.add(familyWrapper);
            }
            ObservableList<FamilyWrapper> familyData = FXCollections.observableList(familyWrappers);


            // sensing element
            List<SensingElement> sensingElements = SensingElementDAO.getInstance().fetchAll();
            sensingElements = handleSensingElementData(families, sensingElements);
            ObservableList<SensingElement> sensingElementData = FXCollections.observableList(sensingElements);

            //se un sensing element effettua misure dirette, gli attributi settati a null non devono essere visualizzati nell'xml
            for (int i = 0; i < sensingElementData.size(); i++) {
                if (sensingElementData.get(i).getMeasureTechnique().equals("DIRECT")) {
                    sensingElementData.get(i).setrSense(null);
                    sensingElementData.get(i).setInGain(null);
                    sensingElementData.get(i).setOutGain(null);
                    sensingElementData.get(i).setContacts(null);
                    sensingElementData.get(i).setFrequency(null);
                    sensingElementData.get(i).setHarmonic(null);
                    sensingElementData.get(i).setDcBias(null);
                    sensingElementData.get(i).setModeVI(null);
                    sensingElementData.get(i).setMeasureType(null);
                    sensingElementData.get(i).setPhaseShiftMode(null);
                    sensingElementData.get(i).setPhaseShift(null);
                    sensingElementData.get(i).setIq(null);
                }
            }


            sensichips.setSensingElements(sensingElementData);
            sensichips.setFamilies(familyData);
            sensichips.setClusters(clusterData);
            sensichips.setConfigurations(configurationData);

            // Marshalling and saving XML to the file.
            m.marshal(sensichips, file);

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

    private List<Cluster> handleClusterData(List<Configuration> configurations, List<Cluster> clusters) {

        List<Integer> indexInClusterList = new ArrayList<>();

        for (int i = 0; i < configurations.size(); i++) {
            for (int j = 0; j < clusters.size(); j++) {
                if (configurations.get(i).getIdCluster().equals(clusters.get(j).getId())) {
                    if (!indexInClusterList.contains(j)) {
                        indexInClusterList.add(j);
                    }
                }
            }
        }

        List<Cluster> clusters1 = new ArrayList<>();

        for (int i = 0; i < indexInClusterList.size(); i++) {
            clusters1.add(clusters.get(indexInClusterList.get(i)));
        }

        return clusters1;

    }

    private List<Family> handleFamilyData(List<Cluster> clusters, List<Family> families) {

        List<Integer> indexInClusterList = new ArrayList<>();

        for (int i = 0; i < clusters.size(); i++) {
            for (int j = 0; j < families.size(); j++) {
                if (clusters.get(i).getFamily().equals(families.get(j).getName())) {
                    if (!indexInClusterList.contains(j)) {
                        indexInClusterList.add(j);
                    }
                }
            }
        }

        List<Family> families1 = new ArrayList<>();

        for (int i = 0; i < indexInClusterList.size(); i++) {
            families1.add(families.get(indexInClusterList.get(i)));
        }

        return families1;
    }

    private List<SensingElement> handleSensingElementData(List<Family> families, List<SensingElement> sensingElements) {

        List<Integer> indexInClusterList = new ArrayList<>();

        for (int i = 0; i < families.size(); i++) {
            List<String> sensingElementIdForFamily = families.get(i).getSensingElementId();
            for (int j = 0; j < sensingElementIdForFamily.size(); j++) {
                for (int h = 0; h < sensingElements.size(); h++) {
                    if (sensingElements.get(h).getId().equals(sensingElementIdForFamily.get(j))) {
                        if (!indexInClusterList.contains(h)) {
                            indexInClusterList.add(h);
                        }
                    }
                }
            }
        }

        List<SensingElement> sensingElements1 = new ArrayList<>();

        for (int i = 0; i < indexInClusterList.size(); i++) {
            sensingElements1.add(sensingElements.get(indexInClusterList.get(i)));
        }

        return sensingElements1;
    }
}



