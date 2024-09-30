import java.util.Random;
import java.util.Scanner;

public class number_game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int roundsWon = 0;
        int totalAttempts = 0;

        while (true) {
            int number = random.nextInt(100) + 1; // Generate number between 1 and 100
            int attempts = 0;
            final int MAX_ATTEMPTS = 7;
            boolean guessedCorrectly = false;

            System.out.println("I'm thinking of a number between 1 and 100. You have 7 attempts to guess it.");

            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess < 1 || guess > 100) {
                    System.out.println("Please guess a number between 1 and 100.");
                    attempts--; // Don't count this invalid attempt
                    continue;
                }

                if (guess < number) {
                    System.out.println("Too low! Try again.");
                } else if (guess > number) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You've guessed the number " + number + " correctly in "
                            + attempts + " attempts.");
                    guessedCorrectly = true;
                    break;
                }
            }

            if (!guessedCorrectly) {
                System.out.println(
                        "Sorry, you've used all " + MAX_ATTEMPTS + " attempts. The number was " + number + ".");
            }

            roundsWon += guessedCorrectly ? 1 : 0;
            totalAttempts += MAX_ATTEMPTS;

            System.out.print("Would you like to play another round? (yes/no): ");
            String playAgain = scanner.next().trim().toLowerCase();

            if (!playAgain.equals("yes")) {
                break;
            }
        }

        System.out.println("Final Score:");
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("Total Attempts: " + totalAttempts);

        scanner.close();
    }
}