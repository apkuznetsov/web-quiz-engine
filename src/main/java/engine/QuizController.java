package engine;

import engine.quiz.*;
import engine.quiz.exceptions.QuizHasNoTextException;
import engine.quiz.exceptions.QuizHasNoTitleException;
import engine.quiz.exceptions.QuizNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    final private List<QuizEntry> db = new ArrayList<>();

    @PostMapping(path = "/api/quizzes")
    public QuizEntry addQuiz(@RequestBody Quiz quiz) {
        if (quiz.getTitle() == null
                || quiz.getTitle().equals("")) {
            throw new QuizHasNoTitleException();
        }
        if (quiz.getText() == null
                || quiz.getText().equals("")) {
            throw new QuizHasNoTextException();
        }

        QuizEntry newQuizEntry = new QuizEntry(db.size(), quiz);
        db.add(newQuizEntry);

        return newQuizEntry;
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public QuizDetails getQuiz(@PathVariable int id) {
        try {
            return new QuizDetails(db.get(id));
        } catch (IndexOutOfBoundsException exc) {
            throw new QuizNotFoundException();
        }
    }

    @GetMapping(path = "/api/quizzes")
    public List<QuizDetails> getQuizzes() {
        List<QuizDetails> quizzes = new ArrayList<>(db.size());
        for (QuizEntry entry : db) {
            quizzes.add(new QuizDetails(entry));
        }

        return quizzes;
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public QuizFeedback solveQuiz(QuizAnswer answer, @PathVariable int id) {
        try {
            QuizEntry quiz = db.get(id);
            boolean isSuccess = answer.getAnswer() == quiz.getAnswer();

            return new QuizFeedback(isSuccess, "Feedback!!!");

        } catch (IndexOutOfBoundsException exc) {
            throw new QuizNotFoundException();
        }
    }
}
