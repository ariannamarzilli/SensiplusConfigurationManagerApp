package it.unicas.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import it.unicas.project.dao.SensingElementDAO;
import it.unicas.project.model.Family;
import it.unicas.project.model.Port;
import it.unicas.project.model.SensingElement;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import java.util.Iterator;
import java.util.List;


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
    private CheckComboBox<String> portBox;

    @FXML
    private Label port1Label;
    @FXML
    private Label port2Label;
    @FXML
    private Label port3Label;
    @FXML
    private Label port4Label;
    @FXML
    private Label port5Label;
    @FXML
    private Label port6Label;
    @FXML
    private Label port7Label;
    @FXML
    private Label port8Label;
    @FXML
    private Label port9Label;
    @FXML
    private Label port10Label;
    @FXML
    private Label port11Label;
    @FXML
    private Label port12Label;
    @FXML
    private Label portExt1Label;
    @FXML
    private Label portExt2Label;
    @FXML
    private Label portExt3Label;
    @FXML
    private Label portVoltageLabel;
    @FXML
    private Label portTemperatureLabel;
    @FXML
    private Label portLightLabel;
    @FXML
    private Label portDarkLabel;

    ObservableList<String> sensingElementName;

    private Stage dialogStage;
    Family family;


    @FXML
    private void initialize() {
        measureTechniqueBox.getItems().addAll("DIRECT", "EIS", "POT", "ENERGY_SPECTROSCOPY", "ULTRASOUND");
        portBox.getItems().addAll("PORT1", "PORT2", "PORT3", "PORT4", "PORT5", "PORT6", "PORT7",
                "PORT8", "PORT9", "PORT10", "PORT11", "PORT12", "PORT_EXT1", "PORT_EXT2", "PORT_EXT3", "PORT_TEMPERATURE",
                "PORT_VOLTAGE", "PORT_LIGHT", "PORT_DARK");

        SensingElementDAO sensingElementDAO = new SensingElementDAO();
        Iterable<SensingElement> sensingElements = sensingElementDAO.fetchAll();
        Iterator<SensingElement> iterator = sensingElements.iterator();
        sensingElementName = FXCollections.observableArrayList();
        while (iterator.hasNext()) {
            SensingElement sensingElement = iterator.next();
            sensingElementName.add(sensingElement.getName());
        }

        portBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                ObservableList<String> checkedItems = FXCollections.observableArrayList(portBox.getCheckModel().getCheckedItems());
                for (int i = 0; i < checkedItems.size(); i++) {
                    String portName = checkedItems.get(i);

                    handleDisableLabel(portName, "PORT1", port1Label);
                    handleDisableLabel(portName, "PORT2", port2Label);
                    handleDisableLabel(portName, "PORT3", port3Label);
                    handleDisableLabel(portName, "PORT4", port4Label);
                    handleDisableLabel(portName, "PORT5", port5Label);
                    handleDisableLabel(portName, "PORT6", port6Label);
                    handleDisableLabel(portName, "PORT7", port7Label);
                    handleDisableLabel(portName, "PORT8", port8Label);
                    handleDisableLabel(portName, "PORT9", port9Label);
                    handleDisableLabel(portName, "PORT10", port10Label);
                    handleDisableLabel(portName, "PORT11", port11Label);
                    handleDisableLabel(portName, "PORT12", port12Label);

                }
            }
        });
    }


    public void setFamily(Family family) {
        this.family = family;

        idTextField.setText(family.getId());
        nameTextField.setText(family.getName());
        hwVersionTextField.setText(family.getHwVersion());
        sysClockTextField.setText(family.getSysclock());
        oscTrimTextField.setText(family.getOsctrim());

        for (int i = 0; i < family.getMeasureType().size(); i++) {
            measureTechniqueBox.getCheckModel().check(family.getMeasureType().get(i));
        }

        if (family.getPorts().size() != 0) {
            for (int i = 0; i < family.getPorts().size(); i++) {
                portBox.getCheckModel().check(family.getPorts().get(i).getName());
                handlePortLabel(family);
            }
        } else {
            port1Label.setDisable(true);
            port2Label.setDisable(true);
            port3Label.setDisable(true);
            port4Label.setDisable(true);
            port5Label.setDisable(true);
            port6Label.setDisable(true);
            port7Label.setDisable(true);
            port8Label.setDisable(true);
            port9Label.setDisable(true);
            port10Label.setDisable(true);
            port11Label.setDisable(true);
            port12Label.setDisable(true);
            portExt1Label.setDisable(true);
            portExt2Label.setDisable(true);
            portExt3Label.setDisable(true);
            portVoltageLabel.setDisable(true);
            portTemperatureLabel.setDisable(true);
            portLightLabel.setDisable(true);
            portDarkLabel.setDisable(true);
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
        this.family.getPorts().clear();

        ObservableList<String> check = measureTechniqueBox.getCheckModel().getCheckedItems();
        for (int i = 0; i < check.size(); i++) {
            this.family.getMeasureType().add(check.get(i));
        }

        ObservableList<String> check1 = portBox.getCheckModel().getCheckedItems();
        for (int i = 0; i < check1.size(); i++) {
            this.family.getPorts().add(new Port(check.get(i)));
        }

        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        this.dialogStage.close();
    }

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    private void handlePortLabel(Family family) {
        for (int i = 0; i < family.getPorts().size(); i++) {
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT1", port1Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT2", port2Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT3", port3Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT4", port4Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT5", port5Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT6", port7Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT8", port8Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT9", port9Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT10", port10Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT11", port11Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT12", port12Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT_EXT1", portExt1Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT_EXT2", portExt2Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT_EXT3", portExt3Label);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT_VOLTAGE", portVoltageLabel);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT_TEMPERATURE", portTemperatureLabel);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT_LIGHT", portLightLabel);
            handleDisableLabel(family.getPorts().get(i).getName(), "PORT_DARK", portDarkLabel);
        }
    }

    private void handleDisableLabel(String port, String PORT, Label label) {
        if (port.equals(PORT)) {
            label.setDisable(false);
        } else {
            label.setDisable(true);
        }
    }

}
