package it.unicas.project.view;

import it.unicas.project.model.Family;
import javafx.stage.Stage;

public class FamilyDetailsController {

    private Stage dialogStage;
    private Family family;

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public void setFamily(Family family) {
        this.family = family;
    }
}
