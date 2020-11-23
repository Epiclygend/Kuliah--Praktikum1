package MathGame;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import MathGame.Game.Game;

public class User {
    public String name;
    public int lives = 3;
    private List<Game> gamePlayed = new ArrayList<Game>();

    public User(String username) {
        this.name = username;
    }

    public int getScore() {
        BiFunction<Integer, Game, Integer> counter = (total, elm) -> total + elm.getScoreResult();

        return gamePlayed.stream().reduce(0, counter, Integer::sum);
    }

    @Override
    public String toString() {
        return "{" + "\n\tname: " + this.name + "\n\ttotal_score: " + this.getScore() + "\n}";
    }

    public void playAGame(Game game) {
        gamePlayed.add(game);
    }
}
