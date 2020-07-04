package engine;

import engine.Quiz.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    final private List<QuizEntry> db = new ArrayList<>();

    @GetMapping(path = "/api/quizzes/{id}")
    public QuizDetails getQuiz(@PathVariable int id) {
        try {
            return new QuizDetails(db.get(id));
        } catch (IndexOutOfBoundsException exc) {
            throw new QuizNotFoundException();
        }
    }

    @PostMapping(path = "/api/quiz")
    public QuizFeedback postAnswer(int answer) {
        final boolean isSuccess = answer == 2;

        return new QuizFeedback(isSuccess, "Feedback!!!");
    }

    @PostMapping(path = "/api/quizzes")
    public QuizEntry addQuiz(@RequestBody Quiz quiz) {
        QuizEntry newQuizEntry = new QuizEntry(db.size(), quiz);
        db.add(newQuizEntry);

        return newQuizEntry;
    }
}
