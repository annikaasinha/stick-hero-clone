package com.example.annika;

import javafx.scene.input.MouseEvent;

public interface GameController {
    void stickLengthIncreaseDecrease(MouseEvent event);
    void onMouseReleased(MouseEvent event);
    void handleGameOver();
    void generatePillar();

}
