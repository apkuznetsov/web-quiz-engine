package engine;

import engine.db.Answer;
import engine.db.Quiz;
import engine.db.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public ResponseEntity<Quiz> addQuiz(@Valid @RequestBody Quiz quiz) {
        quizRepository.save(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        return quiz == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @GetMapping(path = "/api/quizzes")
    public ResponseEntity<List<Quiz>> getQuizzes() {
        return new ResponseEntity<>(new ArrayList<>(quizRepository.findAll()), HttpStatus.OK);
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public ResponseEntity<QuizFeedback> solveQuiz(@PathVariable Long id, @RequestBody Answer answer) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        return quiz == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(new QuizFeedback(quiz, answer), HttpStatus.OK);
    }
}
