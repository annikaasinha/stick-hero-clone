package com.example.annika;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class PauseButtonController {
    private int highScore;
    private int currentScore;
    @FXML
    private Button recentScoreView;

    @FXML
    private Button resume;

    @FXML
    private Button viewHighScore;

    @FXML
    public void goBackToGame(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        String path="C:\\Users\\manis\\IdeaProjects\\Annika\\src\\main\\resources\\com\\example\\annika\\neon-gaming-128925.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer m1 = new MediaPlayer(media);
        m1.setCycleCount(MediaPlayer.INDEFINITE);
        m1.play();
    }


    @FXML
    public void viewHighScore(ActionEvent event) {
        try {
            int currentHighScore = highScore;
            int updatedHighScore = HighScoreManager.updateAndGetHighScore(currentHighScore);
            viewHighScore.setText(String.valueOf(updatedHighScore));
        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            if (cause != null) {
                cause.printStackTrace();
            }
        }
    }

    @FXML
    public void viewPoints(ActionEvent event) {
        recentScoreView.setText(String.valueOf(currentScore));
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public Button getRecentScoreView() {
        return recentScoreView;
    }

    public void setRecentScoreView(Button recentScoreView) {
        this.recentScoreView = recentScoreView;
    }

    public Button getResume() {
        return resume;
    }

    public void setResume(Button resume) {
        this.resume = resume;
    }

    public Button getViewHighScore() {
        return viewHighScore;
    }

    public void setViewHighScore(Button viewHighScore) {
        this.viewHighScore = viewHighScore;
    }
}
