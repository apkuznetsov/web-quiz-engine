package engine.quiz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Quiz Options Required at Least 2 Items")
public class QuizOptionsRequiredException extends RuntimeException {
}
