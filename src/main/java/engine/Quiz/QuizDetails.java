package engine.Quiz;

public class QuizDetails {

    final private String title;
    final private String text;
    final private String[] options;

    public QuizDetails(String title, String text, String[] options) {
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }
}
