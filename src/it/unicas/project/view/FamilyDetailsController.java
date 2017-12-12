package it.unicas.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import it.unicas.project.dao.SensingElementDAO;
import it.unicas.project.model.SensingElement;
import it.unicas.project.model.Family;
import it.unicas.project.model.Port;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    @FXML
    private JFXComboBox<String> sensingElementPort1Box;
    @FXML
    private JFXComboBox<String> sensingElementPort2Box;
    @FXML
    private JFXComboBox<String> sensingElementPort3Box;
    @FXML
    private JFXComboBox<String> sensingElementPort4Box;
    @FXML
    private JFXComboBox<String> sensingElementPort5Box;
    @FXML
    private JFXComboBox<String> sensingElementPort6Box;
    @FXML
    private JFXComboBox<String> sensingElementPort7Box;
    @FXML
    private JFXComboBox<String> sensingElementPort8Box;
    @FXML
    private JFXComboBox<String> sensingElementPort9Box;
    @FXML
    private JFXComboBox<String> sensingElementPort10Box;
    @FXML
    private JFXComboBox<String> sensingElementPort11Box;
    @FXML
    private JFXComboBox<String> sensingElementPort12Box;
    @FXML
    private JFXComboBox<String> sensingElementPortExt1Box;
    @FXML
    private JFXComboBox<String> sensingElementPortExt2Box;
    @FXML
    private JFXComboBox<String> sensingElementPortExt3Box;
    @FXML
    private JFXComboBox<String> sensingElementPortVoltageBox;
    @FXML
    private JFXComboBox<String> sensingElementPortTemperatureBox;
    @FXML
    private JFXComboBox<String> sensingElementPortLightBox;
    @FXML
    private JFXComboBox<String> sensingElementPortDarkBox;

    ObservableList<String> sensingElementId;

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
        sensingElementId = FXCollections.observableArrayList();
        while (iterator.hasNext()) {
            SensingElement sensingElement = iterator.next();
            sensingElementId.add(sensingElement.getId());
        }

        for (int i = 0; i < sensingElementId.size(); i++) {
            sensingElementPort1Box.getItems().add(sensingElementId.get(i));
            sensingElementPort2Box.getItems().add(sensingElementId.get(i));
            sensingElementPort3Box.getItems().add(sensingElementId.get(i));
            sensingElementPort4Box.getItems().add(sensingElementId.get(i));
            sensingElementPort5Box.getItems().add(sensingElementId.get(i));
            sensingElementPort6Box.getItems().add(sensingElementId.get(i));
            sensingElementPort7Box.getItems().add(sensingElementId.get(i));
            sensingElementPort8Box.getItems().add(sensingElementId.get(i));
            sensingElementPort9Box.getItems().add(sensingElementId.get(i));
            sensingElementPort10Box.getItems().add(sensingElementId.get(i));
            sensingElementPort11Box.getItems().add(sensingElementId.get(i));
            sensingElementPort12Box.getItems().add(sensingElementId.get(i));
            sensingElementPortExt1Box.getItems().add(sensingElementId.get(i));
            sensingElementPortExt2Box.getItems().add(sensingElementId.get(i));
            sensingElementPortExt3Box.getItems().add(sensingElementId.get(i));
            sensingElementPortVoltageBox.getItems().add(sensingElementId.get(i));
            sensingElementPortTemperatureBox.getItems().add(sensingElementId.get(i));
            sensingElementPortLightBox.getItems().add(sensingElementId.get(i));
            sensingElementPortDarkBox.getItems().add(sensingElementId.get(i));
        }

        portBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                ObservableList<String> checkedItems = FXCollections.observableArrayList(portBox.getCheckModel().getCheckedItems());

                if (checkedItems.contains("PORT1")) {
                    port1Label.setDisable(false);
                    sensingElementPort1Box.setDisable(false);
                } else {
                    port1Label.setDisable(true);
                    sensingElementPort1Box.setDisable(true);
                }

                if (checkedItems.contains("PORT2")) {
                    port2Label.setDisable(false);
                    sensingElementPort2Box.setDisable(false);
                } else {
                    port2Label.setDisable(true);
                    sensingElementPort2Box.setDisable(true);
                }

                if (checkedItems.contains("PORT3")) {
                    port3Label.setDisable(false);
                    sensingElementPort3Box.setDisable(false);
                } else {
                    port3Label.setDisable(true);
                    sensingElementPort3Box.setDisable(true);
                }

                if (checkedItems.contains("PORT4")) {
                    port4Label.setDisable(false);
                    sensingElementPort4Box.setDisable(false);
                } else {
                    port4Label.setDisable(true);
                    sensingElementPort4Box.setDisable(true);
                }

                if (checkedItems.contains("PORT5")) {
                    port5Label.setDisable(false);
                    sensingElementPort5Box.setDisable(false);
                } else {
                    port5Label.setDisable(true);
                    sensingElementPort5Box.setDisable(true);
                }

                if (checkedItems.contains("PORT6")) {
                    port6Label.setDisable(false);
                    sensingElementPort6Box.setDisable(false);
                } else {
                    port6Label.setDisable(true);
                    sensingElementPort6Box.setDisable(true);
                }

                if (checkedItems.contains("PORT7")) {
                    port7Label.setDisable(false);
                    sensingElementPort7Box.setDisable(false);
                } else {
                    port7Label.setDisable(true);
                    sensingElementPort7Box.setDisable(true);
                }

                if (checkedItems.contains("PORT8")) {
                    port8Label.setDisable(false);
                    sensingElementPort8Box.setDisable(false);
                } else {
                    port8Label.setDisable(true);
                    sensingElementPort8Box.setDisable(true);
                }

                if (checkedItems.contains("PORT9")) {
                    port9Label.setDisable(false);
                    sensingElementPort9Box.setDisable(false);
                } else {
                    port9Label.setDisable(true);
                    sensingElementPort9Box.setDisable(true);
                }

                if (checkedItems.contains("PORT10")) {
                    port10Label.setDisable(false);
                    sensingElementPort10Box.setDisable(false);
                } else {
                    port10Label.setDisable(true);
                    sensingElementPort10Box.setDisable(true);
                }

                if (checkedItems.contains("PORT11")) {
                    port11Label.setDisable(false);
                    sensingElementPort11Box.setDisable(false);
                } else {
                    port11Label.setDisable(true);
                    sensingElementPort11Box.setDisable(true);
                }

                if (checkedItems.contains("PORT12")) {
                    port12Label.setDisable(false);
                    sensingElementPort12Box.setDisable(false);
                } else {
                    port12Label.setDisable(true);
                    sensingElementPort12Box.setDisable(true);
                }

                if (checkedItems.contains("PORT_EXT1")) {
                    portExt1Label.setDisable(false);
                    sensingElementPortExt1Box.setDisable(false);
                } else {
                    portExt1Label.setDisable(true);
                    sensingElementPortExt1Box.setDisable(true);
                }

                if (checkedItems.contains("PORT_EXT2")) {
                    portExt2Label.setDisable(false);
                    sensingElementPortExt2Box.setDisable(false);
                } else {
                    portExt2Label.setDisable(true);
                    sensingElementPortExt2Box.setDisable(true);
                }

                if (checkedItems.contains("PORT_EXT3")) {
                    portExt3Label.setDisable(false);
                    sensingElementPortExt3Box.setDisable(false);
                } else {
                    portExt3Label.setDisable(true);
                    sensingElementPortExt3Box.setDisable(true);
                }

                if (checkedItems.contains("PORT_VOLTAGE")) {
                    portVoltageLabel.setDisable(false);
                    sensingElementPortVoltageBox.setDisable(false);
                } else {
                    portVoltageLabel.setDisable(true);
                    sensingElementPortVoltageBox.setDisable(true);
                }

                if (checkedItems.contains("PORT_TEMPERATURE")) {
                    portTemperatureLabel.setDisable(false);
                    sensingElementPortTemperatureBox.setDisable(false);
                } else {
                    portTemperatureLabel.setDisable(true);
                    sensingElementPortTemperatureBox.setDisable(true);
                }

                if (checkedItems.contains("PORT_DARK")) {
                    portDarkLabel.setDisable(false);
                    sensingElementPortDarkBox.setDisable(false);
                } else {
                    portDarkLabel.setDisable(true);
                    sensingElementPortDarkBox.setDisable(true);
                }

                if (checkedItems.contains("PORT_LIGHT")) {
                    portLightLabel.setDisable(false);
                    sensingElementPortLightBox.setDisable(false);
                } else {
                    portLightLabel.setDisable(true);
                    sensingElementPortLightBox.setDisable(true);
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
                portBox.getCheckModel().check(family.getPorts().get(i).getIdSensingElement());
            }

            handleWidgetsInScrollPane(family.getPorts(), "PORT1", port1Label, sensingElementPort1Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT2", port2Label, sensingElementPort2Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT3", port3Label, sensingElementPort3Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT4", port4Label, sensingElementPort4Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT5", port5Label, sensingElementPort5Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT6", port6Label, sensingElementPort6Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT7", port7Label, sensingElementPort7Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT8", port8Label, sensingElementPort8Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT9", port9Label, sensingElementPort9Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT10", port10Label, sensingElementPort10Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT11", port11Label, sensingElementPort11Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT12", port12Label, sensingElementPort12Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_EXT1", portExt1Label, sensingElementPortExt1Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_EXT2", portExt2Label, sensingElementPortExt2Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_EXT3", portExt3Label, sensingElementPortExt3Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_VOLTAGE", portVoltageLabel, sensingElementPortVoltageBox);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_TEMPERATURE", portTemperatureLabel, sensingElementPortTemperatureBox);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_DARK", portDarkLabel, sensingElementPortDarkBox);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_LIGHT", portLightLabel, sensingElementPortLightBox);

        } else {
            setDisableAllWidgetsInScrollPane(true);
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

        ObservableList<String> check = measureTechniqueBox.getCheckModel().getCheckedItems();
        for (int i = 0; i < check.size(); i++) {
            this.family.getMeasureType().add(check.get(i));
        }

        savePorts(family);

        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        this.dialogStage.close();
    }

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    private void setDisableAllWidgetsInScrollPane(Boolean bool) {
        port1Label.setDisable(bool);
        port2Label.setDisable(bool);
        port3Label.setDisable(bool);
        port4Label.setDisable(bool);
        port5Label.setDisable(bool);
        port6Label.setDisable(bool);
        port7Label.setDisable(bool);
        port8Label.setDisable(bool);
        port9Label.setDisable(bool);
        port10Label.setDisable(bool);
        port11Label.setDisable(bool);
        port12Label.setDisable(bool);
        portExt1Label.setDisable(bool);
        portExt2Label.setDisable(bool);
        portExt3Label.setDisable(bool);
        portVoltageLabel.setDisable(bool);
        portTemperatureLabel.setDisable(bool);
        portLightLabel.setDisable(bool);
        portDarkLabel.setDisable(bool);
        sensingElementPort1Box.setDisable(bool);
        sensingElementPort2Box.setDisable(bool);
        sensingElementPort3Box.setDisable(bool);
        sensingElementPort4Box.setDisable(bool);
        sensingElementPort5Box.setDisable(bool);
        sensingElementPort6Box.setDisable(bool);
        sensingElementPort7Box.setDisable(bool);
        sensingElementPort8Box.setDisable(bool);
        sensingElementPort9Box.setDisable(bool);
        sensingElementPort10Box.setDisable(bool);
        sensingElementPort11Box.setDisable(bool);
        sensingElementPort12Box.setDisable(bool);
        sensingElementPortExt1Box.setDisable(bool);
        sensingElementPortExt2Box.setDisable(bool);
        sensingElementPortExt3Box.setDisable(bool);
        sensingElementPortVoltageBox.setDisable(bool);
        sensingElementPortTemperatureBox.setDisable(bool);
        sensingElementPortLightBox.setDisable(bool);
        sensingElementPortDarkBox.setDisable(bool);
    }

    private void handleWidgetsInScrollPane(List<Port> ports, String PORT, Label label, JFXComboBox sensingElementBox) {

        if (ports.contains(new Port(PORT))) {
            label.setDisable(false);
            sensingElementBox.setDisable(false);
            int index = ports.indexOf(new Port(PORT));
            if (!ports.get(index).getIdSensingElement().isEmpty()) {
                sensingElementBox.setValue(ports.get(index).getIdSensingElement());
            }
        } else {
            label.setDisable(true);
            sensingElementBox.setDisable(true);
        }
    }

    private void savePorts(Family family) {
        ObservableList<String> check = portBox.getCheckModel().getCheckedItems();
        family.getPorts().clear();

        if (check.contains("PORT1")) {
            family.getPorts().add(new Port("PORT1", sensingElementPort1Box.getValue()));
        }
        if (check.contains("PORT2")) {
            family.getPorts().add(new Port("PORT2", sensingElementPort2Box.getValue()));
        }
        if (check.contains("PORT3")) {
            family.getPorts().add(new Port("PORT3", sensingElementPort3Box.getValue()));
        }
        if (check.contains("PORT4")) {
            family.getPorts().add(new Port("PORT4", sensingElementPort4Box.getValue()));
        }
        if (check.contains("PORT5")) {
            family.getPorts().add(new Port("PORT5", sensingElementPort5Box.getValue()));
        }
        if (check.contains("PORT6")) {
            family.getPorts().add(new Port("PORT6", sensingElementPort6Box.getValue()));
        }
        if (check.contains("PORT7")) {
            family.getPorts().add(new Port("PORT7", sensingElementPort7Box.getValue()));
        }
        if (check.contains("PORT8")) {
            family.getPorts().add(new Port("PORT8", sensingElementPort8Box.getValue()));
        }
        if (check.contains("PORT9")) {
            family.getPorts().add(new Port("PORT9", sensingElementPort9Box.getValue()));
        }
        if (check.contains("PORT10")) {
            family.getPorts().add(new Port("PORT10", sensingElementPort10Box.getValue()));
        }
        if (check.contains("PORT11")) {
            family.getPorts().add(new Port("PORT11", sensingElementPort11Box.getValue()));
        }
        if (check.contains("PORT12")) {
            family.getPorts().add(new Port("PORT12", sensingElementPort12Box.getValue()));
        }
        if (check.contains("PORT_EXT1")) {
            family.getPorts().add(new Port("PORT_EXT1", sensingElementPortExt1Box.getValue()));
        }
        if (check.contains("PORT_EXT2")) {
            family.getPorts().add(new Port("PORT_EXT2", sensingElementPortExt2Box.getValue()));
        }
        if (check.contains("PORT_EXT3")) {
            family.getPorts().add(new Port("PORT_EXT3", sensingElementPortExt3Box.getValue()));
        }
        if (check.contains("PORT_TEMPERATURE")) {
            family.getPorts().add(new Port("PORT_TEMPERATURE", sensingElementPortTemperatureBox.getValue()));
        }
        if (check.contains("PORT_VOLTAGE")) {
            family.getPorts().add(new Port("PORT_VOLTAGE", sensingElementPortVoltageBox.getValue()));
        }
        if (check.contains("PORT_DARK")) {
            family.getPorts().add(new Port("PORT_DARK", sensingElementPortDarkBox.getValue()));
        }
        if (check.contains("PORT_LIGHT")) {
            family.getPorts().add(new Port("PORT_LIGHT", sensingElementPortLightBox.getValue()));
        }
    }

}
