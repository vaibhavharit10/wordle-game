package wordle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static wordle.Constants.ANSI_RESET;
import static wordle.Constants.GRAY_BACKGROUND;
import static wordle.Constants.GREEN_BACKGROUND;
import static wordle.Constants.YELLOW_BACKGROUND;

class WordleGameTest {

    public static final String EXPECTED = "Expected:";
    public static final String ACTUAL = "Actual:";

    @Test
    void testFeedbackOtterAgainstWater() {
        WordleGame game = new WordleGame("WATER");

        Feedback feedback = game.checkGuess("OTTER");

        String expected =
                        GRAY_BACKGROUND + " O " + ANSI_RESET + // White background for 'O'
                        GRAY_BACKGROUND + " T " + ANSI_RESET + // White background for first 'T'
                        GREEN_BACKGROUND + " T " + ANSI_RESET + // Green background for second 'T'
                        GREEN_BACKGROUND + " E " + ANSI_RESET + // Green background for 'E'
                        GREEN_BACKGROUND + " R " + ANSI_RESET;  // Green background for 'R'

        String actual = feedback.coloredOutput();

        System.out.println(EXPECTED + "\n" + expected);
        System.out.println(ACTUAL + "\n" + actual);

        assertEquals(expected, actual);
    }

    @Test
    void testExactMatchAllGreen() {
        WordleGame game = new WordleGame("WATER");
        Feedback feedback = game.checkGuess("WATER");
        String expected =
                        GREEN_BACKGROUND + " W " + ANSI_RESET +
                        GREEN_BACKGROUND + " A " + ANSI_RESET +
                        GREEN_BACKGROUND + " T " + ANSI_RESET +
                        GREEN_BACKGROUND + " E " + ANSI_RESET +
                        GREEN_BACKGROUND + " R " + ANSI_RESET;
        String actual = feedback.coloredOutput();

        System.out.println(EXPECTED + "\n" + expected);
        System.out.println(ACTUAL + "\n" + actual);

        assertEquals(expected, actual);
    }

    @Test
    void testNoMatchesAllGray() {
        WordleGame game = new WordleGame("WATER");
        Feedback feedback = game.checkGuess("PLUMB");
        String expected =
                        GRAY_BACKGROUND + " P " + ANSI_RESET +
                        GRAY_BACKGROUND + " L " + ANSI_RESET +
                        GRAY_BACKGROUND + " U " + ANSI_RESET +
                        GRAY_BACKGROUND + " M " + ANSI_RESET +
                        GRAY_BACKGROUND + " B " + ANSI_RESET;
        String actual = feedback.coloredOutput();

        System.out.println(EXPECTED + "\n" + expected);
        System.out.println(ACTUAL + "\n" + actual);

        assertEquals(expected, actual);
    }

    @Test
    void testRepeatedLetterInAnswerOnlyOneYellow() {
        WordleGame game = new WordleGame("PAPER");
        Feedback feedback = game.checkGuess("PIZZA");
        String expected =
                        GREEN_BACKGROUND + " P " + ANSI_RESET +
                        GRAY_BACKGROUND + " I " + ANSI_RESET +
                        GRAY_BACKGROUND + " Z " + ANSI_RESET +
                        GRAY_BACKGROUND + " Z " + ANSI_RESET +
                        YELLOW_BACKGROUND + " A " + ANSI_RESET;
        String actual = feedback.coloredOutput();

        System.out.println(EXPECTED + "\n" + expected);
        System.out.println(ACTUAL + "\n" + actual);

        assertEquals(expected, actual);
    }

    @Test
    void testYellowOnlyOnceWhenLetterOccursOnceInAnswer() {
        WordleGame game = new WordleGame("CHAIR");
        Feedback feedback = game.checkGuess("CIVIC");
        String expected =
                        GREEN_BACKGROUND + " C " + ANSI_RESET +
                        GRAY_BACKGROUND + " I "  + ANSI_RESET +
                        GRAY_BACKGROUND + " V " + ANSI_RESET +
                        GREEN_BACKGROUND + " I " + ANSI_RESET +
                        GRAY_BACKGROUND  + " C " + ANSI_RESET;
        String actual = feedback.coloredOutput();

        System.out.println(EXPECTED + "\n" + expected);
        System.out.println(ACTUAL + "\n" + actual);

        assertEquals(expected, actual);
    }
}
