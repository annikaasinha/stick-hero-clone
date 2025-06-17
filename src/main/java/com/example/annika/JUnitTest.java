package com.example.annika;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;

class ClassTest {

    @Test
    void testGeneratePillar() {
        StartController objUnderTest = new StartController(); // Replace YourClass with the actual class name

        Rectangle pillar1 = objUnderTest.getPillar1();
        Rectangle pillar2 = objUnderTest.getPillar2();
        ImageView stickHero = objUnderTest.getStickHero();
        Line stick = objUnderTest.getStick();

        double initialPillar1LayoutX = pillar1.getLayoutX();
        double initialPillar1Width = pillar1.getWidth();
        double initialPillar2LayoutX = pillar2.getLayoutX();
        double initialStickEndY = stick.getEndY();

        objUnderTest.generatePillar();

        // Check that pillar1's layout and width have changed
        assertNotEquals(initialPillar1LayoutX, pillar1.getLayoutX());
        assertNotEquals(initialPillar1Width, pillar1.getWidth());

        // Check that pillar2's layout and width have been set using the saved values
        assertEquals(initialPillar1LayoutX, pillar2.getLayoutX());
        assertEquals(initialPillar1Width, pillar2.getWidth());

        // Check that stickHero and stick properties have been set to expected values
        assertEquals(42, stickHero.getLayoutX());
        assertEquals(100.8333511352539, stick.getStartX());
        assertEquals(262, stick.getStartY());
        assertEquals(-2, stick.getLayoutX());
        assertEquals(43, stick.getLayoutY());

        // Check that the stick has been rotated by -90 degrees
        Rotate rotate = getRotateTransform(stick);
        assertEquals(-90, rotate.getAngle());

        // Check that stick's endY has been increased by 40
        assertEquals(initialStickEndY + 40, stick.getEndY());
    }

    private Rotate getRotateTransform(Line stick) {
        for (javafx.scene.transform.Transform transform : stick.getTransforms()) {
            if (transform instanceof Rotate) {
                return (Rotate) transform;
            }
        }
        return null;
    }
}

