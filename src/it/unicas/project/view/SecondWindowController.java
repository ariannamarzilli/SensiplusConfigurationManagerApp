package it.unicas.project.view;

import it.unicas.project.MainApp;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class SecondWindowController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void handleSensingElement() {
        this.mainApp.showSensingElementOverview();
    }

    public void handleFamily() {
        this.mainApp.showFamilyOverview();
    }

    public void handleChip() {
        this.mainApp.showChipOverview();
    }

    public void handleCluster() {
        this.mainApp.showClusterOverview();
    }

    public void handleConfiguration() {
        this.mainApp.showConfigurationOverview();
    }

    @FXML
    public void handleSettings() {
        this.mainApp.showSettings();
    }

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
