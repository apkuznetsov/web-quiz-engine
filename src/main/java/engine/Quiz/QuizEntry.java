package engine.Quiz;

public class QuizEntry {

    final private int id;
    final private String title;
    final private String text;
    final private String[] options;
    final private int answer;

    public QuizEntry(int id, String title, String text, String[] options, int answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public QuizEntry(int id, Quiz quiz) {
        this.id = id;
        this.title = quiz.getTitle();
        this.text = quiz.getText();
        this.options = quiz.getOptions().clone();
        this.answer = quiz.getAnswer();
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

    public int getAnswer() {
        return answer;
    }
}
