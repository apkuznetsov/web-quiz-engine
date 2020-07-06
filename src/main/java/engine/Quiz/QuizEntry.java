package engine.quiz;

public class QuizEntry {

    private int id;
    private String title;
    private String text;
    private String[] options;
    private int[] answer;

    public QuizEntry() {
    }

    public QuizEntry(int id, String title, String text,
                     String[] options, int[] answer) {
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
        this.options = quiz.getOptions();
        this.answer = quiz.getAnswer();
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
