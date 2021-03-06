package it.unicas.project.view;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import it.unicas.project.MainApp;
import it.unicas.project.dao.ChipDAO;
import it.unicas.project.dao.ClusterDAO;
import it.unicas.project.dao.FamilyDAO;
import it.unicas.project.model.*;
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
    private static boolean isAnUpdate;


    /**
     * This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        List<Family> families = FamilyDAO.getInstance().fetchAll();
        ObservableList<String> familyObservableList = FXCollections.observableArrayList();

        for (int i = 0; i < families.size(); i++) {
            if (families.get(i).getPorts().size() != 0) {
                if (!families.get(i).getPorts().get(0).getIdSensingElement().isEmpty()) {
                    familyObservableList.add(families.get(i).getName());
                }
            }
        }

        familyComboBox.setItems(familyObservableList);

    }

    /**
     * Sets the cluster to be edited in the dialog.
     * @param cluster to be edited
     */
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;


        idTextField.setText(cluster.getId());

        if (!cluster.getChipWithCalibrations().isEmpty()) {

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
                for (int j = 0; j < allClusters.get(i).getChipWithCalibrations().size(); j++) {
                    Chip chip = allClusters.get(i).getChipWithCalibrations().get(j).getChip();
                    chipsAlreadyUsed.add(chip);
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

        if (isAnUpdate) {

            for (int i = 0; i < chipsListSelection.getTargetItems().size(); i++) {
                if (chipsListSelection.getSourceItems().contains(chipsListSelection.getTargetItems().get(i))) {
                    chipsListSelection.getSourceItems().remove(chipsListSelection.getTargetItems().get(i));
                }
            }

        }
    }

    @FXML
    private void handleCancel() {

        this.dialogStage.close();
        mainApp.setCancelPressed(true);
    }

    /**
     * It is executed when the "Next" button is clicked, showing another edit dialog.
     */
    @FXML
    private void handleNext() {
        mainApp.setCancelPressed(false);

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
            List<ChipWithCalibration> chipWithCalibrationList = new ArrayList<>();

            for (int i = 0; i < chipsListSelection.getTargetItems().size(); i++) {
                Chip chip = new Chip(familyComboBox.getValue(), chipsListSelection.getTargetItems().get(i));
                boolean isChipAlreadyPresent = false;

                for (int j = 0; j < cluster.getChipWithCalibrations().size(); j++) {
                    if (cluster.getChipWithCalibrations().get(i).getChip().equals(chip)) {
                        isChipAlreadyPresent = true;
                    }
                }

                if (!isChipAlreadyPresent) {

                    List<Family> families = FamilyDAO.getInstance().fetchAll();

                    ChipWithCalibration chipWithCalibration = new ChipWithCalibration(chip);
                    for (int h = 0; h < families.size(); h++) {
                        if (families.get(h).getName().equals(chip.getFamilyName())) {
                            for (int k = 0; k < families.get(h).getPorts().size(); k++) {
                                SensingElementWithCalibration sensingElementWithCalibration = new SensingElementWithCalibration();
                                sensingElementWithCalibration.setIdSensingElement(families.get(h).getPorts().get(k).getIdSensingElement());
                                sensingElementWithCalibration.setPortName(families.get(h).getPorts().get(k).getName());
                                chipWithCalibration.getSensingElementWithCalibrations().add(sensingElementWithCalibration);
                            }
                        }
                    }

                    chipWithCalibrationList.add(chipWithCalibration);
                }
            }
            chipWithCalibrationList.stream().forEach(chipWithCalibration -> cluster.getChipWithCalibrations().add(chipWithCalibration));


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


    /**
     * When a family is selected, all available chips belonging to that family are
     * shown in the list "chipsListSelection"
     */
    @FXML
    private void handleFamilyComboBox() {
        /*
        Prima vengono svuotate sia la target che la source list del widget ListSelectionView
        Dati tutti i cluster già esistenti, vengono memorizzati nella lista "chipsAlreadyUsed" tutti i chip che sono stati selezionati
        Dalla lista "chips" contenente tutti i chip esistenti nel database vengono eliminati i chip contenuti in chipsAlreadyUsed.
        Viene creata una lista osservabile chipSourceList contenente tutti i chip in "chips" che verranno visualizzati nella lista sorgente del widget
         */
        chipsListSelection.getSourceItems().clear();
        chipsListSelection.getTargetItems().clear();

        List<Chip> chips = ChipDAO.getInstance().fetchAll();
        List<Chip> chipsAlreadyUsed = new ArrayList<>();
        List<Cluster> allClusters = ClusterDAO.getInstance().fetchAll();

        for (int i = 0; i < allClusters.size(); i++) {
            for (int j = 0; j < allClusters.get(i).getChipWithCalibrations().size(); j++) {
                Chip chip = allClusters.get(i).getChipWithCalibrations().get(j).getChip();
                chipsAlreadyUsed.add(chip);
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

    /**
     * If it's an update, it prevents from changing id.
     * @return
     */
    public void setAnUpdate(boolean anUpdate) {
        isAnUpdate = anUpdate;
        idTextField.setDisable(anUpdate);
    }

    public static boolean getIsAnUpdate() {
        return isAnUpdate;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Verify that all fields entered are correct (in this case they must be not null)
     * @return true if all the fields entered are correct, false otherwise.
     */
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
