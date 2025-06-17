package com.example.annika;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class StickHeroController {
    @FXML
    private Button cherryButton;

    @FXML
    private Button startButton;

    private int highScore;

    @FXML

    private void handleStartButton(ActionEvent event) {

        String path = "C:\\Users\\manis\\IdeaProjects\\Annika\\src\\main\\resources\\com\\example\\annika\\neon-gaming-128925.mp3";

        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer m1 = new MediaPlayer(media);
        m1.setCycleCount(MediaPlayer.INDEFINITE);
        m1.play();
        try {
            // Load the startPage.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("startPage.fxml"));

            // Create a new controller and set it for the loader
            StartController controller = new StartController(); // Adjust the controller class accordingly
            loader.setController(controller);

            // Create a new scene
            Scene startScene = new Scene(loader.load());

            // Create a new stage for the start scene
            Stage startStage = new Stage();
            startStage.setScene(startScene);
            startStage.show();

            // Close the current stage (Stick Hero)
            Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            primaryStage.close();

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


    @FXML


    private void handleCherryButton(ActionEvent event) {
        try {
            // Load the cherryPage.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cherryPage.fxml"));
            CherryController controller = loader.getController();


            // Create a new scene
            Scene cherryScene = new Scene(loader.load());

            // Create a new stage for the cherry scene
            Stage cherryStage = new Stage();
            cherryStage.setScene(cherryScene);
            cherryStage.show();

            // Close the current stage (Stick Hero)
            Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            primaryStage.close();

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public Button getCherryButton() {
        return cherryButton;
    }

    public void setCherryButton(Button cherryButton) {
        this.cherryButton = cherryButton;
    }

    public Button getStartButton() {
        return startButton;
    }

    public void setStartButton(Button startButton) {
        this.startButton = startButton;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore=highScore;
    }
}






