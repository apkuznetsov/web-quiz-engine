package engine.quiz;

public class Quiz {

    final private String text;
    final private String[] options;
    final private int[] answer;
    private String title;

    public Quiz(String title, String text,
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer.clone();
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

    public String[] getOptions() {
        return options;
    }

    public int[] getAnswer() {
        return answer.clone();
    }
}