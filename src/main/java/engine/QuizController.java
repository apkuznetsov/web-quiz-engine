package engine;

import engine.Quiz.QuizDetails;
import engine.Quiz.QuizFeedback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {

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
}
