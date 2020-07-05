package engine;

import engine.quiz.*;
import engine.quiz.exceptions.QuizNotFoundException;
import engine.quiz.exceptions.QuizOptionsRequiredException;
import engine.quiz.exceptions.QuizTextRequiredException;
import engine.quiz.exceptions.QuizTitleRequiredException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    final private List<QuizEntry> db = new ArrayList<>();

    @PostMapping(path = "/api/quizzes")
    public QuizEntry addQuiz(@RequestBody Quiz quiz) {
        checkQuiz(quiz);
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

    private void checkQuiz(Quiz quiz) {
        if (quiz.getTitle() == null
                || quiz.getTitle().equals("")) {
            throw new QuizTitleRequiredException();
        }
        if (quiz.getText() == null
                || quiz.getText().equals("")) {
            throw new QuizTextRequiredException();
        }
        if (quiz.getOptions() == null
                || quiz.getOptions().length < 2) {
            throw new QuizOptionsRequiredException();
        }
    }
}
