package engine.quiz;

public class Quiz {

    private String title;
    private String text;
    private String[] options;
    private int[] answer;
    
    public Quiz(String title, String text,
                String[] options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = options.clone();
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

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options.clone();
    }

    public void setOptions(String[] options) {
        this.options = options.clone();
    }

    public int[] getAnswer() {
        return answer.clone();
    }

    public void setAnswer(int[] answer) {
        this.answer = answer.clone();
    }
}