package it.unicas.project.view;
/*
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import it.unicas.project.dao.ChipDAO;
import it.unicas.project.dao.ClusterDAO;
import it.unicas.project.dao.FamilyDAO;
import it.unicas.project.model.Chip;
import it.unicas.project.model.Cluster;
import it.unicas.project.model.Family;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.ListSelectionView;

import java.util.ArrayList;
import java.util.List;

public class ClusterDetailsController {

    @FXML private JFXTextField idTextField;

    @FXML private JFXComboBox<String> familyComboBox;

    @FXML private ListSelectionView<String> chipsListSelection;

    @FXML private AnchorPane anchorPane;

    private GridPane gridPane;
    private Stage dialogStage;
    private Cluster cluster;
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

            ObservableList<String> chipSource = FXCollections.observableArrayList();

            for (int i = 0; i < chips.size(); i++) {

            }

        } else {


        }
    }

    @FXML
    public void handleSaveChanges() {

    }

    @FXML
    private void handleCancel() {
        this.dialogStage.close();
    }

    public void setAnUpdate(boolean anUpdate) {
        isAnUpdate = anUpdate;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

}
*/