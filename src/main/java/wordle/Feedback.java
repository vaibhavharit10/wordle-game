package wordle;

import static wordle.Constants.ANSI_RESET;
import static wordle.Constants.GRAY_BACKGROUND;
import static wordle.Constants.GREEN_BACKGROUND;
import static wordle.Constants.YELLOW_BACKGROUND;

public record Feedback(char[] guess, char[] marks) {

    public String coloredOutput() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guess.length; i++) {
            String color;
            switch (marks[i]) {
                case 'G' -> color = GREEN_BACKGROUND;
                case 'Y' -> color = YELLOW_BACKGROUND;
                default  -> color = GRAY_BACKGROUND;
            }
            sb.append(color)
                    .append(" ")
                    .append(guess[i])
                    .append(" ")
                    .append(ANSI_RESET);
        }
        return sb.toString();
    }
}
