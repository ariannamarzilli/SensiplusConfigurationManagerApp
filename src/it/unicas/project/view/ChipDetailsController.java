package it.unicas.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import it.unicas.project.dao.FamilyDAO;
import it.unicas.project.model.Chip;
import it.unicas.project.model.Family;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.util.List;

public class ChipDetailsController {

    @FXML
    private JFXTextField idChipTextField;

    @FXML
    private JFXComboBox<String> familyNameBox;

    private Stage dialogStage;
    private Chip chip;
    private boolean isAnUpdate;

    @FXML
    private void initialize() {

        List <Family> families = FamilyDAO.getInstance().fetchAll();
        ObservableList<String> familyNames = FXCollections.observableArrayList();
        families.stream().forEach(family -> familyNames.add(family.getName()));
        familyNameBox.setItems(familyNames);

    }

    public void setChip(Chip chip) {
        this.chip = chip;

        idChipTextField.setText(chip.getId());
        familyNameBox.setValue(chip.getFamilyName());
    }

    @FXML
    private void handleSaveChanges() {

        if (isInputCorrect()) {
            this.chip.setId(idChipTextField.getText());
            this.chip.setFamilyName(familyNameBox.getValue());

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uncorrect values");
            alert.setHeaderText("Choose an ID and a Family for the chip");
            alert.showAndWait();
        }

        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        this.dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private boolean isInputCorrect() {
        if (!idChipTextField.getText().isEmpty() && !familyNameBox.getValue().isEmpty()) {
            return true;
        }
        return false;
    }

    public void setAnUpdate(boolean isAnUpdate) {
        this.isAnUpdate = isAnUpdate;
        handleIdTextField(isAnUpdate);
    }

    private void handleIdTextField(boolean isAnUpdate) {
        idChipTextField.setDisable(isAnUpdate);
    }
}
