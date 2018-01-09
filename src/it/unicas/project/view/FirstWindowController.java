package it.unicas.project.view;

import it.unicas.project.MainApp;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FirstWindowController {

    private MainApp mainApp;

    @FXML
    private ImageView imageView;

    @FXML
    private ImageView imageViewReverse;

    public void initialize() {

        ClassLoader loader = getClass().getClassLoader();
        imageView.setImage(new Image(loader.getResourceAsStream("images/image.jpg")));
        imageViewReverse.setImage(new Image(loader.getResourceAsStream("images/imagerev.jpg")));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handleGetStarted() {
        this.mainApp.showSecondWindow();
    }

}
