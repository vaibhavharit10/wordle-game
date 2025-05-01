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

        String actual = feedback.coloredOutput();

        System.out.println("Expected:\n" + expected);
        System.out.println("Actual:\n" + actual);

        assertEquals(expected, actual);
    }

    @Test
    void testExactMatchAllGreen() {
        WordleGame game = new WordleGame("WATER");
        Feedback feedback = game.checkGuess("WATER");
        String expected =
                        "\u001B[42m W \u001B[0m " +
                        "\u001B[42m A \u001B[0m " +
                        "\u001B[42m T \u001B[0m " +
                        "\u001B[42m E \u001B[0m " +
                        "\u001B[42m R \u001B[0m ";
        String actual = feedback.coloredOutput();

        System.out.println("Expected:\n" + expected);
        System.out.println("Actual:\n" + actual);

        assertEquals(expected, actual);
    }

    @Test
    void testNoMatchesAllGray() {
        WordleGame game = new WordleGame("WATER");
        Feedback feedback = game.checkGuess("PLUMB");
        String expected =
                        "\u001B[47m P \u001B[0m " +
                        "\u001B[47m L \u001B[0m " +
                        "\u001B[47m U \u001B[0m " +
                        "\u001B[47m M \u001B[0m " +
                        "\u001B[47m B \u001B[0m ";
        String actual = feedback.coloredOutput();

        System.out.println("Expected:\n" + expected);
        System.out.println("Actual:\n" + actual);

        assertEquals(expected, actual);
    }

    @Test
    void testRepeatedLetterInAnswerOnlyOneYellow() {
        WordleGame game = new WordleGame("PAPER");
        Feedback feedback = game.checkGuess("PIZZA");
        String expected =
                        "\u001B[42m P \u001B[0m " + // Green P
                        "\u001B[47m I \u001B[0m " +
                        "\u001B[47m Z \u001B[0m " +
                        "\u001B[47m Z \u001B[0m " +
                        "\u001B[43m A \u001B[0m ";  // Yellow P
        String actual = feedback.coloredOutput();

        System.out.println("Expected:\n" + expected);
        System.out.println("Actual:\n" + actual);

        assertEquals(expected, actual);
    }

    @Test
    void testYellowOnlyOnceWhenLetterOccursOnceInAnswer() {
        WordleGame game = new WordleGame("CHAIR");
        Feedback feedback = game.checkGuess("CIVIC");
        String expected =
                        "\u001B[42m C \u001B[0m " + // green
                        "\u001B[47m I \u001B[0m " + // gray
                        "\u001B[47m V \u001B[0m " + // gray
                        "\u001B[42m I \u001B[0m " + // green
                        "\u001B[47m C \u001B[0m ";  // gray
        String actual = feedback.coloredOutput();

        System.out.println("Expected:\n" + expected);
        System.out.println("Actual:\n" + actual);

        assertEquals(expected, actual);
    }
}
