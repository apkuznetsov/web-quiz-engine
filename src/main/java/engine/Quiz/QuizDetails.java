package engine.quiz;

import engine.db.Quiz;

public class QuizDetails {

    final private int id;
    final private String title;
    final private String text;
    final private String[] options;

    public QuizDetails(int id, String title, String text, String[] options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public QuizDetails(Quiz quiz) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.text = quiz.getText();
        this.options = quiz.getOptions().clone();
    }

    public int getId() {
        return id;
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
