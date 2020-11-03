import java.util.Random;
import java.util.Scanner;

public class Game {
    private Scanner input;
    private int whatGuess;
    private int guessed;
    private int scoreInit = 100;
    private int tries = 0;
    int score = 0;
    private int bonusPoint = 50;
    private boolean isReceivedBonus = false;

    public Game() {
        Random generator = new Random();
        whatGuess = generator.nextInt(100);
        System.out.println("Angka telah dipilih!");
    }

    public void start() {
        this.gameInit();

        while (true) {
            this.guess();

            if (isGuessedWrong())
                whenGuessedWrong();
            else
                break;
        }

        this.gameOver();
    }

    private void gameInit() {
        // Set game input
        input = new Scanner(System.in);
        // Set initial score
        score = score + scoreInit;
        // Send message when game is ready!
        System.out.println("Game Dimulai!");
    }

    private void gameOver() {
        this.claimBonus();
        System.out.println("============================================");
        System.out.println("Game selesai!");
        // Free some memory
        input.close();
    }

    private void guess() {
        while (true) {
            System.out.print("Tebak angka\t= ");

            try {
                guessed = input.nextInt();
                break;
            } catch (Exception e) {
                System.err.println("Something error! Please try again!");
                input.next();
                continue;
            }
        }

        tries++;
    }

    private void whenGuessedWrong() {
        final int scoreDecrement = 2;

        score = score - scoreDecrement;
    }

    private boolean isGuessedWrong() {
        if (guessed > whatGuess)
            System.err.println("Salah! tebakan anda terlalu besar!");
        else if (guessed < whatGuess)
            System.err.println("Salah! tebakan anda terlalu kecil!");

        return whatGuess != guessed;
    }

    private void claimBonus() {
        if (tries <= 5) {
            isReceivedBonus = true;
            score = score + bonusPoint;
        }
    }

    public void printGameStats() {
        System.out.println("\t-------Game-stats-------");
        System.out.println("Skor awal\t= " + scoreInit);
        System.out.println("Jumlah tebakan\t= " + tries);
        System.out.println(isReceivedBonus ? "+ bonus(" + bonusPoint + ")" : "");
        System.out.println("Skor akhir anda\t= " + score);
    }
}
