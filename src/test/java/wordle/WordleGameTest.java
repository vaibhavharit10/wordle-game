package wordle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordleGameTest {

    @Test
    void testFeedbackOtterAgainstWater() {
        WordleGame game = new WordleGame("WATER");

        Feedback feedback = game.checkGuess("OTTER");

        String expected =
                "\u001B[47m O \u001B[0m " + // White background for 'O'
                        "\u001B[47m T \u001B[0m " + // White background for first 'T'
                        "\u001B[42m T \u001B[0m " + // Green background for second 'T'
                        "\u001B[42m E \u001B[0m " + // Green background for 'E'
                        "\u001B[42m R \u001B[0m ";  // Green background for 'R'

        assertEquals(expected, feedback.coloredOutput());
    }
}
