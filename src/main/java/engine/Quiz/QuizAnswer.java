package engine.quiz;

public class QuizAnswer {

    private int[] answer;

    public QuizAnswer() {
    }

    public QuizAnswer(int[] answer) {
        this.answer = answer;
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
