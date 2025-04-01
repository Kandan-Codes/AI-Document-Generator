package org.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.AIRequest.DeepSeekAI;
import org.Document.DocumentGenerator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainPageController {
    @FXML private ImageView projectImage;
    @FXML private JFXTextArea askQuery;
    @FXML private JFXTextArea aiResponse;
    @FXML private JFXButton generateResponseButton;
    @FXML private JFXButton generateDocumentButton;
    @FXML private JFXRadioButton pdfOption;
    @FXML private JFXRadioButton docxOption;
    @FXML private JFXRadioButton bothOption;

    private List<JFXRadioButton> radioButtons;
    private DeepSeekAI aiHandler;
    private DocumentGenerator documentGenerator;
    private String aiResult;

    @FXML
    public void initialize() {
        aiHandler = new DeepSeekAI();
        documentGenerator = new DocumentGenerator();
        applyAnimations();
    }

    @FXML
    private void generateAIResponse() throws IOException {
        String userQuery = askQuery.getText();
        if(!userQuery.isEmpty()) {
            aiResult = DeepSeekAI.sendQueryToAI(userQuery);
            if(!aiResult.isEmpty()) {
                aiResponse.setText(aiResult);
            } else {
                showAlert("Error", "Failed to fetch AI response.");
            }
        } else {
            showAlert("Error", "Please enter a Query Before generate a Document.");
        }

    }

    @FXML
    private void generateDocument() throws IOException {
        if(aiResponse.getText().isEmpty()) {
            showAlert("Error", "Generate an AI response first.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Document");

        if(pdfOption.isSelected() || bothOption.isSelected()) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File pdfFile = fileChooser.showSaveDialog(null);
            if(pdfFile != null) {
                documentGenerator.generatePDF(aiResponse.getText().toString(), pdfFile);
            }
        }

        if(docxOption.isSelected() || bothOption.isSelected()) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DOCX Files", "*.docx"));
            File docxFile = fileChooser.showSaveDialog(null);
            if(docxFile != null) {
                documentGenerator.generateDOCX(aiResponse.getText().toString(), docxFile);
            }
        }

        showAlert("Success" ,"Document(s) created successfully!");
    }

    //creating alert window
    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void applyAnimations() {
        //Fade-in animation for the image
        FadeTransition fadeImage = new FadeTransition(Duration.seconds(1), projectImage);
        fadeImage.setFromValue(0);
        fadeImage.setToValue(1);
        fadeImage.play();

        //scale effect for buttons
        ScaleTransition scaleButtons = new ScaleTransition(Duration.seconds(1), generateResponseButton);
        scaleButtons.setFromX(0);
        scaleButtons.setFromY(0);
        scaleButtons.setToX(1);
        scaleButtons.setToX(1);
        scaleButtons.play();
    }

}
