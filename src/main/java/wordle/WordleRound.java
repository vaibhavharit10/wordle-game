package wordle;

import java.util.Scanner;

public class WordleRound {
    private final String answer;
    private final WordleGame game;
    private final Scanner scanner;
    private int attempts;
    private static final int MAX_ATTEMPTS = 5;

    public WordleRound(String answer, Scanner scanner) {
        this.answer = answer;
        this.scanner = scanner;
        this.game = new WordleGame(answer);
        this.attempts = MAX_ATTEMPTS;
    }

    public void play() {
        while (attempts > 0) {
            System.out.printf("Attempts remaining: %d\nEnter your guess: ", attempts);
            String guess = scanner.nextLine().trim();

            if (!isValidGuess(guess)) {
                System.out.println("Invalid input. Please enter exactly 5 alphabetic characters.\n");
                continue;
            }

            Feedback feedback = game.checkGuess(guess);
            System.out.println("\nFeedback: " + feedback.coloredOutput() + "\n");

            if (guess.equalsIgnoreCase(answer)) {
                System.out.println("Congratulations! You guessed the word correctly.");
                return;
            }

            attempts--;
        }

        System.out.println("Game over. The correct word was: " + answer);
    }

    private boolean isValidGuess(String guess) {
        return guess.length() == 5 && guess.chars().allMatch(Character::isLetter);
    }
}
