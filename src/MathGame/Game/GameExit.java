package MathGame.Game;

public class GameExit extends Exception {
    private static final long serialVersionUID = -4215637164062431260L;

    public GameExit() {
        super("Game exited!");
    }

}
