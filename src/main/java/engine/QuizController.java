package engine;

import engine.db.Quiz;
import engine.quiz.QuizAdd;
import engine.quiz.QuizAnswer;
import engine.quiz.QuizDetails;
import engine.quiz.QuizFeedback;
import engine.quiz.exceptions.QuizNotFoundException;
import engine.quiz.exceptions.QuizOptionsRequiredException;
import engine.quiz.exceptions.QuizTextRequiredException;
import engine.quiz.exceptions.QuizTitleRequiredException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {

    final private List<Quiz> db = new ArrayList<>();

    @PostMapping(path = "/api/quizzes")
    public Quiz addQuiz(@RequestBody QuizAdd quizAdd) {
        checkQuiz(quizAdd);
        Quiz newQuiz = new Quiz(db.size(),
                quizAdd.getTitle(), quizAdd.getText(),
                quizAdd.getOptions(), quizAdd.getAnswer());
        db.add(newQuiz);

        return newQuiz;
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
        for (Quiz entry : db) {
            quizzes.add(new QuizDetails(entry));
        }

        return quizzes;
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public QuizFeedback solveQuiz(@RequestBody QuizAnswer answer, @PathVariable int id) {
        try {
            int[] answerAnswer;
            if (answer == null || answer.getAnswer() == null) {
                answerAnswer = new int[0];
            } else {
                answerAnswer = answer.getAnswer();
            }

            int[] quizAnswer = db.get(id).getAnswer();
            if (quizAnswer == null) {
                quizAnswer = new int[0];
            }

            boolean isSuccess = false;
            if (answerAnswer.length == quizAnswer.length) {
                isSuccess = true;
                for (int i = 0; i < quizAnswer.length; i++) {
                    if (answerAnswer[i] != quizAnswer[i]) {
                        isSuccess = false;
                        break;
                    }
                }
            }

            return new QuizFeedback(isSuccess, "Feedback!!!");

        } catch (IndexOutOfBoundsException exc) {
            throw new QuizNotFoundException();
        }
    }

    private void checkQuiz(QuizAdd quizAdd) {
        if (quizAdd.getTitle() == null
                || quizAdd.getTitle().equals("")) {
            throw new QuizTitleRequiredException();
        }
        if (quizAdd.getText() == null
                || quizAdd.getText().equals("")) {
            throw new QuizTextRequiredException();
        }
        if (quizAdd.getOptions() == null
                || quizAdd.getOptions().length < 2) {
            throw new QuizOptionsRequiredException();
        }
    }
}
