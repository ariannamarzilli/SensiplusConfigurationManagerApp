package it.unicas.project;

import it.unicas.project.model.Chip;
import it.unicas.project.model.Family;
import it.unicas.project.model.SensingElement;
import it.unicas.project.view.*;
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
    private ObservableList<Chip> chipData = FXCollections.observableArrayList();

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

    public void showSensingElementOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SensingElementOverview.fxml"));
            Node sensingElementOverview = (Node) loader.load();
            rootWindow.setCenter(sensingElementOverview);
            SensingElementOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showFamilyOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FamilyOverview.fxml"));
            Node familyOverview = (Node) loader.load();
            rootWindow.setCenter(familyOverview);
            FamilyOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChipOverview() {
        /*
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ChipOverview.fxml"));
            Node chipOverview = (Node) loader.load();
            rootWindow.setCenter(chipOverview);
            ChipOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    public void showClusterOverview() {}

    public void showConfigurationOverview() {

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

            FamilyDetailsController controller = loader.getController();
            controller.setFamily(family);
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChipEditDialog(Chip chip) {
        /*
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ChipDetails.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Show Chip");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ChipDetailsController controller = loader.getController();
            controller.setChip(chip);
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
        */
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

    public ObservableList<Chip> getChipData() {
        return chipData;
    }

    public static void main(String[] args) {
        launch(args);
    }

}



