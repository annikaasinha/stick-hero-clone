package com.example.annika;

import java.io.*;

public class HighScoreManager {

    private static final String HIGH_SCORE_FILE = "highScore.txt";

    public static int readHighScore() {
        int highScore = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                highScore = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return highScore;
    }

    public static void writeHighScore(int newHighScore) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE))) {
            writer.write(String.valueOf(newHighScore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int updateAndGetHighScore(int currentHighScore) {
        int storedHighScore = readHighScore();
        int updatedHighScore = Math.max(currentHighScore, storedHighScore);
        writeHighScore(updatedHighScore);
        return updatedHighScore;
    }


}
