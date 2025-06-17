package com.example.annika;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class CherryController {
    public static int cherryCount=0;

    @FXML
    private Hyperlink backCherryButton;

    @FXML
    private ImageView back;
    @FXML
    private Button CherryCount;
    @FXML
    void cherryCountView(ActionEvent event) {
        CherryCount.setText(Integer.toString(cherryCount));
    }

    @FXML
    public void goToHomePage(ActionEvent event) {
        try {
            // Load the cherryPage.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stickHero.fxml"));
            Parent homePage = loader.load();

            // Create a new scene
            Scene homeScene = new Scene(homePage);

            Stage homeStage = new Stage();
            homeStage.setScene(homeScene);
            homeStage.show();
            // Close the current stage (Stick Hero)
            Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            primaryStage.close();



        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }


    }

    public static int getCherryCount() {
        return cherryCount;
    }

    public void setCherryCount(Button cherryCount) {
        CherryCount = cherryCount;
    }

    public static void setCherryCount(int cherryCount) {
        CherryController.cherryCount = cherryCount;
    }

    public Hyperlink getBackCherryButton() {
        return backCherryButton;
    }

    public void setBackCherryButton(Hyperlink backCherryButton) {
        this.backCherryButton = backCherryButton;
    }

    public ImageView getBack() {
        return back;
    }

    public void setBack(ImageView back) {
        this.back = back;
    }
}
