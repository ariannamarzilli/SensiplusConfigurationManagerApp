package it.unicas.project.view;

import it.unicas.project.MainApp;
import javafx.fxml.FXML;

public class FirstWindowController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handleGetStarted() {
        this.mainApp.showSecondWindow();
    }

}
