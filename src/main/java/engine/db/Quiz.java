package engine.db;

import engine.quiz.QuizAdd;

public class Quiz {

    private int id;
    private String title;
    private String text;
    private String[] options;
    private int[] answer;

    public Quiz() {
    }

    public Quiz(int id, String title, String text,
                String[] options, int[] answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public Quiz(int id, QuizAdd quizAdd) {
        this.id = id;
        this.title = quizAdd.getTitle();
        this.text = quizAdd.getText();
        this.options = quizAdd.getOptions();
        this.answer = quizAdd.getAnswer();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
