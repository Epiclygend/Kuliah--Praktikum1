import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    private Scanner input;
    private int whatGuess;
    private int scoreInit = 100;
    private ArrayList<Integer> tries = new ArrayList<>();
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
        score += scoreInit;
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
                tries.add(input.nextInt());
                break;
            } catch (Exception e) {
                System.err.println("Something error! Please try again!");
                input.next();
                continue;
            }
        }
    }

    private int getGuessed() {
        return tries.get(tries.size() - 1);
    }

    private void whenGuessedWrong() {
        final int scoreDecrement = 2;

        score = score - scoreDecrement;
    }

    private boolean isGuessedWrong() {
        if (getGuessed() > whatGuess)
            System.err.println("Salah! tebakan anda terlalu besar!");
        else if (getGuessed() < whatGuess)
            System.err.println("Salah! tebakan anda terlalu kecil!");

        return whatGuess != getGuessed();
    }

    private void claimBonus() {
        if (tries.size() <= 5) {
            isReceivedBonus = true;
            score = score + bonusPoint;
        }
    }

    public void printGameStats() {
        System.out.println("\t-------Game-stats-------");
        System.out.println("Skor awal\t= " + scoreInit);
        System.out.println("Jumlah tebakan\t= " + tries.size());
        System.out.println(isReceivedBonus ? "+ bonus(" + bonusPoint + ")" : "");
        System.out.println("Skor akhir anda\t= " + score);
    }
}
