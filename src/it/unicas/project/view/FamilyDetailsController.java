package it.unicas.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import it.unicas.project.dao.FamilyDAO;
import it.unicas.project.dao.SensingElementDAO;
import it.unicas.project.model.Analyte;
import it.unicas.project.model.SensingElement;
import it.unicas.project.model.Family;
import it.unicas.project.model.Port;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import java.util.ArrayList;
import java.util.List;

public class FamilyDetailsController {

    ObservableList<String> measureTechniqueList = FXCollections
            .observableArrayList("DIRECT", "EIS", "POT", "ENERGY_SPECTROSCOPY", "ULTRASOUND");

    ObservableList<String> portList = FXCollections
            .observableArrayList("PORT1", "PORT2", "PORT3", "PORT4", "PORT5", "PORT6", "PORT7",
            "PORT8", "PORT9", "PORT10", "PORT11", "PORT12", "PORT_EXT1", "PORT_EXT2", "PORT_EXT3", "PORT_TEMPERATURE",
                    "PORT_VOLTAGE", "PORT_LIGHT", "PORT_DARK");

    @FXML private JFXTextField idTextField;
    @FXML private JFXTextField nameTextField;
    @FXML private JFXTextField hwVersionTextField;
    @FXML private JFXTextField sysClockTextField;
    @FXML private JFXTextField oscTrimTextField;

    @FXML private CheckComboBox<String> measureTechniqueBox;
    @FXML private CheckComboBox<String> portBox;

    @FXML private Label port1Label;
    @FXML private Label port2Label;
    @FXML private Label port3Label;
    @FXML private Label port4Label;
    @FXML private Label port5Label;
    @FXML private Label port6Label;
    @FXML private Label port7Label;
    @FXML private Label port8Label;
    @FXML private Label port9Label;
    @FXML private Label port10Label;
    @FXML private Label port11Label;
    @FXML private Label port12Label;
    @FXML private Label portExt1Label;
    @FXML private Label portExt2Label;
    @FXML private Label portExt3Label;
    @FXML private Label portVoltageLabel;
    @FXML private Label portTemperatureLabel;
    @FXML private Label portLightLabel;
    @FXML private Label portDarkLabel;

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

    @FXML private CheckComboBox<String> analytePort1Box;
    @FXML private CheckComboBox<String> analytePort2Box;
    @FXML private CheckComboBox<String> analytePort3Box;
    @FXML private CheckComboBox<String> analytePort4Box;
    @FXML private CheckComboBox<String> analytePort5Box;
    @FXML private CheckComboBox<String> analytePort6Box;
    @FXML private CheckComboBox<String> analytePort7Box;
    @FXML private CheckComboBox<String> analytePort8Box;
    @FXML private CheckComboBox<String> analytePort9Box;
    @FXML private CheckComboBox<String> analytePort10Box;
    @FXML private CheckComboBox<String> analytePort11Box;
    @FXML private CheckComboBox<String> analytePort12Box;
    @FXML private CheckComboBox<String> analytePortLightBox;
    @FXML private CheckComboBox<String> analytePortDarkBox;
    @FXML private CheckComboBox<String> analytePortTemperatureBox;
    @FXML private CheckComboBox<String> analytePortVoltageBox;
    @FXML private CheckComboBox<String> analytePortExt1Box;
    @FXML private CheckComboBox<String> analytePortExt2Box;
    @FXML private CheckComboBox<String> analytePortExt3Box;

    ObservableList<String> sensingElementId;

    private Stage dialogStage;
    private boolean isAnUpdate;
    Family family;

