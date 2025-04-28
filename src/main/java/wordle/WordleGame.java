package wordle;

import java.util.HashMap;
import java.util.Map;

public class WordleGame {
    private final String answer;

    public WordleGame(String answer) {
        this.answer = answer.toUpperCase();
    }

    public Feedback checkGuess(String guess) {
        guess = guess.toUpperCase();
        char[] guessChars = guess.toCharArray();
        char[] marks = new char[5];

        boolean[] matchedInAnswer = new boolean[5];

        // Step 1: Green matches (correct letter and position)
        for (int i = 0; i < 5; i++) {
            if (guessChars[i] == answer.charAt(i)) {
                marks[i] = 'G'; // Green
                matchedInAnswer[i] = true;
            }
        }

        // Step 2: Track unused letters
        Map<Character, Integer> unusedAnswerLetters = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            if (!matchedInAnswer[i]) {
                char c = answer.charAt(i);
                unusedAnswerLetters.put(c, unusedAnswerLetters.getOrDefault(c, 0) + 1);
            }
        }

        // Step 3: Yellow matches or blank
        for (int i = 0; i < 5; i++) {
            if (marks[i] == 'G') continue;
            char c = guessChars[i];
            if (unusedAnswerLetters.getOrDefault(c, 0) > 0) {
                marks[i] = 'Y'; // Yellow
                unusedAnswerLetters.put(c, unusedAnswerLetters.get(c) - 1);
            } else {
                marks[i] = 'B'; // Blank (gray)
            }
        }

        return new Feedback(guessChars, marks);
    }
}
