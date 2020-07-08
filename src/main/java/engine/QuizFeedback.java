package engine;

import engine.db.quiz.Answer;
import engine.db.quiz.Quiz;

public class QuizFeedback {

    private final boolean isSuccess;
    private final String feedback;

    public QuizFeedback(Quiz quiz, Answer answer) {
        if (quiz.getAnswer().equals(answer.getAnswer())) {
            isSuccess = true;
            feedback = "Congratulations, you're right!";
        } else {
            isSuccess = false;
            feedback = "Wrong answer! Please, try again.";
        }
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getFeedback() {
        return feedback;
    }
}
