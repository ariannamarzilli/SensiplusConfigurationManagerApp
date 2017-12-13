package it.unicas.project.view;

import it.unicas.project.model.SensingElementOnChip;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ChipDetailsController {

    private Stage dialogStage;
    SensingElementOnChip sensingElementOnChip;

    @FXML
    private void initialize() {

    }

    public void setSensingElementOnChip(SensingElementOnChip sensingElementOnChip) {
        this.sensingElementOnChip = sensingElementOnChip;
    }

    @FXML
    private void handleSaveChanges() {

    }

    @FXML
    private void handleCancel() {
        this.dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


}
