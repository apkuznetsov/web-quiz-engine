package engine;

import engine.db.quiz.Answer;
import engine.db.quiz.Quiz;
import engine.db.quiz.QuizRepository;
import engine.db.user.User;
import engine.db.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;
    
    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        User foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser == null) {
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/quizzes", consumes = "application/json")
    public ResponseEntity<Quiz> addQuiz(@Valid @RequestBody Quiz quiz) {
        quizRepository.save(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @GetMapping(path = "/quizzes/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        return quiz == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @GetMapping(path = "/quizzes")
    public ResponseEntity<List<Quiz>> getQuizzes() {
        return new ResponseEntity<>(new ArrayList<>(quizRepository.findAll()), HttpStatus.OK);
    }

    @PostMapping(path = "/quizzes/{id}/solve")
    public ResponseEntity<QuizFeedback> solveQuiz(@PathVariable Long id, @RequestBody Answer answer) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        return quiz == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(new QuizFeedback(quiz, answer), HttpStatus.OK);
    }

    @DeleteMapping("/quizzes/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        if (quizRepository.existsById(id)) {
            if (canCurrUserDeleteQuiz(id)) {
                quizRepository.deleteById(id);
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
}
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private boolean canCurrUserDeleteQuiz(Long quizId) {
        return quizRepository.findById(quizId).get().getUser().getId()
                .equals(
                        userRepository.findByEmail(
                                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                                        .getUsername()
                        ).getId()
                );
    }
