package MathGame;

import MathGame.Game.*;

public class MathGame {

    public static void main(String[] args) {
        User user = new User(Utils.inputString("Mohon masukkan nama anda: "));
        try {
            Game game = MathGame.getGame(user);

            game.start();
        } catch (Exception e) {}
    }

    private static Game getGame(User user) throws Exception {
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
                return getGame(user);
        }
    }
}
