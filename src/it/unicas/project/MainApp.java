package it.unicas.project;

import it.unicas.project.model.Family;
import it.unicas.project.model.SensingElement;
import it.unicas.project.view.FirstWindowController;
import it.unicas.project.view.SecondWindowController;
import it.unicas.project.view.SensingElementDetailsController;
import it.unicas.project.view.SensingElementTableController;
import it.unicas.project.view.FamilyTableController;
import it.unicas.project.view.FamilyDetailsController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootWindow;
    private ObservableList<SensingElement> sensingElementData = FXCollections.observableArrayList();
    private ObservableList<Family> familyData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sensiplus Configuration Manager");
        initFirstWindow();
    }

    public void initFirstWindow() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FirstWindow.fxml"));
            AnchorPane firstWindow = (AnchorPane) loader.load();
            Scene scene = new Scene(firstWindow);
            primaryStage.setScene(scene);
            primaryStage.show();

            FirstWindowController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showSecondWindow() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SecondWindow.fxml"));
            rootWindow = (BorderPane) loader.load();
            Scene scene = new Scene(rootWindow);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.show();

            SecondWindowController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSensingElementTable() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SensingElementTable.fxml"));
            Node sensingElementTable = (Node) loader.load();
            rootWindow.setCenter(sensingElementTable);
            SensingElementTableController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showFamilyTable() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FamilyTable.fxml"));
            Node sensingElementTable = (Node) loader.load();
            rootWindow.setCenter(sensingElementTable);
            FamilyTableController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChipTable() {

    }

    public void showClusterTable() {

    }

    public void showConfigurationTable() {

    }

    public void showSensingElementEditDialog(SensingElement sensingElement) {
        try{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((MainApp.class.getResource("view/SensingElementDetails.fxml")));
            AnchorPane page = (AnchorPane) loader.load();

            //dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Show Sensing Element");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //set sensing element into the controller
            SensingElementDetailsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSensingElement(sensingElement);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showFamilyEditDialog(Family family) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FamilyDetails.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Show Family");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            //set sensing element into the controller
            FamilyDetailsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setFamily(family);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<SensingElement> getSensingElementData() {
        return sensingElementData;
    }

    public ObservableList<Family> getFamilyData() {
        return familyData;
    }

    public static void main(String[] args) {
        launch(args);
    }

}