    @FXML
    private void initialize() {

        measureTechniqueBox.getItems().addAll("DIRECT", "EIS", "POT", "ENERGY_SPECTROSCOPY", "ULTRASOUND");
        portBox.getItems().addAll("PORT1", "PORT2", "PORT3", "PORT4", "PORT5", "PORT6", "PORT7",
                "PORT8", "PORT9", "PORT10", "PORT11", "PORT12", "PORT_EXT1", "PORT_EXT2", "PORT_EXT3", "PORT_TEMPERATURE",
                "PORT_VOLTAGE", "PORT_LIGHT", "PORT_DARK");


        //devo usare una funzione che restituisce sensing element mai montati
        List<SensingElement> sensingElements = SensingElementDAO.getInstance().fetchAll();

        sensingElementId = FXCollections.observableArrayList();
        sensingElements.stream().forEach(sensingElement -> sensingElementId.add(sensingElement.getId()));

        //family.getPorts().stream().forEach(port -> sensingElementId.add(port.getIdSensingElement()));   //aggiungo i sensing element montati sulle porte della family selezionata

        List<Analyte> analytes = FamilyDAO.getInstance().fetchAllAnalyte();
        ObservableList<Analyte> analyteObservableList = FXCollections.observableList(analytes);

        sensingElementPort1Box.setItems(sensingElementId);
        sensingElementPort1Box.setItems(sensingElementId);
        sensingElementPort2Box.setItems(sensingElementId);
        sensingElementPort3Box.setItems(sensingElementId);
        sensingElementPort4Box.setItems(sensingElementId);
        sensingElementPort5Box.setItems(sensingElementId);
        sensingElementPort6Box.setItems(sensingElementId);
        sensingElementPort7Box.setItems(sensingElementId);
        sensingElementPort8Box.setItems(sensingElementId);
        sensingElementPort9Box.setItems(sensingElementId);
        sensingElementPort10Box.setItems(sensingElementId);
        sensingElementPort11Box.setItems(sensingElementId);
        sensingElementPort12Box.setItems(sensingElementId);
        sensingElementPortExt1Box.setItems(sensingElementId);
        sensingElementPortExt2Box.setItems(sensingElementId);
        sensingElementPortExt3Box.setItems(sensingElementId);
        sensingElementPortVoltageBox.setItems(sensingElementId);
        sensingElementPortTemperatureBox.setItems(sensingElementId);
        sensingElementPortLightBox.setItems(sensingElementId);
        sensingElementPortDarkBox.setItems(sensingElementId);

        for (int i = 0; i < analytes.size(); i++) {
            analytePort1Box.getItems().add(analyteObservableList.get(i).getName());
            analytePort2Box.getItems().add(analyteObservableList.get(i).getName());
            analytePort3Box.getItems().add(analyteObservableList.get(i).getName());
            analytePort4Box.getItems().add(analyteObservableList.get(i).getName());
            analytePort5Box.getItems().add(analyteObservableList.get(i).getName());
            analytePort6Box.getItems().add(analyteObservableList.get(i).getName());
            analytePort7Box.getItems().add(analyteObservableList.get(i).getName());
            analytePort8Box.getItems().add(analyteObservableList.get(i).getName());
            analytePort9Box.getItems().add(analyteObservableList.get(i).getName());
            analytePort10Box.getItems().add(analyteObservableList.get(i).getName());
            analytePort11Box.getItems().add(analyteObservableList.get(i).getName());
            analytePort12Box.getItems().add(analyteObservableList.get(i).getName());
            analytePortVoltageBox.getItems().add(analyteObservableList.get(i).getName());
            analytePortTemperatureBox.getItems().add(analyteObservableList.get(i).getName());
            analytePortLightBox.getItems().add(analyteObservableList.get(i).getName());
            analytePortDarkBox.getItems().add(analyteObservableList.get(i).getName());
            analytePortExt1Box.getItems().add(analyteObservableList.get(i).getName());
            analytePortExt2Box.getItems().add(analyteObservableList.get(i).getName());
            analytePortExt3Box.getItems().add(analyteObservableList.get(i).getName());
        }

        portBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                ObservableList<String> checkedItems = FXCollections.observableArrayList(portBox.getCheckModel().getCheckedItems());

                if (checkedItems.contains("PORT1")) {
                    port1Label.setDisable(false);
                    sensingElementPort1Box.setDisable(false);
                    analytePort1Box.setDisable(false);
                } else {
                    port1Label.setDisable(true);
                    sensingElementPort1Box.setDisable(true);
                    analytePort1Box.setDisable(true);
                }

                if (checkedItems.contains("PORT2")) {
                    port2Label.setDisable(false);
                    sensingElementPort2Box.setDisable(false);
                    analytePort2Box.setDisable(false);
                } else {
                    port2Label.setDisable(true);
                    sensingElementPort2Box.setDisable(true);
                    analytePort2Box.setDisable(true);
                }

                if (checkedItems.contains("PORT3")) {
                    port3Label.setDisable(false);
                    sensingElementPort3Box.setDisable(false);
                    analytePort3Box.setDisable(false);
                } else {
                    port3Label.setDisable(true);
                    sensingElementPort3Box.setDisable(true);
                    analytePort3Box.setDisable(true);
                }

                if (checkedItems.contains("PORT4")) {
                    port4Label.setDisable(false);
                    sensingElementPort4Box.setDisable(false);
                    analytePort4Box.setDisable(false);
                } else {
                    port4Label.setDisable(true);
                    sensingElementPort4Box.setDisable(true);
                    analytePort4Box.setDisable(true);
                }

                if (checkedItems.contains("PORT5")) {
                    port5Label.setDisable(false);
                    sensingElementPort5Box.setDisable(false);
                    analytePort5Box.setDisable(false);
                } else {
                    port5Label.setDisable(true);
                    sensingElementPort5Box.setDisable(true);
                    analytePort5Box.setDisable(true);
                }

                if (checkedItems.contains("PORT6")) {
                    port6Label.setDisable(false);
                    sensingElementPort6Box.setDisable(false);
                    analytePort6Box.setDisable(false);
                } else {
                    port6Label.setDisable(true);
                    sensingElementPort6Box.setDisable(true);
                    analytePort6Box.setDisable(true);
                }

                if (checkedItems.contains("PORT7")) {
                    port7Label.setDisable(false);
                    sensingElementPort7Box.setDisable(false);
                    analytePort7Box.setDisable(false);
                } else {
                    port7Label.setDisable(true);
                    sensingElementPort7Box.setDisable(true);
                    analytePort7Box.setDisable(true);
                }

                if (checkedItems.contains("PORT8")) {
                    port8Label.setDisable(false);
                    sensingElementPort8Box.setDisable(false);
                    analytePort8Box.setDisable(false);
                } else {
                    port8Label.setDisable(true);
                    sensingElementPort8Box.setDisable(true);
                    analytePort8Box.setDisable(true);
                }

                if (checkedItems.contains("PORT9")) {
                    port9Label.setDisable(false);
                    sensingElementPort9Box.setDisable(false);
                    analytePort9Box.setDisable(false);
                } else {
                    port9Label.setDisable(true);
                    sensingElementPort9Box.setDisable(true);
                    analytePort9Box.setDisable(true);
                }

                if (checkedItems.contains("PORT10")) {
                    port10Label.setDisable(false);
                    sensingElementPort10Box.setDisable(false);
                    analytePort10Box.setDisable(false);
                } else {
                    port10Label.setDisable(true);
                    sensingElementPort10Box.setDisable(true);
                    analytePort10Box.setDisable(true);
                }

                if (checkedItems.contains("PORT11")) {
                    port11Label.setDisable(false);
                    sensingElementPort11Box.setDisable(false);
                    analytePort11Box.setDisable(false);
                } else {
                    port11Label.setDisable(true);
                    sensingElementPort11Box.setDisable(true);
                    analytePort11Box.setDisable(true);
                }

                if (checkedItems.contains("PORT12")) {
                    port12Label.setDisable(false);
                    sensingElementPort12Box.setDisable(false);
                    analytePort12Box.setDisable(false);
                } else {
                    port12Label.setDisable(true);
                    sensingElementPort12Box.setDisable(true);
                    analytePort12Box.setDisable(true);
                }

                if (checkedItems.contains("PORT_EXT1")) {
                    portExt1Label.setDisable(false);
                    sensingElementPortExt1Box.setDisable(false);
                    analytePortExt1Box.setDisable(false);
                } else {
                    portExt1Label.setDisable(true);
                    sensingElementPortExt1Box.setDisable(true);
                    analytePortExt1Box.setDisable(true);
                }

                if (checkedItems.contains("PORT_EXT2")) {
                    portExt2Label.setDisable(false);
                    sensingElementPortExt2Box.setDisable(false);
                    analytePortExt2Box.setDisable(false);
                } else {
                    portExt2Label.setDisable(true);
                    sensingElementPortExt2Box.setDisable(true);
                    analytePortExt2Box.setDisable(true);
                }

                if (checkedItems.contains("PORT_EXT3")) {
                    portExt3Label.setDisable(false);
                    sensingElementPortExt3Box.setDisable(false);
                    analytePortExt3Box.setDisable(false);
                } else {
                    portExt3Label.setDisable(true);
                    sensingElementPortExt3Box.setDisable(true);
                    analytePortExt3Box.setDisable(true);
                }

                if (checkedItems.contains("PORT_VOLTAGE")) {
                    portVoltageLabel.setDisable(false);
                    sensingElementPortVoltageBox.setDisable(false);
                    analytePortVoltageBox.setDisable(false);
                } else {
                    portVoltageLabel.setDisable(true);
                    sensingElementPortVoltageBox.setDisable(true);
                    analytePortVoltageBox.setDisable(true);
                }

                if (checkedItems.contains("PORT_TEMPERATURE")) {
                    portTemperatureLabel.setDisable(false);
                    sensingElementPortTemperatureBox.setDisable(false);
                    analytePortTemperatureBox.setDisable(false);
                } else {
                    portTemperatureLabel.setDisable(true);
                    sensingElementPortTemperatureBox.setDisable(true);
                    analytePortTemperatureBox.setDisable(true);
                }

                if (checkedItems.contains("PORT_DARK")) {
                    portDarkLabel.setDisable(false);
                    sensingElementPortDarkBox.setDisable(false);
                    analytePortDarkBox.setDisable(false);
                } else {
                    portDarkLabel.setDisable(true);
                    sensingElementPortDarkBox.setDisable(true);
                    analytePortDarkBox.setDisable(true);
                }

                if (checkedItems.contains("PORT_LIGHT")) {
                    portLightLabel.setDisable(false);
                    sensingElementPortLightBox.setDisable(false);
                    analytePortLightBox.setDisable(false);
                } else {
                    portLightLabel.setDisable(true);
                    sensingElementPortLightBox.setDisable(true);
                    analytePortLightBox.setDisable(true);
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
            }

            handleWidgetsInScrollPane(family.getPorts(), "PORT1", port1Label, sensingElementPort1Box, analytePort1Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT2", port2Label, sensingElementPort2Box, analytePort2Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT3", port3Label, sensingElementPort3Box, analytePort3Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT4", port4Label, sensingElementPort4Box, analytePort4Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT5", port5Label, sensingElementPort5Box, analytePort5Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT6", port6Label, sensingElementPort6Box, analytePort6Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT7", port7Label, sensingElementPort7Box, analytePort7Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT8", port8Label, sensingElementPort8Box, analytePort8Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT9", port9Label, sensingElementPort9Box, analytePort9Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT10", port10Label, sensingElementPort10Box, analytePort10Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT11", port11Label, sensingElementPort11Box, analytePort11Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT12", port12Label, sensingElementPort12Box, analytePort12Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_EXT1", portExt1Label, sensingElementPortExt1Box, analytePortExt1Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_EXT2", portExt2Label, sensingElementPortExt2Box, analytePortExt2Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_EXT3", portExt3Label, sensingElementPortExt3Box, analytePortExt3Box);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_VOLTAGE", portVoltageLabel, sensingElementPortVoltageBox, analytePortVoltageBox);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_TEMPERATURE", portTemperatureLabel, sensingElementPortTemperatureBox, analytePortTemperatureBox);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_DARK", portDarkLabel, sensingElementPortDarkBox, analytePortDarkBox);
            handleWidgetsInScrollPane(family.getPorts(), "PORT_LIGHT", portLightLabel, sensingElementPortLightBox, analytePortLightBox);

        } else {
            setDisableAllWidgetsInScrollPane(true);
        }
    }

    @FXML
    private void handleSaveChanges() {

        List<Family> families = FamilyDAO.getInstance().fetchAll();
        boolean nameAlreadyUsed = false;

        for (int i = 0; i < families.size(); i++) {
            if (families.get(i).getName().equals(nameTextField.getText())) {
                nameAlreadyUsed = true;
            }
        }

        if (areChoosedSensingElementsCorrect()) {

            if (nameAlreadyUsed && !isAnUpdate) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Name already used for another Family");
                alert.showAndWait();

            } else {

                if (nameTextField.getText() == null) {
                    this.family.setName("");
                } else {
                    this.family.setName(nameTextField.getText());
                }

                if (idTextField.getText() == null) {
                    this.family.setId("");
                } else {
                    this.family.setId(idTextField.getText());
                }

                if (hwVersionTextField.getText() == null) {
                    this.family.setHwVersion("");
                } else {
                    this.family.setHwVersion(hwVersionTextField.getText());
                }

                if (oscTrimTextField.getText() == null) {
                    this.family.setOsctrim("");
                } else {
                    this.family.setOsctrim(oscTrimTextField.getText());
                }

                if (sysClockTextField.getText() == null) {
                    this.family.setSysclock("");
                } else {
                    this.family.setSysclock(sysClockTextField.getText());
                }

                this.family.getMeasureType().clear();
                if (measureTechniqueBox.getCheckModel().getCheckedItems() == null) {
                    this.family.setMeasureType(new ArrayList<>());
                } else {
                    measureTechniqueBox.getCheckModel().getCheckedItems().stream().forEach(measureTechnique -> this.family.getMeasureType().add(measureTechnique));
                }
                savePorts(this.family);
                dialogStage.close();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Sensing Element already selected for another port");
            alert.showAndWait();
        }

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
        analytePort1Box.setDisable(bool);
        analytePort2Box.setDisable(bool);
        analytePort3Box.setDisable(bool);
        analytePort4Box.setDisable(bool);
        analytePort5Box.setDisable(bool);
        analytePort6Box.setDisable(bool);
        analytePort7Box.setDisable(bool);
        analytePort8Box.setDisable(bool);
        analytePort9Box.setDisable(bool);
        analytePort10Box.setDisable(bool);
        analytePort11Box.setDisable(bool);
        analytePort12Box.setDisable(bool);
        analytePortVoltageBox.setDisable(bool);
        analytePortTemperatureBox.setDisable(bool);
        analytePortLightBox.setDisable(bool);
        analytePortDarkBox.setDisable(bool);
        analytePortExt1Box.setDisable(bool);
        analytePortExt2Box.setDisable(bool);
        analytePortExt3Box.setDisable(bool);
    }

    private void handleWidgetsInScrollPane(List<Port> ports, String PORT, Label label, JFXComboBox sensingElementBox, CheckComboBox analyteBox) {
        //Questo metodo gestisce tutti i widgts contenuti nello scrollPane in FamilyDetails. I parametri passati in
        // ingresso sono la lista di porte specificate in una family, il nome della porta da controllare e i tre widgets
        // da gestire.

        // Creo una lista che contiene esclusivamente i nomi delle porte
        List<String> portNamelist = new ArrayList<>();

        ports.stream().forEach(port -> portNamelist.add(port.getName()));

        // Il metodo contains utilizza equals per determinare se un oggetto è contenuto nella lista o meno. Per questo
        // motivo è preferibile utilizzare portnameList e non ports. Due porte sono uguali se è uguale sia il nome che
        // l'id del sensing element (che potrebbe non essere specificato)
        if (portNamelist.contains(PORT)) {
            label.setDisable(false);
            sensingElementBox.setDisable(false);
            analyteBox.setDisable(false);
            int index = portNamelist.indexOf(PORT);
            if (!ports.get(index).getIdSensingElement().isEmpty()) {
                sensingElementBox.setValue(ports.get(index).getIdSensingElement());
                for (int i = 0; i < ports.get(index).getAnalytes().size(); i++) {
                    analyteBox.getCheckModel().check(ports.get(index).getAnalytes().get(i));
                }
            }
        } else {
            label.setDisable(true);
            sensingElementBox.setDisable(true);
            analyteBox.setDisable(true);
        }
    }

    private void savePorts(Family family) {
        family.getPorts().clear();

        if (portBox.getCheckModel().getCheckedItems().contains("PORT1")) {
            if (sensingElementPort1Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePort1Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT1", sensingElementPort1Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT1", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT2")) {
            if (sensingElementPort2Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePort2Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT2", sensingElementPort2Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT2", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT3")) {
            if (sensingElementPort3Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePort3Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT3", sensingElementPort3Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT3", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT4")) {
            if (sensingElementPort4Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePort4Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT4", sensingElementPort4Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT4", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT5")) {
            if (sensingElementPort5Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePort5Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT5", sensingElementPort5Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT5", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT6")) {
            if (sensingElementPort6Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePort6Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT6", sensingElementPort6Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT6", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT7")) {
            if (sensingElementPort7Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePort7Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT7", sensingElementPort7Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT7", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT8")) {
            if (sensingElementPort8Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePort8Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT8", sensingElementPort8Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT8", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT9")) {
            if (sensingElementPort9Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePort9Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT9", sensingElementPort9Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT9", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT10")) {
            if (sensingElementPort10Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePort10Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT10", sensingElementPort10Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT10", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT11")) {
            if (sensingElementPort11Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePort11Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT11", sensingElementPort11Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT11", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT12")) {
            if (sensingElementPort12Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePort12Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT12", sensingElementPort12Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT12", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT_EXT1")) {
            if (sensingElementPortExt1Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePortExt1Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT_EXT1", sensingElementPortExt1Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT_EXT1", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT_EXT2")) {
            if (sensingElementPortExt2Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePortExt2Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT_EXT2", sensingElementPortExt2Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT_EXT2", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT_EXT3")) {
            if (sensingElementPortExt3Box.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePortExt3Box.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT_EXT3", sensingElementPortExt3Box.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT_EXT3", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT_TEMPERATURE")) {
            if (sensingElementPortTemperatureBox.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePortTemperatureBox.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT_TEMPERATURE", sensingElementPortTemperatureBox.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT_TEMPERATURE", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT_VOLTAGE")) {
            if (sensingElementPortVoltageBox.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePortVoltageBox.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT_VOLTAGE", sensingElementPortVoltageBox.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT_VOLTAGE", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT_DARK")) {
            if (sensingElementPortDarkBox.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePortDarkBox.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT_DARK", sensingElementPortDarkBox.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT_DARK", "", new ArrayList<>()));
            }
        }

        if (portBox.getCheckModel().getCheckedItems().contains("PORT_LIGHT")) {
            if (sensingElementPortLightBox.getValue() != null) {
                List<Analyte> analytes = new ArrayList<>();
                analytePortLightBox.getCheckModel().getCheckedItems().stream().forEach(analyte -> analytes.add(new Analyte(analyte)));
                family.getPorts().add(new Port("PORT_LIGHT", sensingElementPortLightBox.getValue(), analytes));
            } else {
                family.getPorts().add(new Port("PORT_LIGHT", "", new ArrayList<>()));
            }
        }

    }

    private boolean areChoosedSensingElementsCorrect() {

        ArrayList<String> choosedSensingElements = new ArrayList<>();

        if (!sensingElementPort1Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPort1Box.getValue())) {
                return false;
            } else {
                if (sensingElementPort1Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPort1Box.getValue());
                }
            }
        }
        if (!sensingElementPort2Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPort2Box.getValue())) {
                return false;
            } else {
                if (sensingElementPort2Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPort2Box.getValue());
                }
            }
        }
        if (!sensingElementPort3Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPort3Box.getValue())) {
                return false;
            } else {
                if (sensingElementPort3Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPort3Box.getValue());
                }
            }
        }
        if (!sensingElementPort4Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPort4Box.getValue())) {
                return false;
            } else {
                if (sensingElementPort4Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPort4Box.getValue());
                }
            }
        }
        if (!sensingElementPort5Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPort5Box.getValue())) {
                return false;
            } else {
                if (sensingElementPort5Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPort5Box.getValue());
                }
            }
        }
        if (!sensingElementPort6Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPort6Box.getValue())) {
                return false;
            } else {
                if (sensingElementPort6Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPort6Box.getValue());
                }
            }
        }
        if (!sensingElementPort7Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPort7Box.getValue())) {
                return false;
            } else {
                if (sensingElementPort7Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPort7Box.getValue());
                }
            }
        }
        if (!sensingElementPort8Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPort8Box.getValue())) {
                return false;
            } else {
                if (sensingElementPort8Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPort8Box.getValue());
                }
            }
        }
        if (!sensingElementPort9Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPort9Box.getValue())) {
                return false;
            } else {
                if (sensingElementPort9Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPort9Box.getValue());
                }
            }
        }
        if (!sensingElementPort10Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPort10Box.getValue())) {
                return false;
            } else {
                if (sensingElementPort10Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPort10Box.getValue());
                }
            }
        }
        if (!sensingElementPort11Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPort11Box.getValue())) {
                return false;
            } else {
                if (sensingElementPort11Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPort11Box.getValue());
                }
            }
        }
        if (!sensingElementPort12Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPort12Box.getValue())) {
                return false;
            } else {
                if (sensingElementPort12Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPort12Box.getValue());
                }
            }
        }
        if (!sensingElementPortExt1Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPortExt1Box.getValue())) {
                return false;
            } else {
                if (sensingElementPortExt1Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPortExt1Box.getValue());
                }
            }
        }
        if (!sensingElementPortExt2Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPortExt2Box.getValue())) {
                return false;
            } else {
                if (sensingElementPortExt2Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPortExt2Box.getValue());
                }
            }
        }
        if (!sensingElementPortExt3Box.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPortExt3Box.getValue())) {
                return false;
            } else {
                if (sensingElementPortExt3Box.getValue() != null) {
                    choosedSensingElements.add(sensingElementPortExt3Box.getValue());
                }
            }
        }
        if (!sensingElementPortVoltageBox.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPortVoltageBox.getValue())) {
                return false;
            } else {
                if (sensingElementPortVoltageBox.getValue() != null) {
                    choosedSensingElements.add(sensingElementPortVoltageBox.getValue());
                }
            }
        }
        if (!sensingElementPortTemperatureBox.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPortTemperatureBox.getValue())) {
                return false;
            } else {
                if (sensingElementPortTemperatureBox.getValue() != null) {
                    choosedSensingElements.add(sensingElementPortTemperatureBox.getValue());
                }
            }
        }
        if (!sensingElementPortLightBox.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPortLightBox.getValue())) {
                return false;
            } else {
                if (sensingElementPortLightBox.getValue() != null) {
                    choosedSensingElements.add(sensingElementPortLightBox.getValue());
                }
            }
        }
        if (!sensingElementPortDarkBox.isDisable()) {
            if (choosedSensingElements.size() != 0 && choosedSensingElements.contains(sensingElementPortDarkBox.getValue())) {
                return false;
            } else {
                if (sensingElementPortDarkBox.getValue() != null) {
                    choosedSensingElements.add(sensingElementPortDarkBox.getValue());
                }
            }
        }

        return true;
    }

    public void setAnUpdate(boolean isAnUpdate) {
        this.isAnUpdate = isAnUpdate;
        handleNameTextField(isAnUpdate);
    }

    private void handleNameTextField(boolean isAnUpdate) {
        nameTextField.setDisable(isAnUpdate);
    }

}
