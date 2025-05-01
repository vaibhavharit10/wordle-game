# CLI Wordle - Java Edition

A command-line version of the Wordle game implemented in Java, built as part of a technical assessment.

---

## Game Rules

- You must guess a **5-letter word** in **5 attempts**
- After each guess, you'll receive color-coded feedback for each letter:
  - **Green**: Correct letter in the correct position
  - **Yellow**: Correct letter but in the wrong position
  - **Gray**: Letter not present in the target word
- Duplicate letters are handled according to Wordle rules:
  - For example: if the answer is `WATER` and your guess is `OTTER`, only one `T` will be green
- After the game ends, you'll be asked if you want to play again

---

## Features

- Accurate Wordle letter-matching logic
- Terminal-based UI with colored output
- Input validation (5-letter, alphabetic only)
- "Play again" option
- JUnit 5 test coverage
- Gradle-based build
- GitHub Actions CI integration 
---

## Prerequisites

- Java 21 (Java 17+ recommended)
- Gradle (or use the included `gradlew` wrapper)
- Terminal that supports ANSI colors (e.g., VS Code Terminal, Windows Terminal)

---

## How to Run the Game

### 1. Clone the repository
```bash
git clone https://github.com/vaibhavharit10/wordle-game.git
```
### 2. Build the Project
```bash
./gradlew build
```
### 3. Run the project
```bash
java -cp build/classes/java/main wordle.Main
```
 

