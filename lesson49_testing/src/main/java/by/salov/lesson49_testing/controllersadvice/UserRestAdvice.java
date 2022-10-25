package by.salov.lesson49_testing.controllersadvice;

import by.salov.lesson49_testing.exception.UserNotValidExeption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller for catching exceptions
 */
@RestControllerAdvice
public class UserRestAdvice {
    /*Catch UserNotValidExeption*/
    @ExceptionHandler(UserNotValidExeption.class)
    public ResponseEntity<?> responseEntity() {
        /*return 400 response*/
        return ResponseEntity.badRequest().build();
    }
}
