package it.unicas.project.view;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import it.unicas.project.MainApp;
import it.unicas.project.util.ConnectionFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class ConnectionController {

    @FXML private JFXToggleButton toggleButton;

    @FXML private Label urlLabel;

    @FXML private Label userLabel;

    @FXML private Label passwordLabel;

    @FXML private Label repeatPasswordLabel;

    @FXML private JFXTextField urlTextField;

    @FXML private JFXTextField userTextField;

    @FXML private JFXPasswordField passwordField;

    @FXML private JFXPasswordField repeatPasswordField;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void initialize() {
        if (!ConnectionFactory.getUseDefaultSettings()) {
            setDisableWidgets(true);
        } else {
            toggleButton.setDisableVisualFocus(false);
            setDisableWidgets(false);
            urlTextField.setText(ConnectionFactory.getUrl());
            userTextField.setText(ConnectionFactory.getUser());
            passwordField.setText(ConnectionFactory.getPass());
            repeatPasswordField.setText(ConnectionFactory.getPass());
        }
    }


    @FXML
    private void handleToggleButton() {

        if (toggleButton.isSelected()) {
            setDisableWidgets(false);
        } else {
            setDisableWidgets(true);
        }

    }

    @FXML
    private void handleSave() {

        if (toggleButton.isSelected()) {

            boolean emptyField = false;
            boolean equalPasswords = true;

            if (userTextField.getText().isEmpty() ||
                    urlTextField.getText().isEmpty() ||
                    passwordField.getText().isEmpty() ||
                    repeatPasswordField.getText().isEmpty()) {

                emptyField = true;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No empty space accepted");
                alert.setHeaderText("Insert all values");
                alert.showAndWait();
            }

            if (!passwordField.getText().equals(repeatPasswordField.getText())) {
                equalPasswords = false;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Wrong Password");
                alert.setHeaderText("The passwords inserted are not equals");
                alert.showAndWait();
            }

            if (!emptyField && equalPasswords) {
                ConnectionFactory.setUseDefaultSettings(false);
                ConnectionFactory.setUrl(urlTextField.getText());
                ConnectionFactory.setUser(userTextField.getText());
                ConnectionFactory.setPass(passwordField.getText());
            }

        }
    }

    private void setDisableWidgets(boolean disable) {
        urlLabel.setDisable(disable);
        userLabel.setDisable(disable);
        passwordLabel.setDisable(disable);
        repeatPasswordLabel.setDisable(disable);
        urlTextField.setDisable(disable);
        userTextField.setDisable(disable);
        passwordField.setDisable(disable);
        repeatPasswordField.setDisable(disable);
    }

}
