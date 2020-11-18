package MathGame.Game;

import MathGame.User;

public class Pengurangan extends Game {

    public Pengurangan(User user) {
        super(user);
    }

    @Override
    protected String getOperator() {
        return "-";
    }

    @Override
    protected boolean assertAnswerIsCorrect(int answer, int numb1, int numb2) {
        return answer == numb1 - numb2;
    }
}
