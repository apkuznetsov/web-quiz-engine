package engine.db;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String text;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "QuizId", nullable = false)
    private List<Option> options = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "QuizId", nullable = false)
    private List<Answer> answers = new ArrayList<>();

    public Quiz() {
    }

    public Quiz(int id, String title, String text,
                int[] answer) {
        this.id = id;
        this.title = title;
        this.text = text;
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
}
