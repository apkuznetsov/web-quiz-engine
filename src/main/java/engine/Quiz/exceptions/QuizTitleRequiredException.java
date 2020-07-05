package engine.quiz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Quiz Title Required")
public class QuizTitleRequiredException extends RuntimeException {
}
