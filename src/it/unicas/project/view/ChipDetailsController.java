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

    /**
     * This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        List <Family> families = FamilyDAO.getInstance().fetchAll();
        ObservableList<String> familyNames = FXCollections.observableArrayList();
        families.stream().forEach(family -> familyNames.add(family.getName()));
        familyNameBox.setItems(familyNames);

    }

    /**
     * Sets the chip to be edited in the dialog.
     * @param chip to be edited
     */
    public void setChip(Chip chip) {
        this.chip = chip;

        idChipTextField.setText(chip.getId());
        familyNameBox.setValue(chip.getFamilyName());
    }

    /**
     * Called when the user clicks Save Changes.
     */
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

    /**
     * Validates the user input in the fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputCorrect() {
        if (!idChipTextField.getText().isEmpty() && !familyNameBox.getValue().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * If it's an update, it prevents from changing id.
     * @return
     */
    public void setAnUpdate(boolean isAnUpdate) {
        this.isAnUpdate = isAnUpdate;
        handleIdTextField(isAnUpdate);
    }

    private void handleIdTextField(boolean isAnUpdate) {
        idChipTextField.setDisable(isAnUpdate);
    }
}
