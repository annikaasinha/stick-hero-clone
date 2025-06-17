package com.example.annika;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class StartController {

    @FXML
    private ImageView cherry;

    @FXML
    private ImageView backButton;

    @FXML
    private  Rectangle pillar1;


    @FXML
    private  Rectangle pillar2;

    @FXML
    private Line stick;

    @FXML
    private ImageView stickHero;
    @FXML
    private ImageView cherry1;
    private volatile boolean stopStickLengthIncrease = false;
    private volatile boolean isStickHeroUpsideDown=false;
    int pointsCount = 0;
    private double stickInitialX;
    private double stickFinalX;
    private double stickInitialY;
    private double stickFinalY;
    private int highScore;
    @FXML
    private ImageView pauseButton;
    public void setHighScore(int highScore){
        this.highScore=highScore;
    }

    //    @FXML
//    void onMouseReleased(MouseEvent event) {
//
//        isStickHeroUpsideDown=false;
//        stopStickLengthIncrease = true;
//
//
//        double pivotY = stick.getStartY();
//        double newY = stick.getStartX();
//
//        Rotate rotate = new Rotate(90, stick.getStartX(), pivotY);
//        stick.getTransforms().add(rotate);
//        moveStickHero();
//
//    }
    @FXML
    public void StopGame(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pauseButton.fxml"));
            Parent root = loader.load();

            // Retrieve the controller after loading the FXML
            PauseButtonController controller = loader.getController();
            controller.setHighScore(highScore);
            controller.setCurrentScore(pointsCount);


            // Create a new scene
            Scene pauseScene = new Scene(root);

            // Create a new stage
            Stage pauseStage = new Stage();

            // Set the scene for the new stage
            pauseStage.setScene(pauseScene);

            // Show the new stage
            pauseStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML

    public void flipCharacter(MouseEvent event) {

        if (!isStickHeroUpsideDown) {
            isStickHeroUpsideDown = true;
            double heroLayoutY = stickHero.getLayoutY();
            stickHero.setLayoutY(heroLayoutY + 59);
            stickHero.setRotate(180);
        } else {
            isStickHeroUpsideDown = false;
            stickHero.setRotate(0);
            stickHero.setLayoutY(343);
        }
    }


    @FXML
    public void flipHeroUp(KeyEvent event) {
        System.out.println("hero flipped back up");

    }




    public void moveStickHero() {
        // Get the local coordinates of the end of the stick
        Point2D endPointLocal = new Point2D(stick.getEndX(), stick.getEndY());

        // Transform the local coordinates to parent coordinates after rotation
        Point2D endPointParent = stick.localToParent(endPointLocal);

        // Set the position of stickHero to the transformed coordinates

        Timeline timeline = new Timeline();

        // Add a KeyFrame to the timeline for smooth movement
        KeyValue keyValueX = new KeyValue(stickHero.layoutXProperty(), endPointParent.getX() - 40);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(2000), keyValueX); // Adjust duration as needed

        // Add an event handler to check the position after the animation

        // Set the event handler to run when the timeline finishes
        timeline.setOnFinished(event -> handleGameOver());

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private void setCherryVisibility() {

        // Calculate the distance between pillar1 and pillar2
        double distanceBetweenPillars = pillar2.getLayoutX() - (pillar1.getLayoutX() + pillar1.getWidth());

        // Set a minimum distance required for cherry generation (you can adjust this value)
        double minDistanceForCherry = 50;

        // Check if the distance between pillars is above the minimum required
        if (distanceBetweenPillars > minDistanceForCherry) {
            // Generate a random x-coordinate within the available space
            double randomX = Math.random() * (distanceBetweenPillars - minDistanceForCherry) + pillar1.getLayoutX() + pillar1.getWidth();

            // Set the generated x-coordinate for the cherry
            cherry1.setLayoutX(randomX);

            // Make the cherry visible

            int randomNumber = new Random().nextInt(2);

            // Set visibility based on the random number
            boolean isVisible = (randomNumber == 1);
            cherry1.setVisible(isVisible);
        } else {
            // If the space is too narrow, don't generate the cherry
            cherry1.setVisible(false);
        }



    }

    private void handleGameOver() {
        System.out.println("Points count = " + pointsCount);

        double stick_length;
        double length_1;
        double length_2;

        if (pointsCount%2 == 0){

            stick_length = stick.getStartY() - stick.getEndY();
            length_1 = pillar2.getLayoutX() - stick.getStartX();
            length_2 = length_1 + pillar2.getWidth();

        } else {

            stick_length = stick.getStartY() - stick.getEndY();
            length_1 = pillar1.getLayoutX() - stick.getStartX();
            length_2 = length_1 + pillar1.getWidth();

        }


        System.out.println("Stick length = " + stick_length);
        System.out.println("length_1 = " + length_1);
        System.out.println("length_2 = " + length_2);


        if (stick_length > length_1 && stick_length < length_2){

            System.out.println("moved to the next pillar");
            pointsCount += 1;
            if(pointsCount>highScore){
                highScore=pointsCount;
            }
            generatePillar();
        }
        else {
            System.out.println("Game over");
            Stage currentStage = (Stage) stickHero.getScene().getWindow();
            currentStage.close();

            try {
                // Load the gameOver.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("gameOver.fxml"));
                Parent gameOver = loader.load();
                GameOverController controller = loader.getController();
                controller.setHighScoreCount(highScore);
                controller.setPointsCount(pointsCount);

                // Create a new stage and scene
                Stage gameOverStage = new Stage();
                Scene gameOverScene = new Scene(gameOver);


                gameOverStage.setScene(gameOverScene);
                gameOverStage.show();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void generatePillar() {
        double pillar1LayoutX = pillar1.getLayoutX();
        double pillar1Width = pillar1.getWidth();
        double pillar2LayoutX=pillar2.getLayoutX();

        int randomWidth = (int) (Math.random() * (270 - 100 + 1) + 100);

        pillar1.setWidth(randomWidth);
        pillar1.setLayoutX(pillar2LayoutX);

        pillar2.setWidth(pillar1Width);
        pillar2.setLayoutX(pillar1LayoutX);
        stickHero.setLayoutX(42);
        stick.setStartX(100.8333511352539);
        stick.setEndX(100.8333511352539);
        stick.setStartY(356);
        stick.setEndY(262);
        stick.setLayoutX(-2);
        stick.setLayoutY(43);
        double pivotY = stick.getStartY();
        double newX = stick.getStartX();
        Rotate rotate=new Rotate(-90, newX, pivotY);
        stick.getTransforms().add(rotate);
        stick.setEndY(stick.getEndY()+40);
        setCherryVisibility();

    }


    public void collectCherry() {
        // Your logic for collecting cherry goes here
        System.out.println("Cherry collected!");

    }




    private Timeline stickTimeline;

    @FXML
    public void stickLengthIncreaseDecrease(MouseEvent event) {
        stopStickLengthIncrease = false;
        //this.stickFinalX = stick.getEndX();
        //this.stickInitialX = stick.getStartX();
        //this.stickInitialY = stick.getEndX();
        this.stickFinalY = stick.getEndY();

        stopStickLengthIncrease = false; // Ensure it's set to false before starting the animation

        stickTimeline = new Timeline(
                new KeyFrame(Duration.millis(5), e -> {
                    if (!stopStickLengthIncrease) {
                        stick.setEndY(stick.getEndY() - 0.5);
                    }
                })
        );

        stickTimeline.setCycleCount(1500); // Number of cycles, adjust as needed
        stickTimeline.play();
    }

    @FXML
    public void onMouseReleased(MouseEvent event) {
        isStickHeroUpsideDown = false;
        stopStickLengthIncrease = true;

        double pivotY = stick.getStartY();
        double newY = stick.getStartX();

        Rotate rotate = new Rotate(90, stick.getStartX(), pivotY);
        stick.getTransforms().add(rotate);
        moveStickHero();
    }


    @FXML
    public void goToHomePage(MouseEvent event) {
        System.out.println("going to home page");
        try {
            // Load the cherryPage.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stickHero.fxml"));
            Parent homePage = loader.load();
            Scene homeScene = new Scene(homePage);
            Stage homeStage = new Stage();
            homeStage.setScene(homeScene);
            homeStage.show();
            // Close the current stage (Stick Hero)
            Stage primaryStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            primaryStage.close();
            StickHeroController controller=loader.getController();
            controller.setHighScore(highScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageView getCherry() {
        return cherry;
    }

    public void setCherry(ImageView cherry) {
        this.cherry = cherry;
    }

    public ImageView getBackButton() {
        return backButton;
    }

    public void setBackButton(ImageView backButton) {
        this.backButton = backButton;
    }

    public Rectangle getPillar1() {
        return pillar1;
    }

    public void setPillar1(Rectangle pillar1) {
        this.pillar1 = pillar1;
    }

    public Rectangle getPillar2() {
        return pillar2;
    }

    public void setPillar2(Rectangle pillar2) {
        this.pillar2 = pillar2;
    }

    public Line getStick() {
        return stick;
    }

    public void setStick(Line stick) {
        this.stick = stick;
    }

    public ImageView getStickHero() {
        return stickHero;
    }

    public void setStickHero(ImageView stickHero) {
        this.stickHero = stickHero;
    }

    public boolean isStopStickLengthIncrease() {
        return stopStickLengthIncrease;
    }

    public void setStopStickLengthIncrease(boolean stopStickLengthIncrease) {
        this.stopStickLengthIncrease = stopStickLengthIncrease;
    }

    public boolean isStickHeroUpsideDown() {
        return isStickHeroUpsideDown;
    }

    public void setStickHeroUpsideDown(boolean stickHeroUpsideDown) {
        isStickHeroUpsideDown = stickHeroUpsideDown;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public void setPointsCount(int pointsCount) {
        this.pointsCount = pointsCount;
    }

    public double getStickInitialX() {
        return stickInitialX;
    }

    public void setStickInitialX(double stickInitialX) {
        this.stickInitialX = stickInitialX;
    }

    public double getStickFinalX() {
        return stickFinalX;
    }

    public void setStickFinalX(double stickFinalX) {
        this.stickFinalX = stickFinalX;
    }

    public double getStickInitialY() {
        return stickInitialY;
    }

    public void setStickInitialY(double stickInitialY) {
        this.stickInitialY = stickInitialY;
    }

    public double getStickFinalY() {
        return stickFinalY;
    }

    public void setStickFinalY(double stickFinalY) {
        this.stickFinalY = stickFinalY;
    }

    public Timeline getStickTimeline() {
        return stickTimeline;
    }

    public void setStickTimeline(Timeline stickTimeline) {
        this.stickTimeline = stickTimeline;
    }

}
