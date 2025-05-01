package wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static final int WORD_LENGTH = 5;
    private static final int MAX_ATTEMPTS = 5;
    private static final String WORDS_FILE = "words.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("=== Welcome to CLI Wordle ===");
        System.out.println("Guess the 5-letter word. You have " + MAX_ATTEMPTS + " attempts.\n");

        Scanner scanner = new Scanner(System.in);

        do {
            String answer = chooseRandomWord();
            if (answer == null) {
                System.out.println("ERROR: Failed to load a valid word from the word list. Exiting.");
                return;
            }

            WordleGame game = new WordleGame(answer);
            int attempts = MAX_ATTEMPTS;

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
                    break;
                }

                attempts--;
            }

            if (attempts == 0) {
                System.out.println("Game over. The correct word was: " + answer);
            }

            System.out.print("Do you want to play again? (y/n): ");
        } while (scanner.nextLine().trim().equalsIgnoreCase("y"));
    }

    private static boolean isValidGuess(String guess) {
        return guess.length() == WORD_LENGTH && guess.chars().allMatch(Character::isLetter);
    }

    private static String chooseRandomWord() {
        try {
            List<String> words = Files.readAllLines(Paths.get(WORDS_FILE));
            List<String> validWords = new ArrayList<>();
            for (String word : words) {
                word = word.trim();
                if (word.length() == WORD_LENGTH && word.chars().allMatch(Character::isLetter)) {
                    validWords.add(word.toUpperCase());
                }
            }
            if (validWords.isEmpty()) return null;

            Random random = new Random();
            return validWords.get(random.nextInt(validWords.size()));
        } catch (IOException e) {
            return null;
        }
    }
}
