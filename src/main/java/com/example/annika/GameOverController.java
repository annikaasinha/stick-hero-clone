package com.example.annika;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;


public class GameOverController {
    private int pointsCount;
    private int highScoreCount;
    @FXML
    private ImageView backButton;




    @FXML
    private Button currentScore;

    @FXML
    private Button highScore;
    public void setHighScoreCount(int highScoreCount){
        this.highScoreCount=highScoreCount;
    }

    public void setPointsCount(int pointsCount) {
        this.pointsCount = pointsCount;
    }

    @FXML
    void viewCurrentScore(ActionEvent event) {
        currentScore.setText(String.valueOf(pointsCount));

    }
    @FXML
    public void goToHomePage(MouseEvent event) {
        System.out.println("going to home page");
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

    @FXML
    public void viewHighScore(ActionEvent event) {
        try {
            int currentHighScore = highScoreCount;
            int updatedHighScore = HighScoreManager.updateAndGetHighScore(currentHighScore);
            highScore.setText(String.valueOf(updatedHighScore));
        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            if (cause != null) {
                cause.printStackTrace();
            }
        }

    }

    public int getPointsCount() {
        return pointsCount;
    }

    public int getHighScoreCount() {
        return highScoreCount;
    }

    public ImageView getBackButton() {
        return backButton;
    }

    public void setBackButton(ImageView backButton) {
        this.backButton = backButton;
    }

    public Button getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Button currentScore) {
        this.currentScore = currentScore;
    }

    public Button getHighScore() {
        return highScore;
    }

    public void setHighScore(Button highScore) {
        this.highScore = highScore;
    }
}
