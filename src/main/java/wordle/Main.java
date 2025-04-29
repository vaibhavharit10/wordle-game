package wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("*** Welcome to CLI Wordle! ***");
        System.out.println("Guess the 5-letter word. You have 5 attempts.\n");

        List<String> words = Files.readAllLines(Paths.get("words.txt"));
        if (words.isEmpty()) {
            System.out.println("Word list is empty! Exiting.");
            return;
        }

        Random random = new Random();
        String answer = words.get(random.nextInt(words.size())).trim().toUpperCase();

        WordleGame game = new WordleGame(answer);
        Scanner scanner = new Scanner(System.in);

        int attempts = 5;
        while (attempts > 0) {
            System.out.printf("You have %d attempts left. Enter your guess: ", attempts);
            String guess = scanner.nextLine().trim();

            if (guess.length() != 5 || !guess.chars().allMatch(Character::isLetter)) {
                System.out.println("XXX Guess must be exactly 5 letters. XXX\n");
                continue;
            }

            Feedback feedback = game.checkGuess(guess);
            System.out.println(feedback.coloredOutput() + "\n");

            if (guess.equalsIgnoreCase(answer)) {
                System.out.println("<<<Congratulations! You guessed the word!>>>");
                return;
            }

            attempts--;
        }

        System.out.println("Game over. The correct word was: " + answer);
    }
}
