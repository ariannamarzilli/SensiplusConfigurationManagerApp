package it.unicas.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import it.unicas.project.dao.FamilyDAO;
import it.unicas.project.model.Chip;
import it.unicas.project.model.Family;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ChipDetailsController {

    @FXML
    private JFXTextField idChipTextField;

    @FXML
    private JFXComboBox<String> familyNameBox;

    @FXML
    private GridPane gridPane;

    private Stage dialogStage;
    private Iterable<Family> families;
    private Chip chip;
    private List<Label> idSensingElementLabelList = new ArrayList<>();
    private List<JFXComboBox<String>> calibrationNameComboBoxList = new ArrayList<>();
    private List<JFXTextField> mParameterList = new ArrayList<>();
    private List<JFXTextField> nParameterList = new ArrayList<>();

    @FXML
    private void initialize() {
        families = FamilyDAO.getInstance().fetchAll();
        ObservableList<String> familyNames = FXCollections.observableArrayList();

        while (families.iterator().hasNext()) {
            familyNames.add(families.iterator().next().getName());
        }

        familyNameBox.setItems(familyNames);

    }

    public void setSensingElementOnChip(Chip chip) {
        this.chip = chip;

        idChipTextField.setText(chip.getId());
        familyNameBox.setValue(chip.getFamilyName());

        for (int i = 0; i < chip.getSensingElementWithCalibrations().size(); i++) {
            Label idSensingElementLabel = new Label(chip.getSensingElementWithCalibrations().get(i).getIdSensingElement());
            GridPane.setConstraints(idSensingElementLabel, 0, i);
            idSensingElementLabelList.add(idSensingElementLabel);


            for (int j = 0; j < chip.getSensingElementWithCalibrations().get(i).getCalibrationList().size(); j++) {
                ObservableList<String> calibrationName = FXCollections.observableArrayList();

                calibrationName.add(chip.getSensingElementWithCalibrations().get(i).getCalibrationList().get(j).getName());

                JFXComboBox<String> calibrationNameComboBox = new JFXComboBox<>();
                calibrationNameComboBox.setItems(calibrationName);

                GridPane.setConstraints(calibrationNameComboBox, 1, i);
                calibrationNameComboBoxList.add(calibrationNameComboBox);

            }

            JFXTextField mParameter = new JFXTextField();
            JFXTextField nParameter = new JFXTextField();

            GridPane.setConstraints(mParameter, 2, i);
            GridPane.setConstraints(nParameter, 3, i);

            mParameterList.add(mParameter);
            nParameterList.add(nParameter);

            final int j = i;

            calibrationNameComboBoxList.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    JFXComboBox<String> comboBox = (JFXComboBox) event.getSource();
                    String calibrationNameChoosed = comboBox.getValue();

                    for (int k = 0; k < chip.getSensingElementWithCalibrations().get(j).getCalibrationList().size(); k++) {
                        if (chip.getSensingElementWithCalibrations().get(j).getCalibrationList().get(k).getName().equals(calibrationNameChoosed)) {
                            mParameterList.get(j).setText(String.valueOf(chip.getSensingElementWithCalibrations().get(k).getCalibrationList().get(k).getM()));
                            nParameterList.get(j).setText(String.valueOf(chip.getSensingElementWithCalibrations().get(k).getCalibrationList().get(k).getN()));
                        }
                    }
                }
            });
        }

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
