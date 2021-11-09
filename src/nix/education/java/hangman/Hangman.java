package nix.education.java.hangman;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    StringBuilder helpWord = new StringBuilder();
    StringBuilder letterEntries = new StringBuilder();
    StringBuilder healthPoints = new StringBuilder();
    public static final int MAX_HEALTH = 8;

    private String randomizeArrayValue(String[] array) {
        int i = random.nextInt(array.length);
        return array[i];
    }

    private void guessWord(String guessedWord) {
        int health = MAX_HEALTH;
        fillHealthPoint(health);
        hideGuessedWord(guessedWord);
        while (health > 0) {
            System.out.println("Your health: " + healthPoints);
            System.out.print("Guess the letter " + helpWord + ":> ");
            char letter = scanner.next().charAt(0);
            if (!isLetterCorrect(letter)) continue;
            if (guessedWord.indexOf(letter) == -1) {
                System.out.println("That letter doesn't appear in the word");
                letterEntries.append(letter);
                healthPoints.deleteCharAt(health - 1);
                health--;
                continue;
            }
            fillHelpWord(letter, guessedWord);
            if (isWordGuessed(guessedWord)) {
                System.out.println("\n" + guessedWord);
                System.out.println("You survived");
                break;
            }
        }
        if (health == 0 && !helpWord.toString().equals(guessedWord)) {
            System.out.println("You lost");
        }
    }

    private boolean isLetterCorrect(char letter){
        if (!Character.isLowerCase(letter)) {
            System.out.println("Please enter a lowercase English letter.");
            return false;
        }
        if (helpWord.indexOf(String.valueOf(letter)) > -1 || letterEntries.indexOf(String.valueOf(letter)) > -1) {
            System.out.println("You've already guessed this letter");
            return false;
        }
        return true;
    }

    private boolean isWordGuessed(String guessedWord) {
        return helpWord.toString().equals(guessedWord);
    }

    private void fillHelpWord(char letter, String guessedWord) {
        for (int i = 0; i < guessedWord.length(); i++) {
            if (letter == guessedWord.charAt(i))
                helpWord.setCharAt(i, letter);
        }
    }

    private void fillHealthPoint(int health) {
        healthPoints.append("❤".repeat(Math.max(0, health)));
    }

    private void hideGuessedWord(String guessedWord) {
        helpWord.append("-".repeat(guessedWord.length()));
    }

    private void reset() {
        helpWord = new StringBuilder();
        letterEntries = new StringBuilder();
        healthPoints = new StringBuilder();
    }

    public void showMenu(String[] guessedWord) {
        System.out.println("HANGMAN\nThe game will be available soon");
        boolean hasIntentionToPlay = true;
        while (hasIntentionToPlay) {
            System.out.print("Type \"play\" to play the game, \"exit\" to quit:> ");
            String userChoice = scanner.nextLine();
            if (userChoice.toLowerCase(Locale.ROOT).equals("play")) {
                reset();
                guessWord(randomizeArrayValue(guessedWord));
                scanner.nextLine();
            } else if (userChoice.toLowerCase(Locale.ROOT).equals("exit")) {
                hasIntentionToPlay = false;
            }
        }
    }
}