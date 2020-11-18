package MathGame.Game;

import MathGame.User;
import MathGame.Utils;

public abstract class Game {
    protected User user;
    private int level;

    protected abstract String getOperator();

    protected abstract boolean assertAnswerIsCorrect(int answer, int numb1, int numb2);

    public Game(User user) {
        this.user = user;
    }

    public void start() {
        this.play();

        System.out.println("Game over!");
        System.out.println(user.name);
        System.out.println(user.score);
        System.out.println(user.lives);
    }

    final private void play() {
        final int numb1 = this.generateNumb();
        final int numb2 = this.generateNumb();
        int answer;

        do {
            System.out.println(getPrintableQuestion(numb1, numb2));
            answer = Utils.inputInteger("Jawab: ");

            this.whenAnswerIsWrong();
        } while (!this.assertAnswerIsCorrect(answer, numb1, numb2));

        this.whenAnswerIsCorrect();
    }

    final private int generateNumb() {
        final int range = 10;
        final int generated = Utils.randGenerator.nextInt(range + 1);

        switch (this.level) {
            case 2:
                return -generated;
            case 3:
                return generated * (Utils.generateRandomBoolean() ? 1 : -1);
            default:
                return generated;
        }
    }

    protected void whenAnswerIsCorrect() {
        this.user.score += 5;

        this.answerResponse(this.correctAnswerResponse());
        this.play();
    }

    protected void whenAnswerIsWrong() {
        this.user.score -= 2;
        this.user.lives -= 1;

        this.answerResponse(this.wrongAnswerResponse());
    }

    protected void answerResponse(String message) {
        System.out.print(Utils.squareBracket(message));
        System.out.print(Utils.squareBracket("Skor: " + user.score));
        System.out.print(Utils.squareBracket("Lives: " + user.lives));
        System.out.print(Utils.squareBracket("Level: " + this.level));
        System.out.println();
    }

    final protected String correctAnswerResponse() {
        return "Selamat " + user.name + ", anda benar!";
    }

    final protected String wrongAnswerResponse() {
        return "yaah salah :(";
    }

    final private String printedNumber(Integer n) {
        return n < 0 ? Utils.parenthesize(n.toString()) : n.toString();
    }

    final protected String getPrintableQuestion(int numb1, int numb2) {
        return "Berapakah hasil dari " + printedNumber(numb1) + " " + this.getOperator() + " " + printedNumber(numb2)
                + " ?";
    }
}
