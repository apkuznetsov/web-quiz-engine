package engine;

public class QuizFeedback {

    private final boolean success;
    private final String feedback;

    public QuizFeedback(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
