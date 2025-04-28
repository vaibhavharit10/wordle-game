package wordle;

public record Feedback(char[] guess, char[] marks) {

    public String coloredOutput() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guess.length; i++) {
            String color;
            switch (marks[i]) {
                case 'G' -> color = "\u001B[42m"; // Green background
                case 'Y' -> color = "\u001B[43m"; // Yellow background
                default  -> color = "\u001B[47m"; // Gray background
            }
            sb.append(color)
                    .append(" ")
                    .append(guess[i])
                    .append(" ")
                    .append("\u001B[0m ");
        }
        return sb.toString();
    }
}
