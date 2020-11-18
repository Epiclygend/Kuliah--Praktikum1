package MathGame.Game;

import MathGame.User;

public class Penjumlahan extends Game {

    public Penjumlahan(User user) {
        super(user);
    }

    @Override
    protected String getOperator() {
        return "+";
    }

    @Override
    protected boolean assertAnswerIsCorrect(int answer, int numb1, int numb2) {
        return answer == numb1 + numb2;
    }

}
