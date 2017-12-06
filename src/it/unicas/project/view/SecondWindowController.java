package it.unicas.project.view;

import it.unicas.project.MainApp;

public class SecondWindowController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void handleSensingElement() {
        this.mainApp.showSensingElementTable();
    }

    public void handleFamily() {
        this.mainApp.showFamilyTable();
    }

    public void handleChip() {
        this.mainApp.showChipTable();
    }

    public void handleCluster() {
        this.mainApp.showClusterTable();
    }

    public void handleConfiguration() {
        this.mainApp.showConfigurationTable();
    }

}
