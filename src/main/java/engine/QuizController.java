package engine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {

    final private String[] options = new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"};
    final private Quiz quiz = new Quiz(
            "The Java Logo",
            "What is depicted on the Java logo?",
            options);

    @GetMapping(path = "/api/quiz")
    public Quiz getQuiz() {
        return quiz;
    }
}
