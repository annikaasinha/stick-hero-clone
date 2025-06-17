# Stick Hero Clone 

### Project Members

* **Annika Sinha** \[2022082]
* **Anish Jain** \[2022077]

---

###  How to Run the Project

To launch the game, execute the `HelloApplication.java` file located in the `src/main/java` directory.

---

###  Object-Oriented Concepts Used

This project applies key OOP principles to ensure maintainability and modularity:

* **Interfaces:**
  Used for generalization, e.g., `GameController`.

* **Abstract Classes:**
  Used for shared logic, e.g., `ObjectController`.

* **Encapsulation:**
  All classes encapsulate data and behavior, exposing only necessary functionality.

* **Singleton Pattern:**
  Implemented in `PillarController` to ensure a single instance manages pillar states.

---

###  Testing

* **JUnit Tests** are written to validate:

  * Pillar widths
  * Initial player and stick positions
  * Other core game parameters

---

###  Code Structure

* All attributes are marked `private` unless otherwise necessary.
* Only essential methods are declared `public`.

---

### 🕹️ Game Mechanics

1. **Stick Growth:**
   Stick extends while the mouse is pressed on the hero and stops upon release.

2. **Hero Flip:**
   The hero flips when you click on the background.

3. **Cherry Generation:**
   Cherries spawn randomly if there's enough space between pillars.

4. **Audio Integration:**
   Background music is embedded and plays during gameplay.

5. **High Score Tracking:**
   A `HighScoreManager` class handles reading and writing scores to a file.

6. **Scoring Logic:**
   Score increases based on the number of pillars the hero successfully crosses.

7. **Pause/Resume Feature:**
   A pause button saves the current game state and allows you to resume.

---

###  Technologies Used

* **Java**
* **JavaFX**
* **SceneBuilder**
* **JUnit**

---

###  Project Structure Overview

```
StickHeroClone/
│
├── src/
│   └── main/
│       └── java/
│           └── HelloApplication.java   # Main entry point
│           └── controllers/
│           └── models/
│           └── views/
│
├── resources/
│   └── audio/
│   └── images/
│
├── test/
│   └── java/
│       └── unit tests
│
└── README.md
```

---

