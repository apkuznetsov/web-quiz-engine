package engine.db;

import javax.persistence.Column;
import javax.persistence.Id;

public class Answer {
    @Id
    @Column(name = "AnswerId")
    private long id;

    private int answer;

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
