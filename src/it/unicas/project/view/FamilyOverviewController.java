package it.unicas.project.view;

import com.jfoenix.controls.JFXListView;
import it.unicas.project.MainApp;
import it.unicas.project.dao.FamilyDAO;
import it.unicas.project.model.Family;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.Iterator;
import javafx.scene.input.MouseEvent;


public class FamilyOverviewController {

    @FXML
    private TableView<Family> familyTableView = new TableView<>();

    @FXML
    private TableColumn<Family, String> nameColumn;

    @FXML
    private TableColumn<Family, String> idColumn;

    @FXML
    private TableColumn<Family, String> hwVersionColumn;

    @FXML
    private TableColumn<Family, String> sysClockColumn;

    @FXML
    private TableColumn<Family, String> osctrimColumn;

    @FXML
    private JFXListView<String> portList;

    @FXML
    private JFXListView<String> measureTypeList;

    ObservableList<Family> familyData;
    private MainApp mainApp;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setFamilyData(ObservableList<Family> familyData) {
        this.familyData = familyData;
    }

    @FXML
    public void initialize() {
        FamilyDAO familyDAO = new FamilyDAO();
        Iterable<Family> families = familyDAO.fetchAll();
        Iterator<Family> iterator = families.iterator();

        ObservableList<Family> familyObservableList = FXCollections.observableArrayList();

        while(iterator.hasNext()) {
            Family family = iterator.next();
            family.checkNullField();
            familyObservableList.add(family);
        }

        this.setFamilyData(familyObservableList);

        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        hwVersionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHwVersion()));
        sysClockColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSysclock()));
        osctrimColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOsctrim()));

        familyTableView.setItems(familyObservableList);

    }

    @FXML
    public void handleNew() {
        Family tempFamily = new Family();
        Family oldFamily = new Family();
        mainApp.showFamilyEditDialog(tempFamily);
        if (!tempFamily.equals(oldFamily)) {
            FamilyDAO.getInstance().create(tempFamily);
            mainApp.getFamilyData().add(tempFamily);
        }
    }

    @FXML
    public void handleDelete() {
        int selectedIndex = familyTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Confirm delete");
            alert.setHeaderText("Are you sure you want to delete this item?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                Family family = familyTableView.getItems().get(selectedIndex);
                FamilyDAO.getInstance().delete(family);
                familyTableView.getItems().remove(selectedIndex);
            }
        }
    }


    @FXML
    public void handleClick(MouseEvent event) {

        if ((!familyData.isEmpty()) && familyTableView.getSelectionModel().getSelectedItem() != null) {

            Family family = familyTableView.getSelectionModel().getSelectedItem();

            if (event.getClickCount() == 1) {

                ObservableList<String> ports = FXCollections.observableArrayList();
                for (int i = 0; i < family.getPorts().size(); i++) {
                    ports.add(family.getPorts().get(i).getName());
                }
                ObservableList<String> measureTypes = FXCollections.observableArrayList();

                for (int i = 0; i < family.getMeasureType().size(); i++) {
                    measureTypes.add(family.getMeasureType().get(i));
                }

                portList.setItems(ports);
                measureTypeList.setItems(measureTypes);

            } else if (event.getClickCount() == 2) {
                Family oldFamily = new Family(family);
                mainApp.showFamilyEditDialog(family);
                if (!family.equals(oldFamily)) {
                    FamilyDAO.getInstance().update(family);
                    mainApp.getFamilyData().add(family);
                }
            }
        }
    }

}
