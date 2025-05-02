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

            WordleRound session = new WordleRound(answer, scanner);
            session.play();

            System.out.print("Do you want to play again? (y/n): ");
        } while (scanner.nextLine().trim().equalsIgnoreCase("y"));
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
