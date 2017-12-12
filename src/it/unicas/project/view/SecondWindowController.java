package it.unicas.project.view;

import it.unicas.project.MainApp;

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

}
