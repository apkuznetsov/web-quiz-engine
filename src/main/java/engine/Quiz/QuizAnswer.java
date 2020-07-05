package engine.quiz;

public class QuizAnswer {

    private int[] answer;

    public QuizAnswer(int[] answer) {
        this.answer = answer.clone();
    }

    public int[] getAnswer() {
        return answer.clone();
    }

    public void setAnswer(int[] answer) {
        this.answer = answer.clone();
    }
}
