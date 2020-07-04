package engine;

import engine.Quiz.Quiz;
import engine.Quiz.QuizDetails;
import engine.Quiz.QuizEntry;
import engine.Quiz.QuizFeedback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    private int idSequence = 10;
    final private List<QuizEntry> db = new ArrayList<>();

    final private String[] options = new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"};
    final private QuizDetails quizDetails = new QuizDetails(
            "The Java Logo",
            "What is depicted on the Java logo?",
            options);

    @GetMapping(path = "/api/quiz")
    public QuizDetails getQuizDetails() {
        return quizDetails;
    }

    @PostMapping(path = "/api/quiz")
    public QuizFeedback postAnswer(int answer) {
        final boolean isSuccess = answer == 2;

        return new QuizFeedback(isSuccess, "Feedback!!!");
    }

    @PostMapping(path = "/api/quizzes")
    public QuizEntry addQuiz(@RequestBody Quiz quiz) {
        QuizEntry newQuizEntry = new QuizEntry(idSequence, quiz);
        db.add(newQuizEntry);
        idSequence++;

        return newQuizEntry;
    }
}
