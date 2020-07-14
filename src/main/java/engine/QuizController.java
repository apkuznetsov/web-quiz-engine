package engine;

import engine.models.*;
import engine.repositories.QuizCompletedRepository;
import engine.repositories.QuizRepository;
import engine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizCompletedRepository quizCompletedRepository;

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User newUser) {
        User foundUser = userRepository.findByEmail(newUser.getEmail());
        if (foundUser == null) {
            newUser.setPassword(
                    bCryptPasswordEncoder.encode(newUser.getPassword())
            );
            userRepository.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(newUser, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/quizzes")
    public ResponseEntity<Page<Quiz>> getQuizzes(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return new ResponseEntity<>(quizRepository.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "/quizzes", consumes = "application/json")
    public ResponseEntity<Quiz> addQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal User user) {
        quiz.setUser(user);
        quizRepository.save(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @GetMapping("/quizzes/completed")
    public Page<QuizCompleted> getCompletedQuizzes(@RequestParam int page, @AuthenticationPrincipal User user) {
        Pageable pageable = PageRequest.of(page, 10);
        return quizCompletedRepository.findCompletedQuizzesPaginated(user.getId(), pageable);
    }

    @GetMapping(path = "/quizzes/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        return quiz == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @DeleteMapping("/quizzes/{id}")
    public void delete(@PathVariable long id, @AuthenticationPrincipal User user) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            if (quiz.get().getUser().equals(user)) {
                quizRepository.delete(quiz.get());
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/quizzes/{id}/solve")
    public ResponseEntity<Feedback> solveQuiz(@PathVariable long id, @RequestBody Answer answer, @AuthenticationPrincipal User user) {
        Optional<Quiz> quizzes = quizRepository.findById(id);
        if (quizzes.isEmpty()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Quiz quiz = quizzes.get();
        Feedback feedback = new Feedback(quiz, answer);

        if (feedback.isSuccess()) {
            QuizCompleted quizCompleted = new QuizCompleted();
            quizCompleted.setCompletedAt(LocalDateTime.now());
            quizCompleted.setQuiz(quiz);
            quizCompleted.setUser(user);
            quizCompleted = quizCompletedRepository.save(quizCompleted);

            quiz.getQuizCompleteds().add(quizCompleted);
            quizRepository.save(quiz);
        }

        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }
}
