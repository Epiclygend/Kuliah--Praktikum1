package MathGame;

import MathGame.Game.*;

public class MathGame {
    public static int maxLengthString = 50;
    public static int scoreIncrement = 5;
    public static int scoreDecrement = 2;

    public static void main(String[] args) {
        MathGame.greeting();
        User user = new User(Utils.inputString("Mohon masukkan nama anda: "));
        System.out.println();
        System.out.println("Selamat datang " + user.name + "!");

        try {
            while (true) {
                Game game = MathGame.getGame(user);
                game.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        user.printGameStats();
    }

    private static Game getGame(User user) throws GameExit {
        System.out.println("Menu:");
        System.out.println("1. Penjumlahan");
        System.out.println("2. Pengurangan");
        System.out.println("3. Exit");

        int pilihan = Utils.inputInteger("Pilih no. Menu: ");

        switch (pilihan) {
            case 1:
                return new Penjumlahan(user);

            case 2:
                return new Pengurangan(user);

            case 3:
                throw new GameExit();

            default:
                System.err.println(pilihan + " tidak tersedia, silahkan pilih menu yang lain");
                return getGame(user);
        }
    }

    private static void greeting() {
        System.out.println("=".repeat(MathGame.maxLengthString));
        System.out.println("|" + Utils.padCenter("Math Game", MathGame.maxLengthString - 2) + "|");
        System.out.println("=".repeat(MathGame.maxLengthString));
    }
}
