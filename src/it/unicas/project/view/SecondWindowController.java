package it.unicas.project.view;

import it.unicas.project.MainApp;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class SecondWindowController {

    private MainApp mainApp;

    @FXML
    private ImageView settingsImageView;

    @FXML
    private ImageView xmlImageView;

    /**
     * This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {

        ClassLoader loader = getClass().getClassLoader();
        settingsImageView.setImage(new Image(loader.getResourceAsStream("images/settingsIcon.png")));
        xmlImageView.setImage(new Image(loader.getResourceAsStream("images/xmlIcon.png")));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * It's called when the "Sensing Element" button is clicked
     */
    public void handleSensingElement() {
        this.mainApp.showSensingElementOverview();
    }

    /**
     * It's called when the "Family" button is clicked
     */
    public void handleFamily() {
        this.mainApp.showFamilyOverview();
    }

    /**
     * It's called when the "Chip" button is clicked
     */
    public void handleChip() {
        this.mainApp.showChipOverview();
    }

    /**
     * It's called when the "Cluster" button is clicked
     */
    public void handleCluster() {
        this.mainApp.showClusterOverview();
    }

    /**
     * It's called when the "Configuration" button is clicked
     */
    public void handleConfiguration() {
        this.mainApp.showConfigurationOverview();
    }

    /**
     * It's called when the "Settings" button is clicked
     */
    @FXML
    public void handleSettings() {
        this.mainApp.showSettings();
    }

    /**
     * It's called when the "New xml file" button is clicked
     */
    @FXML
    public void handleXml() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveDataToFile(file);
        }
    }
}
