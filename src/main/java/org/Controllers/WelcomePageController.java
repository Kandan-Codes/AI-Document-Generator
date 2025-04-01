package org.Controllers;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
public class WelcomePageController  {
    @FXML private Label titleLabel;
    @FXML private ImageView projectImage;
    @FXML private JFXButton startButton;
    @FXML private VBox mainContainer;

    @FXML
    public void initialize() {
        applyAnimations();
    }

    @FXML
    private void handleStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void applyAnimations() {
        Platform.runLater(() -> {
            //Fade in animation for title
            FadeTransition fadeTitle = new FadeTransition(Duration.seconds(1), titleLabel);
            fadeTitle.setFromValue(0);
            fadeTitle.setToValue(1);
            fadeTitle.play();

            //scale animation for image
            ScaleTransition scaleImage = new ScaleTransition(Duration.seconds(1), projectImage);
            scaleImage.setFromX(0);
            scaleImage.setFromY(0);
            scaleImage.setToX(1);
            scaleImage.setToY(1);
            scaleImage.play();

            //slide-in effect for main container
            TranslateTransition slideIn = new TranslateTransition(Duration.seconds(1), mainContainer);
            slideIn.setFromY(50);
            slideIn.setToY(0);
            slideIn.play();
        });
    }
}
