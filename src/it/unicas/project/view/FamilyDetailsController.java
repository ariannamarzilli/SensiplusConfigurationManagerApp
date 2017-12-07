package it.unicas.project.view;

import com.jfoenix.controls.JFXTextField;
import it.unicas.project.model.Family;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;


import java.util.Iterator;

public class FamilyDetailsController {

    ObservableList<String> measureTechniqueList = FXCollections
            .observableArrayList("DIRECT", "EIS", "POT", "ENERGY_SPECTROSCOPY", "ULTRASOUND");

    ObservableList<String> portList = FXCollections
            .observableArrayList("PORT1", "PORT2", "PORT3", "PORT4", "PORT5", "PORT6", "PORT7",
            "PORT8", "PORT9", "PORT10", "PORT11", "PORT12", "PORT_EXT1", "PORT_EXT2", "PORT_EXT3", "PORT_TEMPERATURE",
                    "PORT_VOLTAGE", "PORT_LIGHT", "PORT_DARK");

    @FXML
    private JFXTextField idTextField;

    @FXML
    private JFXTextField nameTextField;

    @FXML
    private JFXTextField hwVersionTextField;

    @FXML
    private JFXTextField sysClockTextField;

    @FXML
    private JFXTextField oscTrimTextField;

    @FXML
    private CheckComboBox<String> measureTechniqueBox;

    @FXML
    private CheckComboBox<String> portsBox;

    private Stage dialogStage;
    Family family;

    @FXML
    private void initialize() {
        measureTechniqueBox.getItems().addAll("DIRECT", "EIS", "POT", "ENERGY_SPECTROSCOPY", "ULTRASOUND");
        portsBox.getItems().addAll("PORT1", "PORT2", "PORT3", "PORT4", "PORT5", "PORT6", "PORT7",
                "PORT8", "PORT9", "PORT10", "PORT11", "PORT12", "PORT_EXT1", "PORT_EXT2", "PORT_EXT3", "PORT_TEMPERATURE",
                "PORT_VOLTAGE", "PORT_LIGHT", "PORT_DARK");
    }


    public void setFamily(Family family) {
        this.family = family;

        idTextField.setText(family.getId());
        nameTextField.setText(family.getName());
        hwVersionTextField.setText(family.getHwVersion());
        sysClockTextField.setText(family.getSysclock());
        oscTrimTextField.setText(family.getOsctrim());

        Iterator<String> iterator = family.getMeasureType().iterator();
        while (iterator.hasNext()) {
            measureTechniqueBox.getCheckModel().check(iterator.next());
        }

        Iterator<String> iterator1 = family.getPortName().iterator();
        while (iterator1.hasNext()) {
            portsBox.getCheckModel().check(iterator1.next());
        }
    }

    @FXML
    private void handleSaveChanges() {
        this.family.setName(nameTextField.getText());
        this.family.setId(idTextField.getText());
        this.family.setHwVersion(hwVersionTextField.getText());
        this.family.setOsctrim(oscTrimTextField.getText());
        this.family.setSysclock(sysClockTextField.getText());

        this.family.getMeasureType().clear();
        this.family.getPortName().clear();

        ObservableList<String> check = measureTechniqueBox.getCheckModel().getCheckedItems();
        for (int i = 0; i < check.size(); i++) {
            this.family.getMeasureType().add(check.get(i));
        }

        ObservableList<String> check1 = portsBox.getCheckModel().getCheckedItems();
        for (int i = 0; i < check1.size(); i++) {
            this.family.getPortName().add(check1.get(i));
        }
        dialogStage.close();
    }

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

}
