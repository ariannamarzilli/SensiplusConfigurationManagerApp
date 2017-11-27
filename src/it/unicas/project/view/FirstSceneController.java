package it.unicas.project.view;

import it.unicas.project.MainApp;
import it.unicas.project.model.Elements;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class FirstSceneController {

    @FXML
    private TableView<Elements> elementsTableView;

    private boolean sensingElementClicked = false;


    private MainApp mainApp;

    public FirstSceneController() {
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleSensingElement(){
        sensingElementClicked = true;
        //SensingElement tempSensingElement = new SensingElement();
        mainApp.showSensingElementDialog();

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    public boolean isSensingElementClicked() {
        return sensingElementClicked;
    }



}
