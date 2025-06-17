package com.example.annika;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class PillarController implements PillarInterface{

    private static HelloController instance;

    private void HelloController() {
    }

    public static synchronized HelloController getInstance() {
        if (instance == null) {
            instance = new HelloController();
        }
        return instance;
    }
    @Override
    public void GeneratePillar() {

        Rectangle pillar1 = null;
        Rectangle pillar2 = null;
        ImageView stickHero = null;
        Line stick = null;

        double pillar1LayoutX = pillar1.getLayoutX();
        double pillar1Width = pillar1.getWidth();
        double pillar2LayoutX=pillar2.getLayoutX();

        // Generate a random width for pillar1
        int randomWidth = (int) (Math.random() * (270 - 100 + 1) + 100);

        // Set the width and layout of pillar1
        pillar1.setWidth(randomWidth);
        pillar1.setLayoutX(pillar2LayoutX);

        // Set the width and layout of pillar2 using the saved values
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

    }

}
