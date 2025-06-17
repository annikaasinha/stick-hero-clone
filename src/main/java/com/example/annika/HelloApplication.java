package com.example.annika;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage)  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stickHero.fxml"));
        // Load the FXML file
        try {

            Parent root = loader.load();

            // Create the scene
            Scene scene = new Scene(root, 610, 400);

            // Set the stage properties
            primaryStage.setTitle("Stick Hero game");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e){
            System.out.println("some error");
            e.printStackTrace();
        }


// Set the StickHeroController as the controller for the stickHero.fxml
        StickHeroController controller = loader.getController();


    }

    public static void main(String[] args) {
        launch(args);
    }
}