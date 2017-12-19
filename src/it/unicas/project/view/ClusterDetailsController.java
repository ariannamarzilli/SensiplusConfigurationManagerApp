package it.unicas.project.view;
/*
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import it.unicas.project.dao.FamilyDAO;
import it.unicas.project.model.Cluster;
import it.unicas.project.model.Family;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.ListSelectionView;

import java.util.List;

public class ClusterDetailsController {

    @FXML private JFXTextField idTextField;

    @FXML private JFXComboBox familyComboBox;

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

            ObservableList<String> chipTargetList = FXCollections.observableArrayList();

            for (int i = 0; i < cluster.getChipWithCalibrations().size(); i++) {
                chipTargetList.add(cluster.getChipWithCalibrations().get(i).getChip().getId());
            }

            chipsListSelection.setTargetItems(chipTargetList);


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