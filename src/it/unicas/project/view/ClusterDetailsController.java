package it.unicas.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import it.unicas.project.MainApp;
import it.unicas.project.dao.ChipDAO;
import it.unicas.project.dao.ClusterDAO;
import it.unicas.project.dao.FamilyDAO;
import it.unicas.project.model.Chip;
import it.unicas.project.model.Cluster;
import it.unicas.project.model.Family;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.controlsfx.control.ListSelectionView;


import java.util.ArrayList;
import java.util.List;

public class ClusterDetailsController {

    @FXML private JFXTextField idTextField;

    @FXML private JFXComboBox<String> familyComboBox;

    @FXML private ListSelectionView<String> chipsListSelection;

    private Stage dialogStage;
    private Cluster cluster;
    private MainApp mainApp;
    private boolean isAnUpdate;

    @FXML
    private void initialize() {

        List<Family> families = FamilyDAO.getInstance().fetchAll();
        ObservableList<String> familyObservableList = FXCollections.observableArrayList();

        for (int i = 0; i < families.size(); i++) {
            if (!families.get(i).getPorts().get(0).getIdSensingElement().isEmpty()) {
                familyObservableList.add(families.get(i).getName());
            }
        }

        familyComboBox.setItems(familyObservableList);
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;

        idTextField.setText(cluster.getId());

        if (cluster.getChipWithCalibrations().size() != 0) {

            familyComboBox.setValue(cluster.getChipWithCalibrations().get(0).getChip().getFamilyName());

            ObservableList<String> chipTargetList = FXCollections.observableArrayList();

            for (int i = 0; i < cluster.getChipWithCalibrations().size(); i++) {
                chipTargetList.add(cluster.getChipWithCalibrations().get(i).getChip().getId());
            }

            chipsListSelection.setTargetItems(chipTargetList);

            List<Chip> chips = ChipDAO.getInstance().fetchAll();
            List<Chip> chipsAlreadyUsed = new ArrayList<>();
            List<Cluster> allClusters = ClusterDAO.getInstance().fetchAll();

            for (int i = 0; i < allClusters.size(); i++) {
                for (int j = 0; j < chips.size(); j++) {
                    if (allClusters.get(i).getChipWithCalibrations().contains(chips.get(j))) {
                        chipsAlreadyUsed.add(allClusters.get(i).getChipWithCalibrations().get(j).getChip());
                    }
                }
            }

            for (int i = 0; i < chipsAlreadyUsed.size(); i++) {
                if (chips.contains(chipsAlreadyUsed.get(i))) {
                    chips.remove(chipsAlreadyUsed.get(i));
                }
            }

            ObservableList<String> chipSourceList = FXCollections.observableArrayList();

            for (int i = 0; i < chips.size(); i++) {
                if (chips.get(i).getFamilyName().equals(familyComboBox.getValue())) {
                    chipSourceList.add(chips.get(i).getId());
                }
            }
            chipsListSelection.setSourceItems(chipSourceList);

        }
    }

    @FXML
    private void handleCancel() {
        this.dialogStage.close();
    }

    @FXML
    private void handleNext() {
        if (isInputValid()) {
            cluster.setId(idTextField.getText());

            // rimuovo chip appartenenti a famiglie differenti da quella scelta dall'utente
            for (int i = 0; i < cluster.getChipWithCalibrations().size(); i++) {
                if (!cluster.getChipWithCalibrations().get(i).getChip().getFamilyName().equals(familyComboBox.getValue())) {
                    cluster.getChipWithCalibrations().remove(i);
                }
            }

            //rimangono chip appartenenti alla famiglia scelta dall'utente. A questo punto ho:
            // 1. chip già presenti in cluster ma non contenuti in target -> da eliminare
            // 2. chip già presenti in cluster e contenuti in target -> non devo far nulla
            // 3. chip non presenti in cluster e contenuti in target -> da aggiungere

            // 3
            for (int i = 0; i < chipsListSelection.getTargetItems().size(); i++) {
                Chip chip = new Chip(familyComboBox.getValue(), chipsListSelection.getTargetItems().get(i));
                boolean isChipAlreadyPresent = false;

                for (int j = 0; j < cluster.getChipWithCalibrations().size(); j++) {
                    if (cluster.getChipWithCalibrations().get(i).getChip().equals(chip)) {
                        isChipAlreadyPresent = true;
                    }
                }

                if (!isChipAlreadyPresent) {
                    // ho bisogno di una funzione che dato l'id di un chip, mi restituisca un ChipWithCalibration
                    //cluster.getChipWithCalibrations().add();
                }
            }

            // 1
            for (int i = 0; i < cluster.getChipWithCalibrations().size(); i++) {

                Chip chip = cluster.getChipWithCalibrations().get(i).getChip();
                boolean found = false;

                for (int j = 0; j < chipsListSelection.getTargetItems().size(); j++) {
                    if (chipsListSelection.getTargetItems().get(j).equals(chip.getId())) {
                        found = true;
                    }
                }

                if (!found) {
                    cluster.getChipWithCalibrations().remove(chip);
                }
            }

            mainApp.showClusterCalibrationsEditDialog(cluster);
        }
    }

    @FXML
    private void handleFamilyComboBox() {

        chipsListSelection.getSourceItems().clear();
        chipsListSelection.getTargetItems().clear();

        List<Chip> chips = ChipDAO.getInstance().fetchAll();
        List<Chip> chipsAlreadyUsed = new ArrayList<>();
        List<Cluster> allClusters = ClusterDAO.getInstance().fetchAll();

        for (int i = 0; i < allClusters.size(); i++) {
            for (int j = 0; j < chips.size(); j++) {
                if (allClusters.get(i).getChipWithCalibrations().contains(chips.get(j))) {
                    chipsAlreadyUsed.add(allClusters.get(i).getChipWithCalibrations().get(j).getChip());
                }
            }
        }

        for (int i = 0; i < chipsAlreadyUsed.size(); i++) {
            if (chips.contains(chipsAlreadyUsed.get(i))) {
                chips.remove(chipsAlreadyUsed.get(i));
            }
        }

        ObservableList<String> chipSourceList = FXCollections.observableArrayList();

        for (int i = 0; i < chips.size(); i++) {
            if (chips.get(i).getFamilyName().equals(familyComboBox.getValue())) {
                chipSourceList.add(chips.get(i).getId());
            }
        }

        chipsListSelection.getSourceItems().addAll(chipSourceList);

    }

    public void setAnUpdate(boolean anUpdate) {
        isAnUpdate = anUpdate;
        idTextField.setDisable(anUpdate);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private boolean isInputValid() {

        String errorMessage = new String();

        if (idTextField.getText().isEmpty()) {
            errorMessage += "No id specified.\n";
        }

        if (familyComboBox.getValue() == null) {
            errorMessage += "No family specified.\n";
        }

        if (chipsListSelection.getTargetItems().size() == 0) {
            errorMessage += "No chips selected.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
