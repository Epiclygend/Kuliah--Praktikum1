package MathGame;

public class User {
    public String name;
    public int lives = 3;
    public int score = 0;

    public User(String username) {
        this.name = username;
    }

    @Override
    public String toString() {
        return "{" +
            "\n\tname: " + this.name +
        "\n}";
    }
}
