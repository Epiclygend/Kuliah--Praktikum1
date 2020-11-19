package MathGame.Game;

import MathGame.User;
import MathGame.Utils;

public abstract class Game {
    protected User user;
    protected int level = 1;

    protected abstract String getOperator();

    protected abstract boolean assertAnswerIsCorrect(int answer, int numb1, int numb2);

    public Game(User user) {
        this.user = user;
    }

    public void start() {
        System.out.println("=============");
        System.out.println("GAME DIMULAI!");
        System.out.println("=============");

        this.play();

        System.out.println("Game over!");
        System.out.println(Utils.squareBracket(user.name));
        System.out.println(Utils.squareBracket("final score: " + user.score));
    }

    final private void play() {
        final int[] quizNumb = this.generateQuizNumb();

        while (true) {
            if (user.lives > 0) {

                System.out.println();
                System.out.println(getPrintableQuestion(quizNumb[0], quizNumb[1]));
                int answer = Utils.inputInteger("Jawab: ");

                if (this.assertAnswerIsCorrect(answer, quizNumb[0], quizNumb[1])) {
                    this.whenAnswerIsCorrect();
                    break;

                } else
                    this.whenAnswerIsWrong();

            } else {
                this.whenNoMoreLives();
                break;
            }

        }
    }

    protected int[] generateQuizNumb() {
        int[] generated = {
            this.generateNumb(),
            this.generateNumb()
        };

        return generated;
    }

    protected void whenAnswerIsCorrect() {
        this.user.score += 5;

        // Level check
        if (user.score > 100)
            this.level = 2;
        else if (user.score > 200)
            this.level = 3;
        this.answerResponse(this.correctAnswerResponse());
        this.play();
    }

    protected void whenAnswerIsWrong() {
        this.user.score -= 2;
        this.user.lives -= 1;

        this.answerResponse(this.wrongAnswerResponse());
    }

    protected void whenNoMoreLives() {
        System.out.println("Hai " + user.name + ", jangan menyerah!");
    }

    protected void answerResponse(String message) {
        System.out.print(message);
        System.out.println();
        System.out.print(Utils.squareBracket("Skor: " + user.score));
        System.out.print(Utils.squareBracket("Lives: " + user.lives));
        System.out.print(Utils.squareBracket("Level: " + this.level));
        System.out.println();
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
